import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class Day15Test {
    private val sample = """
        1163751742
        1381373672
        2136511328
        3694931569
        7463417111
        1319128137
        1359912421
        3125421639
        1293138521
        2311944581
    """.trimIndent()

    private val puzzleInput = readText(javaClass, "day_15.txt")

    @Test
    fun testSampleInputPart1() {
        val actual = Day15.part1(sample)
        assertEquals(40, actual)
    }

    @Test
    fun testPuzzleInputPart1() {
        val solution = Day15.part1(puzzleInput)
        assertEquals(698, solution)
    }

    @Test
    fun testSampleInputPart2() {
        val actual = Day15.part2(sample)
        assertEquals(315, actual)
    }

    @Test
    fun testPuzzleInputPart2() {
        val solution = Day15.part2(puzzleInput)
        assertEquals(3022, solution)
    }
}
