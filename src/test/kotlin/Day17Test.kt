import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class Day17Test {
    private val sample = "target area: x=20..30, y=-10..-5"

    private val puzzleInput = "target area: x=81..129, y=-150..-108"

    @Test
    fun testSampleInputPart1() {
        val actual = Day17.part1(sample)
        assertEquals(45, actual)
    }

    @Test
    fun testPuzzleInputPart1() {
        val solution = Day17.part1(puzzleInput)
        assertEquals(11_175, solution)
    }

    @Test
    fun testSampleInputPart2() {
        val actual = Day17.part2(sample)
        assertEquals(112, actual)
    }

    @Test
    fun testPuzzleInputPart2() {
        val solution = Day17.part2(puzzleInput)
        assertEquals(3540, solution)
    }
}
