import org.junit.jupiter.api.Test
import java.io.File
import kotlin.test.assertEquals

class Day01Test {

    @Test
    fun testSampleInputPart1() {
        val input = listOf("199", "200", "208", "210", "200", "207", "240", "269", "260", "263")
        val actual = Day01.part1(input)
        assertEquals(7, actual)
    }

    @Test
    fun testPuzzleInputPart1() {
        val url = javaClass.getResource("day_01.txt") ?: error("input file not found")
        val input = File(url.toURI()).readLines()
        val solution = Day01.part1(input)
        assertEquals(1228, solution)
    }

    @Test
    fun testSampleInputPart2() {
        val input = listOf("199", "200", "208", "210", "200", "207", "240", "269", "260", "263")
        val actual = Day01.part2(input)
        assertEquals(5, actual)
    }

    @Test
    fun testPuzzleInputPart2() {
        val url = javaClass.getResource("day_01.txt") ?: error("input file not found")
        val input = File(url.toURI()).readLines()
        val solution = Day01.part2(input)
        assertEquals(1257, solution)
    }
}