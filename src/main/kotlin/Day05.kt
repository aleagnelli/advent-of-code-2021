import kotlin.math.max
import kotlin.math.min

object Day05 {
    private data class Point(val x: Int, val y: Int)

    fun part1(input: List<String>): Int {
        val lines = parse(input).filter { (s, e) -> s.x == e.x || s.y == e.y }
        val hPoints = horizontalPoints(lines)
        val vPoints = verticalPoints(lines)
        return (hPoints + vPoints)
            .groupingBy { it }.eachCount()
            .count { it.value > 1 }
    }

    private fun horizontalPoints(lines: List<Pair<Point, Point>>) =
        lines.filter { (s, e) -> s.x == e.x }
            .flatMap { (s, e) -> (min(s.y, e.y)..max(s.y, e.y)).map { Point(s.x, it) } }

    private fun verticalPoints(lines: List<Pair<Point, Point>>) =
        lines.filter { (s, e) -> s.y == e.y }
            .flatMap { (s, e) -> (min(s.x, e.x)..max(s.x, e.x)).map { Point(it, s.y) } }

    private fun diagonalPoints(lines: List<Pair<Point, Point>>): List<Point> {
        fun diagonal(e: Point, acc: List<Point>, fx: (Int) -> Int, fy: (Int) -> Int): List<Point> {
            return if (acc.last() == e) {
                return acc
            } else {
                val next = acc.last().let { it.copy(x = fx(it.x), y = fy(it.y)) }
                diagonal(e, acc + next, fx, fy)
            }
        }

        fun mainDiagonal(lines: List<Pair<Point, Point>>) =
            lines
                .filter { (s, e) -> s.x < e.x && s.y < e.y }
                .flatMap { (s, e) -> diagonal(e, listOf(s), { it + 1 }, { it + 1 }) }

        fun antiDiagonal(lines: List<Pair<Point, Point>>) =
            lines
                .filter { (s, e) -> s.x < e.x && s.y > e.y }
                .flatMap { (s, e) -> diagonal(e, listOf(s), { it + 1 }, { it - 1 }) }

        return mainDiagonal(lines + lines.map { it.swap() }) + antiDiagonal(lines + lines.map { it.swap() })
    }

    private fun parse(input: List<String>) = input.map { rawLine ->
        val line = rawLine.split("->")
            .map(String::trim)
            .map {
                val point = it.split(",").map(String::toInt)
                Point(point.first(), point.last())
            }
        line.first() to line.last()
    }

    fun part2(input: List<String>): Int {
        val lines = parse(input)
        val hPoints = horizontalPoints(lines)
        val vPoints = verticalPoints(lines)
        val dPoints = diagonalPoints(lines)
        return (hPoints + vPoints + dPoints)
            .groupingBy { it }.eachCount()
            .count { it.value > 1 }
    }

}
