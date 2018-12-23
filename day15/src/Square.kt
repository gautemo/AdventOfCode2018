class Square(var x: Int, var y: Int){
    fun getNeighbours(map: Array<CharArray>):Array<Square>{
        val squares = arrayOf(Square(x-1, y),Square(x+1, y),Square(x, y-1),Square(x, y+1))
        return squares.filter { s ->
            map[s.y][s.x] == '.'
                    && s.x in 0..(map[0].size - 1)
                    && s.y in 0..(map.size -1)
        }.toTypedArray()
    }

    fun equals(other: Square):Boolean{
        return x == other.x && y == other.y
    }
}