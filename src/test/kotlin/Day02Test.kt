import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class Day02Test {
    private val sample = listOf(
        "forward 5",
        "down 5",
        "forward 8",
        "up 3",
        "down 8",
        "forward 2"
    )

    private val puzzleInput = readLines(javaClass, "day_02.txt")

    @Test
    fun testSampleInputPart1() {
        val actual = Day02.part1(sample)
        assertEquals(150, actual)
    }

    @Test
    fun testPuzzleInputPart1() {
        val solution = Day02.part1(puzzleInput)
        assertEquals(1_989_014, solution)
    }

    @Test
    fun testSampleInputPart2() {
        val actual = Day02.part2(sample)
        assertEquals(900, actual)
    }

    @Test
    fun testPuzzleInputPart2() {
        val solution = Day02.part2(puzzleInput)
        assertEquals(2_006_917_119, solution)
    }
}