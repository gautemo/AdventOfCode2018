import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*

internal class MainKtTest {

    @Test
    fun powerForCell() {
        assertEquals(4, powerForCell(3, 5, 8))
        assertEquals(-5, powerForCell(122,79,57))
        assertEquals(0, powerForCell(217, 196,39))
        assertEquals(4, powerForCell(101,153,71))
    }

    @Test
    fun powerForGrid() {
        assertEquals(29, PowerSquare(33,45, 3,18).power)
        assertEquals(30, PowerSquare(21,61, 3,42).power)
        assertEquals(113, PowerSquare(90,269, 16,18).power)
        assertEquals(119, PowerSquare(232,251, 12,42).power)
    }
}
