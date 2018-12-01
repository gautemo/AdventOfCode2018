const readline = require('readline');
const fs = require('fs');

const rl = readline.createInterface({
  input: fs.createReadStream('input.txt'),
  crlfDelay: Infinity
});

let frequency = 0;
rl.on('line', (line) => {
  frequency += parseInt(line);
});
rl.on('close', () => {
    console.log(frequency);
});
