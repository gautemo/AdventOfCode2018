import java.io.File

fun main() {
    var polymers = File("input.txt").readText().trim()
    val task1 = fullyReact(polymers)
    println("Result: $task1")
    println("Length: ${task1.length}")
    println()
    println("Shortest length when with removed unit: ${shortestWhenRemoveUnit(polymers)}")
}

fun react(polymers: String): String{
    for(i in 0 until polymers.length - 1){
        if(isTriggered(polymers[i], polymers[i+1])){
            return polymers.removeRange(i, i+2)
        }
    }
    return polymers
}

fun isTriggered(a: Char, b: Char): Boolean{
    return (a.isLowerCase() && b.isUpperCase() && a == b.toLowerCase()) || (a.isUpperCase() && b.isLowerCase() && a.toLowerCase() == b)
}

fun fullyReact(polymers: String): String{
    var polys = polymers
    do{
        val lastLength = polys.length
        polys = react(polys)
    }while (polys.length != lastLength)
    return polys
}

fun shortestWhenRemoveUnit(polymers: String): Int{
    val unique = polymers.toLowerCase().toCharArray().distinct()
    var shortest = polymers.length
    for(char in unique){
        val removedUnit = polymers.replace(Regex("$char*${char.toUpperCase()}*"), "")
        val length = fullyReact(removedUnit).length
        if(length < shortest){
            shortest = length
        }
    }
    return shortest
}