import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class Day06Test {
    private val sample = "3,4,3,1,2"

    private val puzzleInput = readText(javaClass, "day_06.txt")

    @Test
    fun testSampleInputPart1() {
        val actual = Day06.part1(sample)
        assertEquals(5934, actual)
    }

    @Test
    fun testPuzzleInputPart1() {
        val solution = Day06.part1(puzzleInput)
        assertEquals(389_726, solution)
    }

    @Test
    fun testSampleInputPart2() {
        val actual = Day06.part2(sample)
        assertEquals(26_984_457_539L, actual)
    }

    @Test
    fun testPuzzleInputPart2() {
        val solution = Day06.part2(puzzleInput)
        assertEquals(1_743_335_992_042, solution)
    }
}
