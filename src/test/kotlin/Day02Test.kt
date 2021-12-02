import org.junit.jupiter.api.Test
import java.io.File
import kotlin.test.assertEquals

class Day02Test {

    @Test
    fun testSampleInputPart1() {
        val input = listOf(
            "forward 5",
            "down 5",
            "forward 8",
            "up 3",
            "down 8",
            "forward 2"
        )
        val actual = Day02.part1(input)
        assertEquals(150, actual)
    }

    @Test
    fun testPuzzleInputPart1() {
        val url = javaClass.getResource("day_02.txt") ?: error("input file not found")
        val input = File(url.toURI()).readLines()
        val solution = Day02.part1(input)
        assertEquals(1_989_014, solution)
    }

    @Test
    fun testSampleInputPart2() {
        val input = listOf(
            "forward 5",
            "down 5",
            "forward 8",
            "up 3",
            "down 8",
            "forward 2"
        )
        val actual = Day02.part2(input)
        assertEquals(900, actual)
    }

    @Test
    fun testPuzzleInputPart2() {
        val url = javaClass.getResource("day_02.txt") ?: error("input file not found")
        val input = File(url.toURI()).readLines()
        val solution = Day02.part2(input)
        assertEquals(2_006_917_119, solution)
    }
}