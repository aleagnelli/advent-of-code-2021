import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class Day05Test {
    private val sample = listOf(
        "0,9 -> 5,9",
        "8,0 -> 0,8",
        "9,4 -> 3,4",
        "2,2 -> 2,1",
        "7,0 -> 7,4",
        "6,4 -> 2,0",
        "0,9 -> 2,9",
        "3,4 -> 1,4",
        "0,0 -> 8,8",
        "5,5 -> 8,2"
    )

    private val puzzleInput = readLines(javaClass, "day_05.txt")

    @Test
    fun testSampleInputPart1() {
        val actual = Day05.part1(sample)
        assertEquals(5, actual)
    }

    @Test
    fun testPuzzleInputPart1() {
        val solution = Day05.part1(puzzleInput)
        assertEquals(6666, solution)
    }

    @Test
    fun testSampleInputPart2() {
        val actual = Day05.part2(sample)
        assertEquals(12, actual)
    }

    @Test
    fun testPuzzleInputPart2() {
        val solution = Day05.part2(puzzleInput)
        assertEquals(19081, solution)
    }
}
