import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class Day12Test {

    private val sample1 = """
        start-A
        start-b
        A-c
        A-b
        b-d
        A-end
        b-end
    """.trimIndent().lines()

    private val sample2 = """
        dc-end
        HN-start
        start-kj
        dc-start
        dc-HN
        LN-dc
        HN-end
        kj-sa
        kj-HN
        kj-dc
    """.trimIndent().lines()

    private val sample3 = """
        fs-end
        he-DX
        fs-he
        start-DX
        pj-DX
        end-zg
        zg-sl
        zg-pj
        pj-he
        RW-he
        fs-DX
        pj-RW
        zg-RW
        start-pj
        he-WI
        zg-he
        pj-fs
        start-RW
    """.trimIndent().lines()

    private val puzzleInput = readLines(javaClass, "day_12.txt")

    @Test
    fun testSampleInputPart1() {
        assertEquals(10, Day12.part1(sample1))
        assertEquals(19, Day12.part1(sample2))
        assertEquals(226, Day12.part1(sample3))
    }

    @Test
    fun testPuzzleInputPart1() {
        val solution = Day12.part1(puzzleInput)
        assertEquals(4912, solution)
    }

    @Test
    fun testSampleInputPart2() {
        assertEquals(36, Day12.part2(sample1))
        assertEquals(103, Day12.part2(sample2))
        assertEquals(3509, Day12.part2(sample3))
    }

    @Test
    fun testPuzzleInputPart2() {
        val solution = Day12.part2(puzzleInput)
        assertEquals(150_004, solution)
    }
}
