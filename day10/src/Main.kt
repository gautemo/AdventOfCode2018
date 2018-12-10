import java.io.File

fun main(args: Array<String>) {
    var lines = File("input.txt").readLines()
    var lights: MutableList<Light> = mutableListOf()
    var lowX = 0
    var highX = 0
    var lowY = 0
    var highY = 0
    lines.forEach { l ->
        var values = Regex("-?\\d+").findAll(l)
        var x = values.elementAt(0).value.toInt()
        var y = values.elementAt(1).value.toInt()
        if(x < lowX) lowX = x
        if(y < lowY) lowY = y
        if(x > highX) highX = x
        if(y > highY) highY = y
        lights.add(Light(x, y, values.elementAt(2).value.toInt(), values.elementAt(3).value.toInt()))
    }
    for(i in 0..4){
        print(lights, lowX, lowY, highX, highY)
        lights.forEach{ it.move()}
    }
}

fun print(lights: List<Light>, startX: Int, startY: Int, endX: Int, endY: Int){
    for(i in startY..endY){
        x@ for(j in startX..endX){
            for(l in lights){
                if(l.isHere(j, i)){
                    print('#')
                    continue@x
                }
            }
            print('.')
        }
        println()
    }
    println()
}

class Light(var x: Int, var y: Int, val velX: Int, val velY: Int){
    fun move(){
        this.x += this.velX
        this.y += this.velY
    }

    fun isHere(x: Int, y:Int):Boolean{
        return this.x == x && this.y == y
    }
}