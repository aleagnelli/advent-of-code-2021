import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class Day07Test {
    private val sample = "16,1,2,0,4,2,7,1,2,14"

    private val puzzleInput = readText(javaClass, "day_07.txt")

    @Test
    fun testSampleInputPart1() {
        val actual = Day07.part1(sample)
        assertEquals(37, actual)
    }

    @Test
    fun testPuzzleInputPart1() {
        val solution = Day07.part1(puzzleInput)
        assertEquals(342_641, solution)
    }

    @Test
    fun testSampleInputPart2() {
        val actual = Day07.part2(sample)
        assertEquals(168, actual)
    }

    @Test
    fun testPuzzleInputPart2() {
        val solution = Day07.part2(puzzleInput)
        assertEquals(93_006_301, solution)
    }
}
