import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class Day13Test {
    private val sample = """
        6,10
        0,14
        9,10
        0,3
        10,4
        4,11
        6,0
        6,12
        4,1
        0,13
        10,12
        3,4
        3,0
        8,4
        1,10
        2,14
        8,10
        9,0

        fold along y=7
        fold along x=5
    """.trimIndent()

    private val puzzleInput = readText(javaClass, "day_13.txt")

    @Test
    fun testSampleInputPart1() {
        val actual = Day13.part1(sample)
        assertEquals(17, actual)
    }

    @Test
    fun testPuzzleInputPart1() {
        val solution = Day13.part1(puzzleInput)
        assertEquals(781, solution)
    }

    @Test
    fun testPuzzleInputPart2() {
        val solution = Day13.part2(puzzleInput)
        assertEquals(
            """
                ###..####.###...##...##....##.###..###.
                #..#.#....#..#.#..#.#..#....#.#..#.#..#
                #..#.###..#..#.#....#.......#.#..#.###.
                ###..#....###..#....#.##....#.###..#..#
                #....#....#.#..#..#.#..#.#..#.#....#..#
                #....####.#..#..##...###..##..#....###.
            """.trimIndent(),
            solution
        )
    }
}
