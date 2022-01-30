import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class Day24Test {
    private val puzzleInput = readLines(javaClass, "day_24.txt")

    @Test
    fun testPuzzleInputPart1() {
        val solution = Day24.part1(puzzleInput)
        assertEquals(99_799_212_949_967, solution)
    }

    @Test
    fun testPuzzleInputPart2() {
        val solution = Day24.part2(puzzleInput)
        assertEquals(34_198_111_816_311, solution)
    }
}
