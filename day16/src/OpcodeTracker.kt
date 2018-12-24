class OpcodeTracker{
    var addr = mutableSetOf<Int>()
    var addi = mutableSetOf<Int>()
    var muli = mutableSetOf<Int>()
    var mulr = mutableSetOf<Int>()
    var bani = mutableSetOf<Int>()
    var banr = mutableSetOf<Int>()
    var bori = mutableSetOf<Int>()
    var borr = mutableSetOf<Int>()
    var setr = mutableSetOf<Int>()
    var seti = mutableSetOf<Int>()
    var gtir = mutableSetOf<Int>()
    var gtri = mutableSetOf<Int>()
    var gtrr = mutableSetOf<Int>()
    var eqir = mutableSetOf<Int>()
    var eqri = mutableSetOf<Int>()
    var eqrr = mutableSetOf<Int>()

    fun printValues(clean: Boolean){
        if(clean)clean()
        println("addr: " + addr.joinToString())
        println("addi: " + addi.joinToString())
        println("muli: " + muli.joinToString())
        println("mulr: " + mulr.joinToString())
        println("bani: " + bani.joinToString())
        println("banr: " + banr.joinToString())
        println("bori: " + bori.joinToString())
        println("borr: " + borr.joinToString())
        println("setr: " + setr.joinToString())
        println("seti: " + seti.joinToString())
        println("gtir: " + gtir.joinToString())
        println("gtri: " + gtri.joinToString())
        println("gtrr: " + gtrr.joinToString())
        println("eqir: " + eqir.joinToString())
        println("eqri: " + eqri.joinToString())
        println("eqrr: " + eqrr.joinToString())
    }

    fun clean(){
        val alls = mutableListOf(addr, addi, muli, mulr, bani, banr, bori, borr, setr, seti, gtir, gtri, gtrr, eqir, eqri, eqrr)
        while(!alls.all{it.size == 1}){
            for(li in alls){
                if(li.size == 1){
                    for(rli in alls){
                        if(li != rli) rli.removeIf { it == li.first() }
                    }
                    continue
                }
                for(nr in li){
                    val others = alls.filter { it != li }
                    if(others.all{!it.contains(nr)}){
                        li.removeIf { it != nr }
                        break
                    }
                }
            }
        }
    }
}