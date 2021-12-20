import Day19.Companion.orientAll
import Day19.Companion.overlap
import Day19.Companion.parse
import Day19.Companion.parseCoordinate
import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class Day19Test {
    private val sample = readText(javaClass, "day_19_sample.txt")

    private val puzzleInput = readText(javaClass, "day_19.txt")

    @Test
    fun testOverlapping01() {
        val input = parse(sample)
        val expected = """
            -618,-824,-621
            -537,-823,-458
            -447,-329,318
            404,-588,-901
            544,-627,-890
            528,-643,409
            -661,-816,-575
            390,-675,-793
            423,-701,434
            -345,-311,381
            459,-707,401
            -485,-357,347
        """.trimIndent().lines().map(::parseCoordinate).toSet()
        assertEquals(
            expected,
            overlap(input["0"]!!, input["1"]!!)?.let { (p, d) ->
                orientAll(input["1"]!!, p, d).intersect(input["0"]!!)
            }
        )
    }

    @Test
    fun testSampleInput() {
        val day = Day19(sample)
        assertEquals(79, day.part1())
        assertEquals(3621, day.part2())
    }

    @Test
    fun testPuzzleInput() {
        val day = Day19(puzzleInput)
        assertEquals(425, day.part1())
        assertEquals(13354, day.part2())
    }
}
