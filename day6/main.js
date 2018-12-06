const readline = require('readline');
const fs = require('fs');

const rl = readline.createInterface({
  input: fs.createReadStream('input.txt')
});

const cords = [];
let [maxX, maxY] = [0,0];

rl.on('line', (line) => {
    const [x,y] = [parseInt(line.split(',')[0].trim()), parseInt(line.split(',')[1].trim())];
    cords.push(new Cord(x, y));
    if(x > maxX) maxX = x;
    if(y > maxY) maxY = y;
});
rl.on('close', () => {
    claimArea();
    findLargest();
    findAreasCloseToCords();
});

class Cord {
    constructor(x,y){
        this.x = x;
        this.y = y;
        this.area = 0;
        this.isInfinite = false;
    }

    calcDistance(x, y){
        return Math.abs(x - this.x) + Math.abs(y - this.y);
    }
}

const claimArea = () => {
    for(let x=0; x<=maxX; x++){
        for(let y=0; y<=maxY; y++){
            let closest = null;
            let closestDistance = 9999999;
            for(cord of cords){
                const d = cord.calcDistance(x, y);
                if(d < closestDistance){
                    closest = cord;
                    closestDistance = d;
                }else if(d === closestDistance){
                    closest = null;
                }
            }
            if(closest){
                closest.area++;
            }
            if(closest && (x === 0 || x === maxX || y === 0 || y === maxY)) closest.isInfinite = true;
        }
    }
}

const findLargest = () => {
    let largest = null;
    let area = 0;
    for(cord of cords){
        if(!cord.isInfinite && cord.area > area){
            area = cord.area;
            largest = cord;
        }
    }
    console.log(largest.x, largest.y, largest.area);
}

const findAreasCloseToCords = () => {
    let count = 0;
    for(let x=0; x<=maxX; x++){
        for(let y=0; y<=maxY; y++){
            let dist = 0;
            for(cord of cords){
                dist += cord.calcDistance(x,y);
            }
            if(dist < 10000){
                count++;
            }
        }
    }
    console.log(count);
}