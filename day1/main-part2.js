const readline = require('readline');
const fs = require('fs');

let frequency = 0;
const set = new Set([0]);
let found = false;

const readFrequencies = () => {
  const rl = readline.createInterface({
    input: fs.createReadStream('input.txt')
  });

  rl.on('line', (line) => {
    if(found) return;
    frequency += parseInt(line);
    if(set.has(frequency)){
      console.log(frequency);
      found = true;
      rl.close();
    }
    set.add(frequency);
  });
  rl.on('close', () => {
    if(!found){
      readFrequencies();
    }
  });  
}

readFrequencies();