import java.util.*

object Day15 {
    private data class Point(val row: Int, val col: Int)

    private fun parseInput(input: String): Grid<Int> =
        input.lines().map { it.toCharArray().map(Char::digitToInt) }

    private fun findRisk(risks: Grid<Int>): Int {
        val rows = risks.size
        val cols = risks[0].size
        val finalRisks = Array(rows) { Array(cols) { Int.MAX_VALUE } }
        finalRisks[0][0] = 0

        val visited = mutableSetOf<Point>()
        val queue = PriorityQueue<Pair<Point, Int>>(rows * cols, compareBy { it.second })
        queue.add(Point(0, 0) to 0)

        fun check(row: Int, col: Int, v: Int) {
            listOfNotNull(
                finalRisks.getOrNull(row)?.getOrNull(col),
                risks.getOrNull(row)?.getOrNull(col)?.let { it + v }
            ).minOrNull()?.let {
                finalRisks[row][col] = it
                queue.add(Point(row, col) to it)
            }
        }

        val endPoint = Point(rows - 1, cols - 1)
        while (endPoint !in visited) {
            val next = queue.poll()

            val minVal = next.second
            val minRow = next.first.row
            val minCol = next.first.col

            visited.add(Point(minRow, minCol))
            check(minRow - 1, minCol, minVal)
            check(minRow + 1, minCol, minVal)
            check(minRow, minCol - 1, minVal)
            check(minRow, minCol + 1, minVal)
            queue.removeAll(queue.filter { it.first in visited }.toSet())
        }

        return finalRisks.last().last()
    }

    private fun extend(risks: Grid<Int>): Grid<Int> {
        fun increase(grid: Grid<Int>): Grid<Int> = grid.map { row -> row.map { if (it + 1 > 9) 1 else it + 1 } }
        val s = generateSequence(risks) { increase(it) }.take(9).windowed(5)
        return s
            .map { it.reduce { g1, g2 -> g1.zip(g2).map { rowPair -> rowPair.first + rowPair.second } } }
            .reduce { g1, g2 -> g1 + g2 }
    }

    fun part1(input: String): Int {
        val risks = parseInput(input)
        return findRisk(risks)
    }

    fun part2(input: String): Int {
        val risks = parseInput(input)
        val completeRisks = extend(risks)
        return findRisk(completeRisks)
    }
}
