import java.io.File

class Cave{
    var map: Array<CharArray> = emptyArray()
    var units = mutableListOf<Unit>()

    init {
        readMap(0)
    }

    private fun restart(version: Int){
        units = mutableListOf()
        map = emptyArray()
        readMap(version)
    }

    private fun readMap(version: Int){
        val lines = File("input.txt").readLines()
        lines.forEach{map += it.toCharArray()}

        for(i in 0 until map.size){
            for(j in 0 until map[i].size){
                if(map[i][j] == 'E') units.add(Unit(Creature.ELF, Square(j, i), 3 + version))
                else if(map[i][j] == 'G') units.add(Unit(Creature.GOBLIN, Square(j, i), 3))
            }
        }
    }

    private fun twoTeamLeft():Boolean{
        return !units.all{ it.type == units[0].type }
    }

    fun startFight(){
        var rounds = 0
        while (twoTeamLeft()){
            rounds++
            doRound()
            printmap(rounds)
        }
        rounds--
        val hpLeft = units.sumBy { it.hp }
        println("Outcome $rounds * $hpLeft = ${rounds * hpLeft}")
    }

    fun startCheatFight(){
        var rounds = 0
        var version = 1
        val startingElfs = units.filter { it.type == Creature.ELF }.size
        do {
            while (twoTeamLeft()) {
                rounds++
                doRound()
            }
            rounds--
            if(units[0].type == Creature.ELF && units.size == startingElfs) {
                println("All elfs live and won at round $rounds with attack ${3 + version}: ${rounds * (version + 3)}")
                val hpLeft = units.sumBy { it.hp }
                println("Outcome $rounds * $hpLeft = ${rounds * hpLeft}")
            }else{
                println("Version $version failed. Restarting system v.${version+1}")
                println("${startingElfs - units.size} elves died")
                version++
                rounds = 0
                restart(version)
            }
        }while(rounds == 0)
    }

    private fun printmap(round: Int){
        println("Round $round")
        for(r in map){
            for(c in r) print(c)
            println()
        }
    }

    private fun doRound() {
        units.sortWith(compareBy( { it.s.y}, {it.s.x }))
        for(u in units) if(u.hp > 0) unitRound(u)
        units.removeIf { it.hp <= 0 }
    }

    private fun unitRound(u: Unit){
        val targets = units.filter { unit -> unit.type != u.type && unit.hp > 0 }.toMutableList()
        if(!targets.any { it.isNextTo(u) }) {
            var attackSquares = mutableListOf<Square>()
            for(t in targets){
                attackSquares.addAll(0, t.s.getNeighbours(map).toList())
            }
            attackSquares = attackSquares.distinctBy{ Pair(it.x, it.y)}.toMutableList()
            var goTowards = nearestSquare(u.s, attackSquares)
            while (true) {
                if(goTowards == null){
                    break
                }else {
                    if (u.s.getNeighbours(map).any { square -> square.x == goTowards!!.x && square.y == goTowards!!.y }) {
                        map[u.s.y][u.s.x] = '.'
                        map[goTowards.y][goTowards.x] = u.type.c
                        u.s = goTowards
                        break
                    }
                    goTowards = nearestSquare(u.s, goTowards.getNeighbours(map).toList())
                }
            }
        }
        if(targets.any { it.isNextTo(u) }) {
            targets.removeIf { !it.isNextTo(u) }
            targets.sortWith(compareBy({it.hp}, {it.s.y}, {it.s.x}))
            targets[0].hp -= u.attack
            if(targets[0].hp <= 0){
                map[targets[0].s.y][targets[0].s.x] = '.'
            }
        }
    }

    private fun nearestSquare(startSquare: Square, possibleSquares: List<Square>):Square?{
        var opened = arrayOf(startSquare)
        var openNext = mutableListOf(startSquare)
        while (openNext.isNotEmpty()){
            var neighbours = openNeighbours(openNext).toMutableList()
            opened += openNext
            neighbours.removeIf { n -> opened.any{it.equals(n)} }
            openNext = neighbours
            neighbours = neighbours.filter { n -> possibleSquares.any { p -> p.equals(n) } }.toMutableList()
            if(neighbours.isNotEmpty()){
                neighbours.sortWith(compareBy({it.y}, {it.x}))
                return neighbours[0]
            }
        }
        return null
    }

    private fun openNeighbours(squares: List<Square>):List<Square>{
        var neighbours = emptyArray<Square>()
        for(s in squares){
            neighbours += s.getNeighbours(map)
        }
        return neighbours.distinctBy { Pair(it.x, it.y) }
    }
}

fun main(){
    val cave = Cave()
    //cave.startFight()
    cave.startCheatFight()
}