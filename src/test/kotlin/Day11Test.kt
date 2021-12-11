import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class Day11Test {
    private val sample = """
        5483143223
        2745854711
        5264556173
        6141336146
        6357385478
        4167524645
        2176841721
        6882881134
        4846848554
        5283751526
    """.trimIndent().lines()

    private val puzzleInput = readLines(javaClass, "day_11.txt")

    @Test
    fun testSampleInputPart1() {
        val actual = Day11.part1(sample)
        assertEquals(1656, actual)
    }

    @Test
    fun testPuzzleInputPart1() {
        val solution = Day11.part1(puzzleInput)
        assertEquals(1644, solution)
    }

    @Test
    fun testSampleInputPart2() {
        val actual = Day11.part2(sample)
        assertEquals(195, actual)
    }

    @Test
    fun testPuzzleInputPart2() {
        val solution = Day11.part2(puzzleInput)
        assertEquals(229, solution)
    }
}
