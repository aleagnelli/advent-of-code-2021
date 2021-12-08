import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class Day08Test {
    private val sample = listOf(
        "be cfbegad cbdgef fgaecd cgeb fdcge agebfd fecdb fabcd edb | fdgacbe cefdb cefbgd gcbe",
        "edbfga begcd cbg gc gcadebf fbgde acbgfd abcde gfcbed gfec | fcgedb cgb dgebacf gc",
        "fgaebd cg bdaec gdafb agbcfd gdcbef bgcad gfac gcb cdgabef | cg cg fdcagb cbg",
        "fbegcd cbd adcefb dageb afcb bc aefdc ecdab fgdeca fcdbega | efabcd cedba gadfec cb",
        "aecbfdg fbg gf bafeg dbefa fcge gcbea fcaegb dgceab fcbdga | gecf egdcabf bgf bfgea",
        "fgeab ca afcebg bdacfeg cfaedg gcfdb baec bfadeg bafgc acf | gebdcfa ecba ca fadegcb",
        "dbcfg fgd bdegcaf fgec aegbdf ecdfab fbedc dacgb gdcebf gf | cefg dcbef fcge gbcadfe",
        "bdfegc cbegaf gecbf dfcage bdacg ed bedf ced adcbefg gebcd | ed bcgafe cdgba cbgef",
        "egadfb cdbfeg cegd fecab cgb gbdefca cg fgcdab egfdb bfceg | gbdfcae bgc cg cgb",
        "gcafb gcf dcaebfg ecagb gf abcdeg gaef cafbge fdbac fegbdc | fgae cfgab fg bagce"
    )

    private val puzzleInput = readLines(javaClass, "day_08.txt")

    @Test
    fun testSampleInputPart1() {
        val actual = Day08.part1(sample)
        assertEquals(26, actual)
    }

    @Test
    fun testPuzzleInputPart1() {
        val solution = Day08.part1(puzzleInput)
        assertEquals(392, solution)
    }

    @Test
    fun testSampleInputPart2() {
        val actual = Day08.part2(sample)
        assertEquals(61229, actual)
    }

    @Test
    fun testPuzzleInputPart2() {
        val solution = Day08.part2(puzzleInput)
        assertEquals(1_004_688, solution)
    }
}
