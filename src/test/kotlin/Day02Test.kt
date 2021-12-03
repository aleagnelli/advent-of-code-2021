import org.junit.jupiter.api.Test
import java.io.File
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

    private val puzzleInput = run {
        val url = javaClass.getResource("day_02.txt") ?: error("input file not found")
        File(url.toURI()).readLines()
    }

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