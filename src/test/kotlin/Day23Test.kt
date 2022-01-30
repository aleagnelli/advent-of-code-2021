import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class Day23Test {
    private val sample = """
        #############
        #...........#
        ###B#C#B#D###
          #A#D#C#A#
          #########
    """.trimIndent().lines()

    private val puzzleInput = readLines(javaClass, "day_23.txt")

    @Test
    fun testSampleInputPart1() {
        val actual = Day23.part1(sample)
        assertEquals(12_521, actual)
    }

    @Test
    fun testPuzzleInputPart1() {
        val solution = Day23.part1(puzzleInput)
        assertEquals(11_320, solution)
    }

    @Test
    fun testSampleInputPart2() {
        val actual = Day23.part2(sample)
        assertEquals(44_169, actual)
    }

    @Test
    fun testPuzzleInputPart2() {
        val solution = Day23.part2(puzzleInput)
        assertEquals(49_532, solution)
    }
}
