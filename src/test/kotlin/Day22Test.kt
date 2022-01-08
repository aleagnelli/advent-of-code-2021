import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class Day22Test {
    private val sample = readLines(javaClass, "day_22_sample.txt")
    private val sample2 = readLines(javaClass, "day_22_sample2.txt")
    private val sample3 = readLines(javaClass, "day_22_sample3.txt")

    private val puzzleInput = readLines(javaClass, "day_22.txt")

    @Test
    fun testSampleInputPart1() {
        assertEquals(39, Day22.part1(sample))
        assertEquals(590_784, Day22.part1(sample2))
    }

    @Test
    fun testPuzzleInputPart1() {
        val solution = Day22.part1(puzzleInput)
        assertEquals(623_748, solution)
    }

    @Test
    fun testSampleInputPart2() {
        val actual = Day22.part2(sample3)
        assertEquals(2_758_514_936_282_235, actual)
    }

    @Test
    fun testPuzzleInputPart2() {
        val solution = Day22.part2(puzzleInput)
        assertEquals(1_227_345_351_869_476, solution)
    }
}
