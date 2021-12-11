object Day11 {
    private data class Point(val row: Int, val col: Int) {
        fun adjacent(group: Grid<Octopus>) = listOfNotNull(
            group.getOrNull(row - 1)?.getOrNull(col - 1),
            group.getOrNull(row - 1)?.getOrNull(col/**/),
            group.getOrNull(row - 1)?.getOrNull(col + 1),
            group.getOrNull(row/**/)?.getOrNull(col - 1),
            group.getOrNull(row/**/)?.getOrNull(col + 1),
            group.getOrNull(row + 1)?.getOrNull(col - 1),
            group.getOrNull(row + 1)?.getOrNull(col/**/),
            group.getOrNull(row + 1)?.getOrNull(col + 1)
        ).map { it.pos }
    }

    private data class Octopus(val pos: Point, val energy: Int) {
        fun increaseEnergy() = copy(energy = energy + 1)
        fun isFlashing() = energy > 9
        fun reset() = copy(energy = if (isFlashing()) 0 else energy)
    }

    private fun Grid<Octopus>.step(): Grid<Octopus> {
        val increased = map { row -> row.map { it.increaseEnergy() } }
        val flashing = increased.flatMap { row -> row.filter { it.isFlashing() } }.map { it.pos }
        val flashedGroup = flash(increased, flashing, emptySet())
        return flashedGroup.map { row -> row.map { it.reset() } }
    }

    private fun flash(group: Grid<Octopus>, toVisit: List<Point>, flashed: Set<Point>): Grid<Octopus> {
        return when {
            toVisit.isEmpty() -> group
            toVisit.first() in flashed -> flash(group, toVisit.drop(1), flashed)
            else -> {
                val next = toVisit.first()
                val nextGroup = group.update(next.row, next.col, group[next.row][next.col].increaseEnergy())
                val visited = nextGroup[next.row][next.col]

                val extraVisit = if (visited.isFlashing()) visited.pos.adjacent(nextGroup) else emptyList()
                val extraFlashed = if (visited.isFlashing()) setOf(visited.pos) else emptySet()
                flash(nextGroup, toVisit.drop(1) + extraVisit, flashed + extraFlashed)
            }
        }
    }

    private fun Grid<Octopus>.countFlashed(): Int = sumOf { it.count { octopus -> octopus.energy == 0 } }

    private fun parseInput(input: List<String>) = input
        .map { it.toCharArray().map(Char::digitToInt) }
        .withIndex()
        .map { rowIndexed -> rowIndexed.value.withIndex().map { Octopus(Point(rowIndexed.index, it.index), it.value) } }

    fun part1(input: List<String>): Int {
        val initialGroup = parseInput(input)
        return generateSequence(initialGroup.step()) { it.step() }.take(100)
            .map { it.countFlashed() }.sum()
    }

    fun part2(input: List<String>): Int {
        val initialGroup = parseInput(input)
        return generateSequence(initialGroup) { it.step() }
            .withIndex()
            .first { it.value.countFlashed() == it.value.size * it.value.first().size }
            .index
    }
}
