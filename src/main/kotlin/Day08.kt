object Day08 {
    fun part1(input: List<String>): Int {
        val uniqueLengths = setOf(2, 3, 4, 7)
        return input
            .map { it.split("|")[1].trim().split(" ") }
            .sumOf { it.count { output -> output.length in uniqueLengths } }
    }

    fun part2(input: List<String>): Int {
        val signals = input.map {
            val parts = it.split("|").map { p -> p.trim().split(" ") }
            parts[0] to parts[1]
        }
        return signals.sumOf { (patterns, output) ->
            val decodingTable = deduct(patterns.map { it.toCharArray().toSet() })
            buildOutput(output, decodingTable).toInt()
        }
    }

    private fun deduct(patterns: List<Set<Char>>): Map<Set<Char>, Int> {
        val oneSegments = patterns.first { it.size == 2 }
        val threeSegments = patterns.first { it.size == 5 && it.containsAll(oneSegments) }
        val fourSegments = patterns.first { it.size == 4 }
        val fiveSegments = patterns.filter { it.size == 6 }
            .reduce { a, b -> a.intersect(b) }
            .union(fourSegments - oneSegments)
        val sixSegments = patterns.first { it.size == 6 && !it.containsAll(oneSegments) }
        val nineSegments = fiveSegments.union(oneSegments)
        return mapOf(
            patterns.first { it.size == 6 && it != sixSegments && it != nineSegments } to 0,
            oneSegments to 1,
            patterns.first { it.size == 5 && it != threeSegments && it != fiveSegments } to 2,
            threeSegments to 3,
            fourSegments to 4,
            fiveSegments to 5,
            sixSegments to 6,
            patterns.first { it.size == 3 } to 7,
            patterns.first { it.size == 7 } to 8,
            nineSegments to 9
        )
    }

    private fun buildOutput(output: List<String>, decodingTable: Map<Set<Char>, Int>): String =
        output.joinToString(separator = "") {
            decodingTable[it.toCharArray().toSet()]!!.toString()
        }
}
