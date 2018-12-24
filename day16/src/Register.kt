class Register(r0: Int, r1: Int, r2: Int, r3: Int){
    var data = Array(4) {0}

    init{
        data[0] = r0
        data[1] = r1
        data[2] = r2
        data[3] = r3
    }

    fun equals(other:Register):Boolean{
        return data.contentEquals(other.data)
    }

    fun copy(): Register{
        return Register(data[0], data[1], data[2], data[3])
    }
}