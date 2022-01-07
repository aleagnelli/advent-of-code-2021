import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class Day21Test {
    private val sample = """
        Player 1 starting position: 4
        Player 2 starting position: 8
    """.trimIndent().lines()

    private val puzzleInput = readLines(javaClass, "day_21.txt")

    @Test
    fun testSampleInputPart1() {
        val actual = Day21.part1(sample)
        assertEquals(739_785, actual)
    }

    @Test
    fun testPuzzleInputPart1() {
        val solution = Day21.part1(puzzleInput)
        assertEquals(1_067_724, solution)
    }

    @Test
    fun testSampleInputPart2() {
        val actual = Day21.part2(sample)
        assertEquals(444_356_092_776_315, actual)
    }

    @Test
    fun testPuzzleInputPart2() {
        val solution = Day21.part2(puzzleInput)
        assertEquals(630_947_104_784_464, solution)
    }
}
