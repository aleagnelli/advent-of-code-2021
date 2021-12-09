object Day09 {
    private data class Height(val row: Int, val col: Int, val value: Int) {
        fun adjacent(heightMap: List<List<Height>>) = listOfNotNull(
            heightMap.getOrNull(row - 1)?.getOrNull(col),
            heightMap.getOrNull(row + 1)?.getOrNull(col),
            heightMap.getOrNull(row)?.getOrNull(col - 1),
            heightMap.getOrNull(row)?.getOrNull(col + 1)
        )
    }

    private fun parseInput(input: List<String>) = input
        .map { it.toCharArray().map(Char::digitToInt) }
        .withIndex()
        .map { rowIndexed -> rowIndexed.value.withIndex().map { Height(rowIndexed.index, it.index, it.value) } }

    private fun lowestPoints(heightMap: List<List<Height>>) = heightMap
        .flatMap { row ->
            row.filterNot { height -> height.adjacent(heightMap).any { it.value <= height.value } }
        }

    private fun findBasin(
        heightMap: List<List<Height>>,
        toVisit: Set<Height>,
        basinPoints: Set<Height> = emptySet()
    ): Set<Height> {
        return if (toVisit.isEmpty()) {
            basinPoints
        } else {
            val nextPoint = toVisit.first()
            val newPoints = nextPoint
                .adjacent(heightMap).filter { it.value != 9 }
                .filter { it.value > nextPoint.value }
            findBasin(heightMap, toVisit - nextPoint + newPoints, basinPoints + nextPoint)
        }
    }

    fun part1(input: List<String>): Int {
        val heightMap = parseInput(input)
        val lowestPoints = lowestPoints(heightMap)
        return lowestPoints.sumOf { it.value } + lowestPoints.size
    }

    fun part2(input: List<String>): Int {
        val heightMap = parseInput(input)
        val lowestPoints = lowestPoints(heightMap)
        val basins = lowestPoints.map { findBasin(heightMap, setOf(it)) }
        return basins
            .map { it.size }
            .sortedDescending()
            .take(3)
            .reduce { a, b -> a * b }
    }
}
