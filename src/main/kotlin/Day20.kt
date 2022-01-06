object Day20 {
    private data class Point(val row: Int, val col: Int)

    private data class Scope(val rows: Bound, val cols: Bound) {
        fun widen() = Scope(rows.widen(), cols.widen())
        operator fun contains(p: Point) = p.row in rows && p.col in cols
    }

    private data class Bound(val min: Int, val max: Int) {
        fun widen() = Bound(min - 1, max + 1)
        operator fun contains(i: Int) = i in min..max
    }

    private fun parseInput(input: String) = input.split("\n\n").let { Pair(it[0], parsePoints(it[1])) }
    private fun parsePoints(input: String) = input.lines()
        .map { it.withIndex() }
        .withIndex()
        .flatMap { row ->
            row.value
                .filter { it.value == '#' }
                .map { col -> Point(row.index, col.index) }
        }
        .toSet()

    private fun createBlock(p: Point): List<Pair<Point, List<Point>>> =
        listOf(
            Point(p.row + 1, p.col + 1) to listOf(
                p /*                 */, Point(p.row/**/, p.col + 1), Point(p.row/**/, p.col + 2),
                Point(p.row + 1, p.col), Point(p.row + 1, p.col + 1), Point(p.row + 1, p.col + 2),
                Point(p.row + 2, p.col), Point(p.row + 2, p.col + 1), Point(p.row + 2, p.col + 2)
            ),
            Point(p.row + 1, p.col) to listOf(
                Point(p.row/**/, p.col - 1), p /*                 */, Point(p.row/**/, p.col + 1),
                Point(p.row + 1, p.col - 1), Point(p.row + 1, p.col), Point(p.row + 1, p.col + 1),
                Point(p.row + 2, p.col - 1), Point(p.row + 2, p.col), Point(p.row + 2, p.col + 1)
            ),
            Point(p.row + 1, p.col - 1) to listOf(
                Point(p.row/**/, p.col - 2), Point(p.row/**/, p.col - 1), p /*                 */,
                Point(p.row + 1, p.col - 2), Point(p.row + 1, p.col - 1), Point(p.row + 1, p.col),
                Point(p.row + 2, p.col - 2), Point(p.row + 2, p.col - 1), Point(p.row + 2, p.col)
            ),

            Point(p.row, p.col + 1) to listOf(
                Point(p.row - 1, p.col), Point(p.row - 1, p.col + 1), Point(p.row - 1, p.col + 2),
                p /*                 */, Point(p.row/**/, p.col + 1), Point(p.row/**/, p.col + 2),
                Point(p.row + 1, p.col), Point(p.row + 1, p.col + 1), Point(p.row + 1, p.col + 2)
            ),
            p to listOf(
                Point(p.row - 1, p.col - 1), Point(p.row - 1, p.col), Point(p.row - 1, p.col + 1),
                Point(p.row/**/, p.col - 1), p /*                 */, Point(p.row/**/, p.col + 1),
                Point(p.row + 1, p.col - 1), Point(p.row + 1, p.col), Point(p.row + 1, p.col + 1)
            ),
            Point(p.row, p.col - 1) to listOf(
                Point(p.row - 1, p.col - 2), Point(p.row - 1, p.col - 1), Point(p.row - 1, p.col),
                Point(p.row/**/, p.col - 2), Point(p.row/**/, p.col - 1), p /*                 */,
                Point(p.row + 1, p.col - 2), Point(p.row + 1, p.col - 1), Point(p.row + 1, p.col)
            ),

            Point(p.row - 1, p.col + 1) to listOf(
                Point(p.row - 2, p.col), Point(p.row - 2, p.col + 1), Point(p.row - 2, p.col + 2),
                Point(p.row - 1, p.col), Point(p.row - 1, p.col + 1), Point(p.row - 1, p.col + 2),
                p, /*                 */ Point(p.row/**/, p.col + 1), Point(p.row/**/, p.col + 2),
            ),
            Point(p.row - 1, p.col) to listOf(
                Point(p.row - 2, p.col - 1), Point(p.row - 2, p.col), Point(p.row - 2, p.col + 1),
                Point(p.row - 1, p.col - 1), Point(p.row - 1, p.col), Point(p.row - 1, p.col + 1),
                Point(p.row/**/, p.col - 1), p, /*                 */ Point(p.row/**/, p.col + 1),
            ),
            Point(p.row - 1, p.col - 1) to listOf(
                Point(p.row - 2, p.col - 2), Point(p.row - 2, p.col - 1), Point(p.row - 2, p.col),
                Point(p.row - 1, p.col - 2), Point(p.row - 1, p.col - 1), Point(p.row - 1, p.col),
                Point(p.row/**/, p.col - 2), Point(p.row/**/, p.col - 1), p, /*                 */
            )
        )

    private fun repeatAlgo(algo: String, activePoints: Set<Point>, scope: Scope, times: Int): Set<Point> {
        val isOutOfScopeOn = algo.first() == '#' && times % 2 == 1
        return if (times == 0) {
            activePoints
        } else {
            val blocks = (scope.rows.min..scope.rows.max).flatMap { row ->
                (scope.cols.min..scope.cols.max).map { col -> Point(row, col) }
            }.flatMap(::createBlock).toMap()
            val newActivePoints = blocks.filter {
                val index = it.value.joinToString(separator = "") { p ->
                    if (p in activePoints || (p !in scope && isOutOfScopeOn)) "1" else "0"
                }.toInt(2)
                algo[index] == '#'
            }.keys
            val newScope = scope.widen()
            repeatAlgo(algo, newActivePoints, newScope, times - 1)
        }
    }

    private fun solve(input: String, n: Int): Int {
        val (algo, activePoints) = parseInput(input)
        val scope = Scope(
            Bound(activePoints.minOf { it.row }, activePoints.maxOf { it.row }),
            Bound(activePoints.minOf { it.col }, activePoints.maxOf { it.col })
        )
        return repeatAlgo(algo, activePoints, scope, n).size
    }

    fun part1(input: String): Int = solve(input, 2)
    fun part2(input: String): Int = solve(input, 50)
}
