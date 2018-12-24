import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

internal class OpcodesTest{

    @Test
    fun addrTestF(){
        val reg = Register(3, 2, 1, 1)
        val i = Instruction(9,2,1,2)
        Opcodes.addr(reg, i)
        val exp = Register(3,2,2,1)
        assert(!reg.equals(exp))
    }

    @Test
    fun muliTestF(){
        val reg = Register(3, 2, 1, 1)
        val i = Instruction(9,2,1,2)
        Opcodes.muli(reg, i)
        val exp = Register(3,2,2,1)
        assert(!reg.equals(exp))
    }

    @Test
    fun banrTestF(){
        val reg = Register(3, 2, 1, 1)
        val i = Instruction(9,2,1,2)
        Opcodes.banr(reg, i)
        val exp = Register(3,2,2,1)
        assert(!reg.equals(exp))
    }

    @Test
    fun baniTestF(){
        val reg = Register(3, 2, 1, 1)
        val i = Instruction(9,2,1,2)
        Opcodes.bani(reg, i)
        val exp = Register(3,2,2,1)
        assert(!reg.equals(exp))
    }

    @Test
    fun boriTestF(){
        val reg = Register(3, 2, 1, 1)
        val i = Instruction(9,2,1,2)
        Opcodes.bori(reg, i)
        val exp = Register(3,2,2,1)
        assert(!reg.equals(exp))
    }

    @Test
    fun borrTestF(){
        val reg = Register(3, 2, 1, 1)
        val i = Instruction(9,2,1,2)
        Opcodes.borr(reg, i)
        val exp = Register(3,2,2,1)
        assert(!reg.equals(exp))
    }

    @Test
    fun setrTestF(){
        val reg = Register(3, 2, 1, 1)
        val i = Instruction(9,2,1,2)
        Opcodes.setr(reg, i)
        val exp = Register(3,2,2,1)
        assert(!reg.equals(exp))
    }

    @Test
    fun gtirTestF(){
        val reg = Register(3, 2, 1, 1)
        val i = Instruction(9,2,1,2)
        Opcodes.gtir(reg, i)
        val exp = Register(3,2,2,1)
        assert(!reg.equals(exp))
    }

    @Test
    fun gtriTestF(){
        val reg = Register(3, 2, 1, 1)
        val i = Instruction(9,2,1,2)
        Opcodes.gtri(reg, i)
        val exp = Register(3,2,2,1)
        assert(!reg.equals(exp))
    }

    @Test
    fun gtrrTestF(){
        val reg = Register(3, 2, 1, 1)
        val i = Instruction(9,2,1,2)
        Opcodes.gtrr(reg, i)
        val exp = Register(3,2,2,1)
        assert(!reg.equals(exp))
    }

    @Test
    fun eqirTestF(){
        val reg = Register(3, 2, 1, 1)
        val i = Instruction(9,2,1,2)
        Opcodes.eqir(reg, i)
        val exp = Register(3,2,2,1)
        assert(!reg.equals(exp))
    }

    @Test
    fun eqriTestF(){
        val reg = Register(3, 2, 1, 1)
        val i = Instruction(9,2,1,2)
        Opcodes.eqri(reg, i)
        val exp = Register(3,2,2,1)
        assert(!reg.equals(exp))
    }

    @Test
    fun eqrrTestF(){
        val reg = Register(3, 2, 1, 1)
        val i = Instruction(9,2,1,2)
        Opcodes.eqrr(reg, i)
        val exp = Register(3,2,2,1)
        assert(!reg.equals(exp))
    }

    @Test
    fun mulrTest(){
        val reg = Register(3, 2, 1, 1)
        val i = Instruction(9,2,1,2)
        Opcodes.mulr(reg, i)
        val exp = Register(3,2,2,1)
        assert(reg.equals(exp))
    }

    @Test
    fun addiTest(){
        val reg = Register(3, 2, 1, 1)
        val i = Instruction(9,2,1,2)
        Opcodes.addi(reg, i)
        val exp = Register(3,2,2,1)
        assert(reg.equals(exp))
    }

    @Test
    fun setiTest(){
        val reg = Register(3, 2, 1, 1)
        val i = Instruction(9,2,1,2)
        Opcodes.seti(reg, i)
        val exp = Register(3,2,2,1)
        assert(reg.equals(exp))
    }


    @Test
    fun addrTest(){
        val reg = Register(3, 2, 1, 1)
        val i = Instruction(9,2,1,2)
        Opcodes.addr(reg, i)
        val exp = Register(3,2,3,1)
        assertArrayEquals(exp.data, reg.data)
    }

    @Test
    fun muliTest(){
        val reg = Register(3, 2, 1, 1)
        val i = Instruction(9,2,1,2)
        Opcodes.muli(reg, i)
        val exp = Register(3,2,1,1)
        assertArrayEquals(exp.data, reg.data)
    }

    @Test
    fun banrTest(){
        val reg = Register(3, 2, 1, 1)
        val i = Instruction(9,2,1,2)
        Opcodes.banr(reg, i)
        val exp = Register(3,2,0,1)
        assertArrayEquals(exp.data, reg.data)
    }

    @Test
    fun baniTest(){
        val reg = Register(3, 2, 1, 1)
        val i = Instruction(9,2,1,2)
        Opcodes.bani(reg, i)
        val exp = Register(3,2,1,1)
        assertArrayEquals(exp.data, reg.data)
    }

    @Test
    fun boriTest(){
        val reg = Register(3, 2, 1, 1)
        val i = Instruction(9,2,1,2)
        Opcodes.bori(reg, i)
        val exp = Register(3,2,1,1)
        assertArrayEquals(exp.data, reg.data)
    }

    @Test
    fun borrTest(){
        val reg = Register(3, 2, 1, 1)
        val i = Instruction(9,2,1,2)
        Opcodes.borr(reg, i)
        val exp = Register(3,2,3,1)
        assertArrayEquals(exp.data, reg.data)
    }

    @Test
    fun setrTest(){
        val reg = Register(3, 2, 1, 1)
        val i = Instruction(9,2,1,2)
        Opcodes.setr(reg, i)
        val exp = Register(3,2,1,1)
        assertArrayEquals(exp.data, reg.data)
    }

    @Test
    fun gtirTest(){
        val reg = Register(3, 2, 1, 1)
        val i = Instruction(9,2,1,2)
        Opcodes.gtir(reg, i)
        val exp = Register(3,2,0,1)
        assertArrayEquals(exp.data, reg.data)
    }

    @Test
    fun gtriTest(){
        val reg = Register(3, 2, 1, 1)
        val i = Instruction(9,2,1,2)
        Opcodes.gtri(reg, i)
        val exp = Register(3,2,0,1)
        assertArrayEquals(exp.data, reg.data)
    }

    @Test
    fun gtrrTest(){
        val reg = Register(3, 2, 1, 1)
        val i = Instruction(9,2,1,2)
        Opcodes.gtrr(reg, i)
        val exp = Register(3,2,0,1)
        assertArrayEquals(exp.data, reg.data)
    }

    @Test
    fun eqirTest(){
        val reg = Register(3, 2, 1, 1)
        val i = Instruction(9,2,1,2)
        Opcodes.eqir(reg, i)
        val exp = Register(3,2,1,1)
        assertArrayEquals(exp.data, reg.data)
    }

    @Test
    fun eqriTest(){
        val reg = Register(3, 2, 1, 1)
        val i = Instruction(9,2,1,2)
        Opcodes.eqri(reg, i)
        val exp = Register(3,2,1,1)
        assertArrayEquals(exp.data, reg.data)
    }

    @Test
    fun eqrrTest(){
        val reg = Register(3, 2, 1, 1)
        val i = Instruction(9,2,1,2)
        Opcodes.eqrr(reg, i)
        val exp = Register(3,2,0,1)
        assertArrayEquals(exp.data, reg.data)
    }

}