object Day01 {

    fun part1(input: List<String>): Int {
        return input
            .map { it.toInt() }
            .zipWithNext()
            .count { it.second > it.first }
    }

    fun part2(input: List<String>): Int {
        return input
            .map { it.toInt() }
            .windowed(3) { it.sum() }
            .zipWithNext()
            .count { it.second > it.first }
    }
}