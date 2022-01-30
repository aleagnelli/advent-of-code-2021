object Day25 {
    private data class Point(val x: Int, val y: Int) {
        fun moveEast(bound: Bound): Point {
            val next = if (y + 1 >= bound.east) 0 else y + 1
            return Point(x, next)
        }

        fun moveSouth(bound: Bound): Point {
            val next = if (x + 1 >= bound.south) 0 else x + 1
            return Point(next, y)
        }
    }

    private enum class SeaCucumber {
        EAST, SOUTH
    }

    private data class Bound(val south: Int, val east: Int)

    private fun parseGrid(input: String): Map<Point, SeaCucumber> =
        input.lines().withIndex().flatMap { (x, line) ->
            line.withIndex().mapNotNull { (y, it) ->
                when (it) {
                    '>' -> Point(x, y) to SeaCucumber.EAST
                    'v' -> Point(x, y) to SeaCucumber.SOUTH
                    '.' -> null
                    else -> error("$it not valid")
                }
            }
        }.toMap()

    private fun step(grid: Map<Point, SeaCucumber>, bound: Bound): Map<Point, SeaCucumber> {
        val nextGrid = stepEast(grid, bound)
        return stepSouth(nextGrid, bound)
    }

    private fun stepEast(grid: Map<Point, SeaCucumber>, bound: Bound): Map<Point, SeaCucumber> {
        val eastSC = grid.filter { it.value == SeaCucumber.EAST }
        val newEastSC = eastSC.mapKeys { (pos, _) ->
            val facingPos = pos.moveEast(bound)
            if (facingPos !in grid) facingPos else pos
        }
        return grid.filter { it.value == SeaCucumber.SOUTH } + newEastSC
    }

    private fun stepSouth(grid: Map<Point, SeaCucumber>, bound: Bound): Map<Point, SeaCucumber> {
        val southSC = grid.filter { it.value == SeaCucumber.SOUTH }
        val newSouthSC = southSC.mapKeys { (pos, _) ->
            val facingPos = pos.moveSouth(bound)
            if (facingPos !in grid) facingPos else pos
        }
        return grid.filter { it.value == SeaCucumber.EAST } + newSouthSC
    }

    fun part1(input: String): Int {
        val bound = input.lines().let { Bound(it.size, it[0].length) }
        val initialGrid = parseGrid(input)
        var current = initialGrid
        var counter = 0
        while (true) {
            val newGrid = step(current, bound)
            if (newGrid == current) break
            current = newGrid
            counter++
        }
        return counter + 1
    }

    private fun pprint(grid: Map<Point, SeaCucumber>, bound: Bound) {
        for (row in 0 until bound.south) {
            for (col in 0 until bound.east) {
                val representation = grid[Point(row, col)]?.let {
                    when (it) {
                        SeaCucumber.EAST -> '>'
                        SeaCucumber.SOUTH -> 'v'
                    }
                } ?: '.'
                print(representation)
            }
            println()
        }
    }
}
