declare var require: any;
const readline = require('readline');
const fs = require('fs');

const rl = readline.createInterface({input: fs.createReadStream('input.txt')});

const removeCrashes = true;
const map: Track[][] = [];
let carts: Cart[] = [];

let readX = 0;
let readY = 0;
let readCartId = 1;

const start = () => {
    let tick = 0;
    while(carts.length > 1){
        carts.sort((a,b) => a.compareXY(b));
        for(let c of carts){
            c.move(map[c.y][c.x]);
            if(carts.some(co => c.id !== co.id && c.compareXY(co) === 0)){
                console.log(`Crash at tick ${tick} on track ${c.x},${c.y}`);
                carts = carts.filter(crashed => c.compareXY(crashed) !== 0);
            }
        }
        tick++;
    }
    if(carts.length === 1){
        console.log(`Last cart at tick ${tick} on track ${carts[0].x},${carts[0].y}`);
    }
}

rl.on('line', (line: string) => {
    let trackRow: Track[] = [];
    for(let c of line){
        if(c === '-') trackRow.push(Track.Flat);
        else if(c === '|') trackRow.push(Track.UpDown);
        else if(c === '/') trackRow.push(Track.CurveRight);
        else if(c === '\\') trackRow.push(Track.CurveLeft);
        else if(c === '+') trackRow.push(Track.Intersection);
        else if(c === '<'){
            trackRow.push(Track.Flat);
            carts.push(new Cart(readCartId, Direction.Left, readX, readY));
            readCartId++;
        }else if(c === '>'){
            trackRow.push(Track.Flat);
            carts.push(new Cart(readCartId, Direction.Right, readX, readY));
            readCartId++;
        }else if(c === '^'){
            trackRow.push(Track.UpDown);
            carts.push(new Cart(readCartId, Direction.Up, readX, readY));
            readCartId++;
        }else if(c === 'v'){
            trackRow.push(Track.UpDown);
            carts.push(new Cart(readCartId, Direction.Down, readX, readY));
            readCartId++;
        }else{
            trackRow.push(Track.NoTrack);
        }
        readX++;
    }
    map.push(trackRow);
    trackRow = [];
    readX = 0;
    readY++;
});
rl.on('close', start);

enum Direction{
    Left, Right, Up, Down
}

enum Track{
    Flat, UpDown, CurveRight, CurveLeft, Intersection, NoTrack
}

class Cart{
    id: number;
    dir: Direction;
    x: number;
    y: number;
    turns: Direction[] = [Direction.Left, Direction.Up, Direction.Right];

    constructor(id: number, dir: Direction, x: number, y: number){
        this.id = id;
        this.dir = dir;
        this.x = x;
        this.y = y;
    }

    move(track: Track): void{
        switch(track){
            case Track.Flat:
                if(this.dir === Direction.Left) this.x--;
                if(this.dir === Direction.Right) this.x++;
                break;
            case Track.UpDown:
                if(this.dir === Direction.Up) this.y--;
                if(this.dir === Direction.Down) this.y++;
                break;
            case Track.CurveLeft:
                if(this.dir === Direction.Left) {
                    this.y--;
                    this.dir = Direction.Up;
                    break;
                }
                if(this.dir === Direction.Right){
                    this.y++;
                    this.dir = Direction.Down;
                    break;
                }
                if(this.dir === Direction.Up) {
                    this.x--;
                    this.dir = Direction.Left;
                    break;
                }
                if(this.dir === Direction.Down){
                    this.x++;
                    this.dir = Direction.Right;
                    break;
                }
                break;
            case Track.CurveRight:
                if(this.dir === Direction.Left) {
                    this.y++;
                    this.dir = Direction.Down;
                    break;
                }
                if(this.dir === Direction.Right){
                    this.y--;
                    this.dir = Direction.Up;
                    break;
                }
                if(this.dir === Direction.Up) {
                    this.x++;
                    this.dir = Direction.Right;
                    break;
                }
                if(this.dir === Direction.Down){
                    this.x--;
                    this.dir = Direction.Left;
                    break;
                }
                break;
            case Track.Intersection:
                const first = this.turns[0];
                this.turns.shift();
                this.turns.push(first);
                if(first === Direction.Left){
                    if(this.dir === Direction.Left) {
                        this.y++;
                        this.dir = Direction.Down;
                        break;
                    }
                    if(this.dir === Direction.Up){
                        this.x--;
                        this.dir = Direction.Left;
                        break;
                    }
                    if(this.dir === Direction.Right){
                        this.y--;
                        this.dir = Direction.Up;
                        break;
                    }
                    if(this.dir === Direction.Down){
                        this.x++;
                        this.dir = Direction.Right;
                        break;
                    }
                } 
                if(first === Direction.Up){
                    if(this.dir === Direction.Left) this.x--;
                    if(this.dir === Direction.Up) this.y--;
                    if(this.dir === Direction.Right) this.x++;
                    if(this.dir === Direction.Down) this.y++;
                } 
                if(first === Direction.Right){
                    if(this.dir === Direction.Left){ 
                        this.y--;
                        this.dir = Direction.Up;
                        break;
                    }
                    if(this.dir === Direction.Up){ 
                        this.x++;
                        this.dir = Direction.Right;
                        break;
                    }
                    if(this.dir === Direction.Right){ 
                        this.y++;
                        this.dir = Direction.Down;
                        break;
                    }
                    if(this.dir === Direction.Down){ 
                        this.x--;
                        this.dir = Direction.Left;
                        break;
                    }
                } 
        }
    }

    compareXY(other: Cart): number{
        if(this.y < other.y) return -1;
        if(this.y > other.y) return 1;
        if(this.x < other.x) return -1;
        if(this.x > other.x) return 1;
        return 0;
    }
}