import Day16.Literal
import Day16.Operator
import Day16.PacketParser
import Day16.hexToBinary
import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class Day16Test {
    private val puzzleInput = readText(javaClass, "day_16.txt")

    @Test
    fun testParser() {
        assertEquals(PacketParser.parse("D2FE28".hexToBinary()).first, Literal(version = 6, type = 4, value = 2021))
        assertEquals(
            PacketParser.parse("38006F45291200".hexToBinary()).first,
            Operator(
                version = 1,
                type = 6,
                subPackets = listOf(
                    Literal(version = 6, type = 4, value = 10),
                    Literal(version = 2, type = 4, value = 20)
                )
            )
        )
        assertEquals(
            PacketParser.parse("EE00D40C823060".hexToBinary()).first,
            Operator(
                version = 7,
                type = 3,
                subPackets = listOf(
                    Literal(version = 2, type = 4, value = 1),
                    Literal(version = 4, type = 4, value = 2),
                    Literal(version = 1, type = 4, value = 3)
                )
            )
        )
    }

    @Test
    fun testSampleInputPart1() {
        assertEquals(16, Day16.part1("8A004A801A8002F478"))
        assertEquals(12, Day16.part1("620080001611562C8802118E34"))
        assertEquals(23, Day16.part1("C0015000016115A2E0802F182340"))
        assertEquals(31, Day16.part1("A0016C880162017C3686B18A3D4780"))
    }

    @Test
    fun testPuzzleInputPart1() {
        val solution = Day16.part1(puzzleInput)
        assertEquals(996, solution)
    }

    @Test
    fun testSampleInputPart2() {
        assertEquals(3, Day16.part2("C200B40A82"))
        assertEquals(54, Day16.part2("04005AC33890"))
        assertEquals(7, Day16.part2("880086C3E88112"))
        assertEquals(9, Day16.part2("CE00C43D881120"))
        assertEquals(1, Day16.part2("D8005AC2A8F0"))
        assertEquals(0, Day16.part2("F600BC2D8F"))
        assertEquals(0, Day16.part2("9C005AC2F8F0"))
        assertEquals(1, Day16.part2("9C0141080250320F1802104A08"))
    }

    @Test
    fun testPuzzleInputPart2() {
        val solution = Day16.part2(puzzleInput)
        assertEquals(96_257_984_154, solution)
    }
}
