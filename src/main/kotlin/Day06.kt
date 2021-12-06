object Day06 {
    data class School(val fish: Map<Int, Long>) {
        fun nextDay(): School {
            val spawningFish = fish[0] ?: 0
            val shifted = (0..7).associateWith { fish[it + 1] ?: 0 }
            val nextDay = shifted.update(6, 0) { it + spawningFish } + (8 to spawningFish)
            return School(nextDay)
        }
    }

    private fun lanternFishSimulation(input: String, days: Int): Long {
        val lanterns = input
            .split(",")
            .map { it.toInt() }
            .groupingBy { it }.eachCount()
            .let { School(it.mapValues { e -> e.value.toLong() }) }
        return simulateSpawn(lanterns, days).fish.values.sumOf { it }
    }

    private fun simulateSpawn(fish: School, days: Int): School =
        if (days == 0) {
            fish
        } else {
            simulateSpawn(fish.nextDay(), days - 1)
        }

    fun part1(input: String): Long = lanternFishSimulation(input, 80)

    fun part2(input: String): Long = lanternFishSimulation(input, 256)
}
