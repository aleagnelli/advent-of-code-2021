object Day01 {

    private fun Pair<Int, Int>.sum() = first + second

    fun part1(input: List<String>): Int {
        return input
            .map { it.toInt() }
            .zipWithNext()
            .count { it.second > it.first }
    }

    fun part2(input: List<String>): Int {
        val depths = input.map { it.toInt() }
        val depthsWindows = depths
            .zipWithNext()
            .map { it.sum() }
            .zip(depths.drop(2))
            .map { it.sum() }
        return depthsWindows
            .zipWithNext()
            .count { it.second > it.first }
    }
}