declare var require: any;
const readline2 = require('readline');
const fs2 = require('fs');

const rl2 = readline2.createInterface({
  input: fs2.createReadStream('input.txt')
});

const boxes: string[] = [];

rl2.on('line', (line: string) => {
    boxes.push(line);
});
rl2.on('close', () => {
    checkBoxes();
});

const checkBoxes = () => {
    for(const box of boxes){
        const match = boxes.filter(b => matching(box, b).length === box.length - 1);
        if(match.length > 0){
            console.log(matching(box, match[0]));
            break;
        }
    }
}

const matching = (box1: string, box2: string): string => {
    if(box1.length !== box2.length) return '';
    let match = '';
    for(let i = 0; i<box1.length; i++){
        if(box1[i] === box2[i]) match += box1[i];
    }
    return match;
}
