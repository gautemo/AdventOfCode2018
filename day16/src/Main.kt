import java.io.File

fun main(){
    val lines = File("input.txt").readLines()
    var countAsOpcodes = 0
    val regRegex = Regex("\\d+,\\s\\d+,\\s\\d+,\\s\\d+")
    for(i in 0 until lines.size step 4){
        val n1 = regRegex.find(lines[i])!!.value.split(", ")
        val beforeReg = Register(n1[0].toInt(), n1[1].toInt(), n1[2].toInt(), n1[3].toInt())
        val nInstr = lines[i+1].split(" ")
        val instr = Instruction(nInstr[0].toInt(), nInstr[1].toInt(), nInstr[2].toInt(), nInstr[3].toInt())
        val n2 = regRegex.find(lines[i+2])!!.value.split(", ")
        val afterReg = Register(n2[0].toInt(), n2[1].toInt(), n2[2].toInt(), n2[3].toInt())
        if(matches3orMoreOpcodes(beforeReg, instr, afterReg)) countAsOpcodes++
    }
    println("Count: $countAsOpcodes")

    //Task2 find matches
    val ot = OpcodeTracker()
    for(i in 0 until lines.size step 4){
        val n1 = regRegex.find(lines[i])!!.value.split(", ")
        val beforeReg = Register(n1[0].toInt(), n1[1].toInt(), n1[2].toInt(), n1[3].toInt())
        val nInstr = lines[i+1].split(" ")
        val instr = Instruction(nInstr[0].toInt(), nInstr[1].toInt(), nInstr[2].toInt(), nInstr[3].toInt())
        val n2 = regRegex.find(lines[i+2])!!.value.split(", ")
        val afterReg = Register(n2[0].toInt(), n2[1].toInt(), n2[2].toInt(), n2[3].toInt())
        findMatch(beforeReg, instr, afterReg, ot)
    }
    println("Update Opcodes.general with these values")
    ot.printValues(true)

    //Task2 solve
    val testLines = File("inputTest.txt").readLines()
    var reg = Register(0,0,0,0)
    for(l in testLines){
        val nInstr = l.split(" ")
        val instr = Instruction(nInstr[0].toInt(), nInstr[1].toInt(), nInstr[2].toInt(), nInstr[3].toInt())
        reg = Opcodes.general(reg, instr)
    }
    println("Register 0 is ${reg.data[0]}")
}

fun matches3orMoreOpcodes(startR: Register, instr: Instruction, endR: Register): Boolean{
    var matches = 0
    if(Opcodes.addi(startR.copy(), instr).equals(endR)) matches++
    if(Opcodes.addr(startR.copy(), instr).equals(endR)) matches++
    if(Opcodes.muli(startR.copy(), instr).equals(endR)) matches++
    if(Opcodes.mulr(startR.copy(), instr).equals(endR)) matches++
    if(Opcodes.bani(startR.copy(), instr).equals(endR)) matches++
    if(Opcodes.banr(startR.copy(), instr).equals(endR)) matches++
    if(Opcodes.bori(startR.copy(), instr).equals(endR)) matches++
    if(Opcodes.borr(startR.copy(), instr).equals(endR)) matches++
    if(Opcodes.setr(startR.copy(), instr).equals(endR)) matches++
    if(Opcodes.seti(startR.copy(), instr).equals(endR)) matches++
    if(Opcodes.gtir(startR.copy(), instr).equals(endR)) matches++
    if(Opcodes.gtri(startR.copy(), instr).equals(endR)) matches++
    if(Opcodes.gtrr(startR.copy(), instr).equals(endR)) matches++
    if(Opcodes.eqir(startR.copy(), instr).equals(endR)) matches++
    if(Opcodes.eqri(startR.copy(), instr).equals(endR)) matches++
    if(Opcodes.eqrr(startR.copy(), instr).equals(endR)) matches++
    return matches >= 3
}

fun findMatch(startR: Register, instr: Instruction, endR: Register, tr: OpcodeTracker){
    if(Opcodes.addi(startR.copy(), instr).equals(endR)) tr.addi.add(instr.opcode)
    if(Opcodes.addr(startR.copy(), instr).equals(endR)) tr.addr.add(instr.opcode)
    if(Opcodes.muli(startR.copy(), instr).equals(endR)) tr.muli.add(instr.opcode)
    if(Opcodes.mulr(startR.copy(), instr).equals(endR)) tr.mulr.add(instr.opcode)
    if(Opcodes.bani(startR.copy(), instr).equals(endR)) tr.bani.add(instr.opcode)
    if(Opcodes.banr(startR.copy(), instr).equals(endR)) tr.banr.add(instr.opcode)
    if(Opcodes.bori(startR.copy(), instr).equals(endR)) tr.bori.add(instr.opcode)
    if(Opcodes.borr(startR.copy(), instr).equals(endR)) tr.borr.add(instr.opcode)
    if(Opcodes.setr(startR.copy(), instr).equals(endR)) tr.setr.add(instr.opcode)
    if(Opcodes.seti(startR.copy(), instr).equals(endR)) tr.seti.add(instr.opcode)
    if(Opcodes.gtir(startR.copy(), instr).equals(endR)) tr.gtir.add(instr.opcode)
    if(Opcodes.gtri(startR.copy(), instr).equals(endR)) tr.gtri.add(instr.opcode)
    if(Opcodes.gtrr(startR.copy(), instr).equals(endR)) tr.gtrr.add(instr.opcode)
    if(Opcodes.eqir(startR.copy(), instr).equals(endR)) tr.eqir.add(instr.opcode)
    if(Opcodes.eqri(startR.copy(), instr).equals(endR)) tr.eqri.add(instr.opcode)
    if(Opcodes.eqrr(startR.copy(), instr).equals(endR)) tr.eqrr.add(instr.opcode)
}