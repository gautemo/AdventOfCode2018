declare var require: any;
const readline = require('readline');
const fs = require('fs');

const rl = readline.createInterface({
  input: fs.createReadStream('input.txt')
});

const steps: StepNode[] = [];
const workers = 5;
const taskSek = 60;

rl.on('line', (line: string) => {
    const [step, laterStep] = [line.split(' ')[1], line.split(' ')[7]];
    let stepNode = steps.some(s => s.id === step) ? steps.filter(s => s.id === step)[0] : null;
    if(!stepNode){
        stepNode = new StepNode(step);
        steps.push(stepNode);
    }
    let laterNode = steps.some(s => s.id === laterStep) ? steps.filter(s => s.id === laterStep)[0] : null;
    if(!laterNode){
        laterNode = new StepNode(laterStep);
        steps.push(laterNode);
    }
    laterNode.dependsOn.push(stepNode);
});
rl.on('close', () => {
    //solveTask1();
    solveTask2();
});

const solveTask1 = () => {
    let order = '';
    do{
        order += popNextStep();
    }while(steps.length > 0);
    console.log(order);
}

const solveTask2 = () => {
    let count = 0;
    let order = '';
    do{
        order += popNextStepTime();
        count++;
    }while(steps.length > 0);
    console.log(order + ': ' + count);
}

const popNextStep = () :string => {
    const nextStep: StepNode = steps.filter(s => s.dependsOn.length === 0).sort((s1,s2) => s1.compareTo(s2))[0];
    for(let i = steps.length-1; i >= 0; i--){
        if(steps[i].id === nextStep.id){
            steps.splice(i, 1);
        }else{
            steps[i].dependsOn = steps[i].dependsOn.filter(d => d.id !== nextStep.id);
        }
    }
    return nextStep.id;
}

const popNextStepTime = () :string => {
    const readySteps: StepNode[] = steps.filter(s => s.dependsOn.length === 0).sort((s1,s2) => s1.compareTo(s2)).slice(0, workers);
    const remove: StepNode[] = [];
    readySteps.forEach(s => {
        s.started = true;
        s.secondsLeft--;
        if(s.secondsLeft === 0) remove.push(s);
    })
    for(let i = steps.length-1; i >= 0; i--){
        if(remove.some(r => r.id === steps[i].id)){
            steps.splice(i, 1);
        }else{
            steps[i].dependsOn = steps[i].dependsOn.filter(d => !remove.some(r => r.id === d.id));
        }
    }
    return remove.reduce((accumulator, r) => {
        return accumulator + r.id;
    }, '');
}

class StepNode{
    id: string;
    dependsOn: StepNode[] = [];
    secondsLeft: number;
    started: boolean = false;

    constructor(id: string){
        this.id = id;
        this.secondsLeft = id.charCodeAt(0) - 64 + taskSek;
    }

    compareTo(step: StepNode): number{
        if(this.started && !step.started) return -1;
        if(!this.started && step.started) return 1;
        return this.id.localeCompare(step.id);
    }
}