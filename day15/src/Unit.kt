enum class Creature(var c: Char) {
    GOBLIN('G'),
    ELF('E')
}

class Unit(var type: Creature, var s:Square, var attack: Int){
    var hp = 200

    fun isNextTo(other: Unit): Boolean{
        return Math.abs(s.x - other.s.x) + Math.abs(s.y - other.s.y) == 1
    }
}