import Day18.assignment
import Day18.explode
import Day18.parse
import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class Day18Test {
    private val sample = """
        [1,1]
        [2,2]
        [3,3]
        [4,4]
    """.trimIndent().lines()

    private val sample2 = """
        [1,1]
        [2,2]
        [3,3]
        [4,4]
        [5,5]
    """.trimIndent().lines()

    private val sample3 = """
        [1,1]
        [2,2]
        [3,3]
        [4,4]
        [5,5]
        [6,6]
    """.trimIndent().lines()

    private val complexSample = """
        [[[0,[4,5]],[0,0]],[[[4,5],[2,6]],[9,5]]]
        [7,[[[3,7],[4,3]],[[6,3],[8,8]]]]
        [[2,[[0,8],[3,4]]],[[[6,7],1],[7,[1,6]]]]
        [[[[2,4],7],[6,[0,5]]],[[[6,8],[2,8]],[[2,1],[4,5]]]]
        [7,[5,[[3,8],[1,4]]]]
        [[2,[2,2]],[8,[8,1]]]
        [2,9]
        [1,[[[9,3],9],[[9,0],[0,7]]]]
        [[[5,[7,4]],7],1]
        [[[[4,2],2],6],[8,7]]
    """.trimIndent().lines()

    private val complexSample2 = """
        [[[0,[5,8]],[[1,7],[9,6]]],[[4,[1,2]],[[1,4],2]]]
        [[[5,[2,8]],4],[5,[[9,9],0]]]
        [6,[[[6,2],[5,6]],[[7,6],[4,7]]]]
        [[[6,[0,7]],[0,9]],[4,[9,[9,0]]]]
        [[[7,[6,4]],[3,[1,3]]],[[[5,5],1],9]]
        [[6,[[7,3],[3,2]]],[[[3,8],[5,7]],4]]
        [[[[5,4],[7,7]],8],[[8,3],8]]
        [[9,3],[[9,9],[6,[4,9]]]]
        [[2,[[7,7],7]],[[5,8],[[9,3],[0,2]]]]
        [[[[5,2],5],[8,[3,7]]],[[5,[7,5]],[4,4]]]
    """.trimIndent().lines()

    private val puzzleInput = readLines(javaClass, "day_18.txt")

    @Test
    fun testMagnitude() {
        assertEquals(29, parse("[9,1]").magnitude())
        assertEquals(129, parse("[[9,1],[1,9]]").magnitude())
        assertEquals(143, parse("[[1,2],[[3,4],5]]").magnitude())
        assertEquals(1384, parse("[[[[0,7],4],[[7,8],[6,0]]],[8,1]]").magnitude())
        assertEquals(445, parse("[[[[1,1],[2,2]],[3,3]],[4,4]]").magnitude())
        assertEquals(791, parse("[[[[3,0],[5,3]],[4,4]],[5,5]]").magnitude())
        assertEquals(1137, parse("[[[[5,0],[7,4]],[5,5]],[6,6]]").magnitude())
        assertEquals(3488, parse("[[[[8,7],[7,7]],[[8,6],[7,7]]],[[[0,7],[6,6]],[8,7]]]").magnitude())
    }

    @Test
    fun testExplode() {
        assertEquals(parse("[[[[0,9],2],3],4]"), explode(parse("[[[[[9,8],1],2],3],4]"))!!.first)
        assertEquals(parse("[7,[6,[5,[7,0]]]]"), explode(parse("[7,[6,[5,[4,[3,2]]]]]"))!!.first)
        assertEquals(parse("[[6,[5,[7,0]]],3]"), explode(parse("[[6,[5,[4,[3,2]]]],1]"))!!.first)
        assertEquals(
            parse("[[3,[2,[8,0]]],[9,[5,[4,[3,2]]]]]"),
            explode(parse("[[3,[2,[1,[7,3]]]],[6,[5,[4,[3,2]]]]]"))!!.first
        )
        assertEquals(
            parse("[[3,[2,[8,0]]],[9,[5,[7,0]]]]"),
            explode(parse("[[3,[2,[8,0]]],[9,[5,[4,[3,2]]]]]"))!!.first
        )
    }

    @Test
    fun testSampleInputPart1() {
        assertEquals(parse("[[[[1,1],[2,2]],[3,3]],[4,4]]"), assignment(sample))
        assertEquals(parse("[[[[3,0],[5,3]],[4,4]],[5,5]]"), assignment(sample2))
        assertEquals(parse("[[[[5,0],[7,4]],[5,5]],[6,6]]"), assignment(sample3))
        assertEquals(parse("[[[[8,7],[7,7]],[[8,6],[7,7]]],[[[0,7],[6,6]],[8,7]]]"), assignment(complexSample))
        assertEquals(4140, Day18.part1(complexSample2))
    }

    @Test
    fun testPuzzleInputPart1() {
        val solution = Day18.part1(puzzleInput)
        assertEquals(4433, solution)
    }

    @Test
    fun testSampleInputPart2() {
        assertEquals(3993, Day18.part2(complexSample2))
    }

    @Test
    fun testPuzzleInputPart2() {
        val solution = Day18.part2(puzzleInput)
        assertEquals(4559, solution)
    }
}
