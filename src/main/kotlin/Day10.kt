import java.util.*

object Day10 {
    private sealed interface LineStatus
    private data class Incomplete(val missingParts: List<Char>) : LineStatus
    private data class Illegal(val value: Char) : LineStatus

    private val syntaxErrorPoints = mapOf(
        ')' to 3L,
        ']' to 57L,
        '}' to 1197L,
        '>' to 25137L
    )

    private val autocompletePoints = mapOf(
        ')' to 1L,
        ']' to 2L,
        '}' to 3L,
        '>' to 4L
    )

    private val match = mapOf(
        '(' to ')',
        '[' to ']',
        '{' to '}',
        '<' to '>'
    )

    private fun illegalChar(line: String): LineStatus {
        val stack = Stack<Char>()
        for (c in line.toCharArray()) {
            when (c) {
                in match -> stack.push(match[c])
                stack.peek() -> stack.pop()
                else -> return Illegal(c)
            }
        }
        return Incomplete(stack.reversed())
    }

    private fun autocompleteScore(part: List<Char>): Long = part
        .fold(0) { score, c -> score * 5 + autocompletePoints[c]!! }

    fun part1(input: List<String>): Long {
        return input
            .map { illegalChar(it) }
            .filterIsInstance<Illegal>()
            .sumOf { syntaxErrorPoints[it.value]!! }
    }

    fun part2(input: List<String>): Long {
        return input
            .map { illegalChar(it) }
            .filterIsInstance<Incomplete>()
            .map { autocompleteScore(it.missingParts) }
            .sorted()
            .let { it[it.size / 2] }
    }
}
