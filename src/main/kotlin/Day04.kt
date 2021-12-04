object Day04 {
    private fun <E> List<List<E>>.transpose(): List<List<E>> {
        val cols = this[0].size
        val transposed = mutableListOf<List<E>>()
        for (colIndex in 0 until cols) {
            transposed.add(this.map { it[colIndex] })
        }
        return transposed
    }

    private fun List<Cell>.isComplete(): Boolean = count { it is Found } == size

    private data class Bingo(val numbers: List<Int>, val boards: List<Board>, private val currentIndex: Int = 0) {
        val winner = run {
            boards.firstOrNull { board ->
                val anyRowCompleted = board.board.any { it.isComplete() }
                val anyColCompleted = board.board.transpose().any { it.isComplete() }
                anyRowCompleted || anyColCompleted
            }
        }

        private fun thereIsAWinner(): Boolean = winner?.let { true } ?: false

        fun play(): Bingo {
            return if (this.thereIsAWinner()) {
                this
            } else {
                step().play()
            }
        }

        private fun step(): Bingo {
            val number = this.numbers[currentIndex]
            val updatedBoards = this.boards.map { board ->
                Board(board.board.map { row ->
                    row.map { if (it.number == number) Found(number) else it }
                })
            }
            return this.copy(boards = updatedBoards, currentIndex = currentIndex + 1)
        }

        fun playUntilLastWin(): Bingo {
            return if (this.thereIsAWinner() && this.boards.size == 1) {
                this
            } else if (this.thereIsAWinner()) {
                this.copy(boards = boards.filterNot { it == winner }).playUntilLastWin()
            } else {
                step().playUntilLastWin()
            }
        }

        fun score(): Int {
            val boardSum = winner!!.board.sumOf { row ->
                row.filterIsInstance<NotFound>().sumOf { it.number }
            }
            return numbers[currentIndex - 1] * boardSum
        }
    }

    private data class Board(val board: List<List<Cell>>)
    private abstract class Cell(open val number: Int)
    private data class Found(override val number: Int) : Cell(number)
    private data class NotFound(override val number: Int) : Cell(number)

    fun part1(input: String): Int {
        val bingo = parse(input)
        val endedBingo = bingo.play()
        return endedBingo.score()
    }

    private fun parse(input: String): Bingo {
        val list = input.split("\n\n")
        val numbers = list[0].split(",").map { it.toInt() }
        val board = list.drop(1).map { block ->
            Board(
                block.lines().map { row ->
                    row.split(" ")
                        .filter { it.isNotEmpty() }
                        .map { NotFound(it.toInt()) }
                }
            )
        }
        return Bingo(numbers, board)
    }

    fun part2(input: String): Int {
        val bingo = parse(input)
        val endedBingo = bingo.playUntilLastWin()
        return endedBingo.score()
    }

}