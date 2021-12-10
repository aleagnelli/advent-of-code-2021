import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class Day10Test {
    private val sample = """
        [({(<(())[]>[[{[]{<()<>>
        [(()[<>])]({[<{<<[]>>(
        {([(<{}[<>[]}>{[]{[(<()>
        (((({<>}<{<{<>}{[]{[]{}
        [[<[([]))<([[{}[[()]]]
        [{[{({}]{}}([{[{{{}}([]
        {<[[]]>}<{[{[{[]{()[[[]
        [<(<(<(<{}))><([]([]()
        <{([([[(<>()){}]>(<<{{
        <{([{{}}[<[[[<>{}]]]>[]]
    """.trimIndent().lines()

    private val puzzleInput = readLines(javaClass, "day_10.txt")

    @Test
    fun testSampleInputPart1() {
        val actual = Day10.part1(sample)
        assertEquals(26_397, actual)
    }

    @Test
    fun testPuzzleInputPart1() {
        val solution = Day10.part1(puzzleInput)
        assertEquals(311_949, solution)
    }

    @Test
    fun testSampleInputPart2() {
        val actual = Day10.part2(sample)
        assertEquals(288_957, actual)
    }

    @Test
    fun testPuzzleInputPart2() {
        val solution = Day10.part2(puzzleInput)
        assertEquals(3_042_730_309, solution)
    }
}
