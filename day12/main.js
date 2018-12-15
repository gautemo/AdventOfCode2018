const readline = require('readline');
const fs = require('fs');

const rl = readline.createInterface({input: fs.createReadStream('input.txt')});

const plants = [];
const rules = [];

let lowest = 0;

const passYears = () => {
    let number = countPotNumbers();
    const threePrev = [];
    const years = 50000000000;
    for(let i = 0; i < years; i++){
        passOneYear();
        const now = countPotNumbers();
        const diff = Math.abs(now-number);
        threePrev.push(diff);
        if(threePrev.length > 3){
            if(threePrev.every(p => p === threePrev[0])){
                i++;
                const answer = countPotNumbers() + (diff * (years - i));
                console.log('found by pattern: ' + answer);
                break;
            }
            threePrev.shift();
        }
        number = now;
    }
    console.log(countPotNumbers());
}

const passOneYear = () => {
    const copyPlants = copyArray(plants);
    for(let i=lowest-2; i<copyPlants.length+2; i++){
        const twoLeft = copyPlants[i-2] ? copyPlants[i-2] : '.';
        const left = copyPlants[i-1] ? copyPlants[i-1] : '.';
        const current = copyPlants[i] ? copyPlants[i] : '.';
        const right = copyPlants[i+1] ? copyPlants[i+1] : '.';
        const twoRight = copyPlants[i+2] ? copyPlants[i+2] : '.';
        const plantChain = twoLeft + left + current + right + twoRight;
        const rule = rules.filter(r => r.rules.trim() === plantChain)[0];
        if(i>=copyPlants.length){
            if(rule.grows.trim() === '#'){
                plants[i] = rule.grows.trim();
            }
        }else{
            plants[i] = rule.grows.trim();
        }
        if(i<lowest && plants[i] === '#') lowest = i;
    }
}

const countPotNumbers = () => {
    let total = 0;
    for(const i in plants){
        if(plants[i] === '#'){
            total += parseInt(i);
        }
    }
    return total;
}

const copyArray = (arr) => {
    const newArray = [];
    for(let i in arr){
        newArray[i] = arr[i];
    }
    return newArray;
}

rl.on('line', (line) => {
    if(plants.length === 0){
        const start = line.replace('initial state: ', '');
        plants.push(...start);
    }else{
        if(line.trim()){
            rules.push(new Rule(line));
        }
    }
});
rl.on('close', passYears);

class Rule{
    constructor(input){
        [this.rules, this.grows] = input.split('=>');
    }
}