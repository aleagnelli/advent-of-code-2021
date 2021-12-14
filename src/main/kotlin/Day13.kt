object Day13 {
    private data class Dot(val x: Int, val y: Int)

    private sealed class FoldInstruction {
        abstract fun fold(dots: Set<Dot>): Set<Dot>
    }

    private data class UP(val line: Int) : FoldInstruction() {
        override fun fold(dots: Set<Dot>): Set<Dot> {
            val baseDots = dots.filterNot { it.y == line }.toSet()
            val foldables = baseDots.filter { it.y > line }.toSet()
            val foldablesReversed = foldables.map { it.copy(y = line - it.y + line) }.toSet()
            return baseDots - foldables + foldablesReversed
        }
    }

    private data class LEFT(val line: Int) : FoldInstruction() {
        override fun fold(dots: Set<Dot>): Set<Dot> {
            val baseDots = dots.filterNot { it.x == line }.toSet()
            val foldables = baseDots.filter { it.x > line }.toSet()
            val foldablesReversed = foldables.map { it.copy(x = line - it.x + line) }.toSet()
            return baseDots - foldables + foldablesReversed
        }
    }

    private data class Input(val dots: Set<Dot>, val foldInstructions: List<FoldInstruction>)

    private fun parseInput(input: String): Input {
        val parts = input.split("\n\n")
        return Input(parseDots(parts[0]), parseFoldInstruction(parts[1]))
    }

    private fun parseDots(dots: String) = dots
        .lines()
        .map {
            val parts = it.split(",").map { p -> p.toInt() }
            Dot(parts[0], parts[1])
        }.toSet()

    private fun parseFoldInstruction(instructions: String) = instructions
        .lines()
        .map {
            val parts = it.replace("fold along", "").trim().split("=")
            when (parts[0]) {
                "y" -> UP(parts[1].toInt())
                "x" -> LEFT(parts[1].toInt())
                else -> error("")
            }
        }

    fun part1(input: String): Int {
        val args = parseInput(input)
        return args.foldInstructions.first().fold(args.dots).size
    }

    private fun prettify(dots: Set<Dot>): String {
        val maxX = dots.maxOf { it.x }
        val maxY = dots.maxOf { it.y }
        return (0..maxY).joinToString(separator = "\n") { y ->
            (0..maxX).joinToString(separator = "") { x -> if (Dot(x, y) in dots) "#" else "." }
        }
    }

    fun part2(input: String): String {
        val args = parseInput(input)
        val folded = args.foldInstructions.fold(args.dots) { dots, instruction -> instruction.fold(dots) }
        return prettify(folded)
    }
}
