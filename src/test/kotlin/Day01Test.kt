import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class Day01Test {
    private val sample = listOf("199", "200", "208", "210", "200", "207", "240", "269", "260", "263")

    private val puzzleInput = readLines(javaClass, "day_01.txt")

    @Test
    fun testSampleInputPart1() {
        val actual = Day01.part1(sample)
        assertEquals(7, actual)
    }

    @Test
    fun testPuzzleInputPart1() {
        val solution = Day01.part1(puzzleInput)
        assertEquals(1228, solution)
    }

    @Test
    fun testSampleInputPart2() {
        val actual = Day01.part2(sample)
        assertEquals(5, actual)
    }

    @Test
    fun testPuzzleInputPart2() {
        val solution = Day01.part2(puzzleInput)
        assertEquals(1257, solution)
    }
}