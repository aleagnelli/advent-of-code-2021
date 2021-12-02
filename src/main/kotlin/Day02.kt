object Day02 {
    private abstract class Position(
        open val horizontal: Int,
        open val depth: Int
    ) {
        abstract fun forward(x: Int): Position
        abstract fun down(x: Int): Position
        abstract fun up(x: Int): Position
    }

    private data class Position1(
        override val horizontal: Int,
        override val depth: Int
    ) : Position(horizontal, depth) {
        override fun forward(x: Int) = this.copy(horizontal = horizontal + x)
        override fun down(x: Int) = this.copy(depth = depth + x)
        override fun up(x: Int) = this.copy(depth = depth - x)
    }

    private data class Position2(
        override val horizontal: Int,
        override val depth: Int,
        val aim: Int
    ) : Position(horizontal, depth) {
        override fun forward(x: Int) = this.copy(horizontal = horizontal + x, depth = depth + aim * x)
        override fun down(x: Int) = this.copy(aim = aim + x)
        override fun up(x: Int) = this.copy(aim = aim - x)
    }

    fun part1(input: List<String>): Int = solve(input, Position1(0, 0))

    fun part2(input: List<String>): Int = solve(input, Position2(0, 0, 0))

    private fun solve(input: List<String>, initialPosition: Position): Int {
        return input
            .map { it.split(" ") }
            .fold(initialPosition) { position, (command, amount) ->
                val x = amount.toInt()
                when (command) {
                    "forward" -> position.forward(x)
                    "down" -> position.down(x)
                    "up" -> position.up(x)
                    else -> error("$command is not a valid command")
                }
            }
            .let { it.horizontal * it.depth }
    }
}