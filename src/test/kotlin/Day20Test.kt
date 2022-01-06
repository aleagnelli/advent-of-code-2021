import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class Day20Test {
    private val sample = """
        ..#.#..#####.#.#.#.###.##.....###.##.#..###.####..#####..#....#..#..##..###..######.###...####..#..#####..##..#.#####...##.#.#..#.##..#.#......#.###.######.###.####...#.##.##..#..#..#####.....#.#....###..#.##......#.....#..#..#..##..#...##.######.####.####.#.#...#.......#..#.#.#...####.##.#......#..#...##.#.##..#...##.#.##..###.#......#.#.......#.#.#.####.###.##...#.....####.#..#..#.##.#....##..#.####....##...##..#...#......#.#.......#.......##..####..#...#.#.#...##..#.#..###..#####........#..####......#..#
        
        #..#.
        #....
        ##..#
        ..#..
        ..###
    """.trimIndent()

    private val puzzleInput = readText(javaClass, "day_20.txt")

    @Test
    fun testSampleInputPart1() {
        val actual = Day20.part1(sample)
        assertEquals(35, actual)
    }

    @Test
    fun testPuzzleInputPart1() {
        val solution = Day20.part1(puzzleInput)
        assertEquals(5437, solution)
    }

    @Test
    fun testSampleInputPart2() {
        val actual = Day20.part2(sample)
        assertEquals(3351, actual)
    }

    @Test
    fun testPuzzleInputPart2() {
        val solution = Day20.part2(puzzleInput)
        assertEquals(19_340, solution)
    }
}
