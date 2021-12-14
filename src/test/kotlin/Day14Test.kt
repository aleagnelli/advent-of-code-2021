import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class Day14Test {
    private val sample = """
        NNCB

        CH -> B
        HH -> N
        CB -> H
        NH -> C
        HB -> C
        HC -> B
        HN -> C
        NN -> C
        BH -> H
        NC -> B
        NB -> B
        BN -> B
        BB -> N
        BC -> B
        CC -> N
        CN -> C
    """.trimIndent()

    private val puzzleInput = readText(javaClass, "day_14.txt")

    @Test
    fun testSampleInputPart1() {
        val actual = Day14.part1(sample)
        assertEquals(1588, actual)
    }

    @Test
    fun testPuzzleInputPart1() {
        val solution = Day14.part1(puzzleInput)
        assertEquals(3411, solution)
    }

    @Test
    fun testSampleInputPart2() {
        val actual = Day14.part2(sample)
        assertEquals(2_188_189_693_529, actual)
    }

    @Test
    fun testPuzzleInputPart2() {
        val solution = Day14.part2(puzzleInput)
        assertEquals(7_477_815_755_570, solution)
    }
}
