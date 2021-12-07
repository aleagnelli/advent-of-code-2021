import java.lang.StrictMath.abs

object Day07 {
    fun part1(input: String): Int {
        val crabs = parseInput(input)
        return simulateFuelUsage(crabs) { it }
    }

    fun part2(input: String): Int {
        val crabs = parseInput(input)
        return simulateFuelUsage(crabs) { (1..it).sum() }
    }

    private fun parseInput(input: String) = input.split(",").map { it.toInt() }

    private fun simulateFuelUsage(crabs: List<Int>, fuelCost: (Int) -> Int): Int {
        val costCache = mutableMapOf<Int, Int>()
        val min = 0
        val max = crabs.maxOrNull() ?: 0
        return (min..max).map { pos ->
            crabs.sumOf {
                val move = abs(it - pos)
                if (!costCache.containsKey(move)) {
                    costCache[move] = fuelCost(move)
                }
                costCache[move]!!
            }
        }.minOrNull() ?: 0
    }
}
