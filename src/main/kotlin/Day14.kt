object Day14 {
    private data class Input(val template: String, val insertions: Map<String, String>)

    private fun parseInput(input: String): Input {
        val parts = input.split("\n\n")
        val insertions = parts[1].lines().associate { line -> line.split(" -> ").let { it[0] to it[1] } }
        return Input(parts[0], insertions)
    }

    private fun applyInsertion(formula: Map<String, Long>, insertions: Map<String, String>): Map<String, Long> {
        return formula
            .flatMap {
                val newValue = insertions[it.key]!!
                listOf(it.key[0] + newValue to it.value, newValue + it.key[1] to it.value)
            }
            .groupBy { it.first }
            .mapValues { it.value.sumOf { e -> e.second } }
    }

    private fun solve(input: String, n: Int): Long {
        val args = parseInput(input)
        val baseFormula = args.template.toCharArray().toList()
            .windowed(2)
            .groupingBy { it.joinToString(separator = "") }.eachCount()
            .mapValues { it.value.toLong() }
        val finalFormula = (1..n).fold(baseFormula) { formula, _ -> applyInsertion(formula, args.insertions) }
        val counter = (finalFormula.map { it.key[0] to it.value } + Pair(args.template.last(), 1L))
            .groupBy { it.first }
            .mapValues { it.value.sumOf { e -> e.second } }
        return counter.values.let { it.maxOrNull()!! - it.minOrNull()!! }
    }

    fun part1(input: String): Long = solve(input, 10)

    fun part2(input: String): Long = solve(input, 40)
}
