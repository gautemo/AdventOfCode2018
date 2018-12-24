class Opcodes{

    companion object {
        fun general(r: Register, i: Instruction): Register {
            return when(i.opcode){
                0 -> bani(r, i)
                1 -> addr(r, i)
                2 -> mulr(r, i)
                3 -> addi(r, i)
                4 -> gtri(r, i)
                5 -> banr(r, i)
                6 -> borr(r, i)
                7 -> eqri(r, i)
                8 -> seti(r, i)
                9 -> eqrr(r, i)
                10 -> bori(r, i)
                11 -> setr(r, i)
                12 -> eqir(r, i)
                13 -> muli(r, i)
                14 -> gtrr(r, i)
                15 -> gtir(r, i)
                else -> addr(r, i)
            }
        }

        fun addr(r: Register, i: Instruction): Register {
            r.data[i.output] = r.data[i.i1] + r.data[i.i2]
            return r
        }

        fun addi(r: Register, i: Instruction): Register {
            r.data[i.output] = r.data[i.i1] + i.i2
            return r
        }

        fun mulr(r: Register, i: Instruction): Register {
            r.data[i.output] = r.data[i.i1] * r.data[i.i2]
            return r
        }

        fun muli(r: Register, i: Instruction): Register {
            r.data[i.output] = r.data[i.i1] * i.i2
            return r
        }

        fun banr(r: Register, i: Instruction): Register {
            r.data[i.output] = r.data[i.i1] and r.data[i.i2]
            return r
        }

        fun bani(r: Register, i: Instruction): Register {
            r.data[i.output] = r.data[i.i1] and i.i2
            return r
        }

        fun borr(r: Register, i: Instruction): Register {
            r.data[i.output] = r.data[i.i1] or r.data[i.i2]
            return r
        }

        fun bori(r: Register, i: Instruction): Register {
            r.data[i.output] = r.data[i.i1] or i.i2
            return r
        }

        fun setr(r: Register, i: Instruction): Register {
            r.data[i.output] = r.data[i.i1]
            return r
        }

        fun seti(r: Register, i: Instruction): Register {
            r.data[i.output] = i.i1
            return r
        }

        fun gtir(r: Register, i: Instruction): Register {
            r.data[i.output] = i.i1.compareTo(r.data[i.i2])
            if(r.data[i.output] < 0) r.data[i.output] = 0
            return r
        }

        fun gtri(r: Register, i: Instruction): Register {
            r.data[i.output] = r.data[i.i1].compareTo(i.i2)
            if(r.data[i.output] < 0) r.data[i.output] = 0
            return r
        }

        fun gtrr(r: Register, i: Instruction): Register {
            r.data[i.output] = r.data[i.i1].compareTo(r.data[i.i2])
            if(r.data[i.output] < 0) r.data[i.output] = 0
            return r
        }

        fun eqir(r: Register, i: Instruction): Register {
            r.data[i.output] = if(i.i1 == r.data[i.i2]) 1 else 0
            return r
        }

        fun eqri(r: Register, i: Instruction): Register {
            r.data[i.output] = if(r.data[i.i1] == i.i2) 1 else 0
            return r
        }

        fun eqrr(r: Register, i: Instruction): Register {
            r.data[i.output] = if(r.data[i.i1] == r.data[i.i2]) 1 else 0
            return r
        }
    }
}