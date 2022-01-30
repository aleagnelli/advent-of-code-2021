import java.util.*

object Day24 {
    private data class Params(val zDiv: Int, val xAdd: Int, val yAdd: Int)
    private data class Item(val index: Int, val y: Int)

    private fun parseParams(program: List<String>) = program.chunked(18).map { sub ->
        Params(
            sub[4].substringAfterLast(" ").toInt(),
            sub[5].substringAfterLast(" ").toInt(),
            sub[15].substringAfterLast(" ").toInt()
        )
    }

    private fun execute(parameters: List<Params>, order: List<Int>): Long {
        val stack = Stack<Item>()
        return parameters.withIndex()
            .fold(List(14) { -1 }) { modelNumber, (index, params) ->
                if (params.zDiv == 1) {
                    stack.push(Item(index, params.yAdd))
                    modelNumber
                } else {
                    val stacked = stack.pop()
                    val delta = stacked.y + params.xAdd
                    val valid = order.first { it + delta in 1..9 }
                    modelNumber.toMutableList().also {
                        it[stacked.index] = valid
                        it[index] = valid + delta
                    }
                }
            }
            .joinToString(separator = "").toLong()
    }


    fun part1(input: List<String>): Long {
        val parameters = parseParams(input)
        return execute(parameters, (9 downTo 1).toList())
    }

    fun part2(input: List<String>): Long {
        val parameters = parseParams(input)
        return execute(parameters, (1..9).toList())
    }
}
