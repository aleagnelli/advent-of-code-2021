import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class Day03Test {
    private val sample = listOf(
        "00100",
        "11110",
        "10110",
        "10111",
        "10101",
        "01111",
        "00111",
        "11100",
        "10000",
        "11001",
        "00010",
        "01010"
    )

    private val puzzleInput = readLines(javaClass, "day_03.txt")

    @Test
    fun testSampleInputPart1() {
        val actual = Day03.part1(sample)
        assertEquals(198, actual)
    }

    @Test
    fun testPuzzleInputPart1() {
        val solution = Day03.part1(puzzleInput)
        assertEquals(841_526, solution)
    }

    @Test
    fun testSampleInputPart2() {
        val actual = Day03.part2(sample)
        assertEquals(230, actual)
    }

    @Test
    fun testPuzzleInputPart2() {
        val solution = Day03.part2(puzzleInput)
        assertEquals(4_790_390, solution)
    }
}