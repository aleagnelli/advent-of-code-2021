import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class Day09Test {
    private val sample = """
        2199943210
        3987894921
        9856789892
        8767896789
        9899965678
    """.trimIndent().lines()

    private val puzzleInput = readLines(javaClass, "day_09.txt")

    @Test
    fun testSampleInputPart1() {
        val actual = Day09.part1(sample)
        assertEquals(15, actual)
    }

    @Test
    fun testPuzzleInputPart1() {
        val solution = Day09.part1(puzzleInput)
        assertEquals(494, solution)
    }

    @Test
    fun testSampleInputPart2() {
        val actual = Day09.part2(sample)
        assertEquals(1134, actual)
    }

    @Test
    fun testPuzzleInputPart2() {
        val solution = Day09.part2(puzzleInput)
        assertEquals(1_048_128, solution)
    }
}
