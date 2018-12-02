declare var require: any;
const readline = require('readline');
const fs = require('fs');

const rl = readline.createInterface({
  input: fs.createReadStream('input.txt')
});

const twosThrees: [number, number] = [0,0];
rl.on('line', (line: string) => {
    const letters = [...line];
    const uniqueLetters = new Set(letters);
    const hasTwoTrees: [boolean, boolean] = [false, false];
    uniqueLetters.forEach(element => {
        const count = letters.filter(l => l === element).length;
        if(count === 2) hasTwoTrees[0] = true;
        if(count === 3) hasTwoTrees[1] = true;
    });
    if(hasTwoTrees[0]) twosThrees[0]++;
    if(hasTwoTrees[1]) twosThrees[1]++;
});
rl.on('close', () => {
    console.log(`${twosThrees[0]} * ${twosThrees[1]} = ${twosThrees[0] * twosThrees[1]}`);
});
