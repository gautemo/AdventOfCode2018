fun main(args: Array<String>) {
    val input = 3031
    var max = PowerSquare(1, 1, 1, input)
    for(i in 1..300){
        for(j in 1..300){
            //max = PowerSquare(i, j, 3, input).largestPower(max)
            val maxSize = if(i > j) 301 - i else 301 - j
            for(k in 1..maxSize) {
                max = PowerSquare(i, j, k, input).largestPower(max)
            }
        }
        println(i)
    }
    println("${max.x},${max.y},${max.size}: ${max.power}")
}

fun powerForCell(x: Int, y: Int, gridSN: Int):Int{
    return ((((x + 10) * y + gridSN) * (x + 10)) %1000 / 100) - 5
}

class PowerSquare(val x:Int, val y:Int, val size: Int, gridSN: Int){
    var power = 0
    init{
        for(i in 0 until size){
            for(j in 0 until size){
                power += powerForCell(x + i, y + j, gridSN)
            }
        }
    }

    fun largestPower(compare: PowerSquare): PowerSquare{
        return if(power > compare.power) this else compare
    }
}
