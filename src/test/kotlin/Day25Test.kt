import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class Day25Test {
    private val sample = """
        v...>>.vv>
        .vv>>.vv..
        >>.>v>...v
        >>v>>.>.v.
        v>v.vv.v..
        >.>>..v...
        .vv..>.>v.
        v.v..>>v.v
        ....v..v.>
    """.trimIndent()

    private val puzzleInput = readText(javaClass, "day_25.txt")

    @Test
    fun testSampleInputPart1() {
        val actual = Day25.part1(sample)
        assertEquals(58, actual)
    }

    @Test
    fun testPuzzleInputPart1() {
        val solution = Day25.part1(puzzleInput)
        assertEquals(530, solution)
    }
}
