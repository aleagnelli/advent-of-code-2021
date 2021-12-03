object Day03 {
    fun part1(input: List<String>): Int {
        val data = input.map { it.toCharArray().map { c -> c.digitToInt() } }
        val gamma = data
            .fold(List(data.first().size) { 0 }) { acc, list ->
                acc.zip(list).map { it.first + it.second }
            }
            .map { if (it > data.size / 2) 1 else 0 }
        val epsilon = gamma.map { if (it == 1) 0 else 1 }
        return gamma.binaryToInt() * epsilon.binaryToInt()
    }

    private fun List<Int>.binaryToInt(): Int = Integer.parseInt(joinToString(separator = ""), 2)

    fun part2(input: List<String>): Int {
        val data = input.map { it.toCharArray().map { c -> c.digitToInt() } }
        val oxygenGenRating = oxygen(data).binaryToInt()
        val co2ScrubberRating = co2(data).binaryToInt()
        return oxygenGenRating * co2ScrubberRating
    }

    private fun oxygen(data: List<List<Int>>): List<Int> = filterOnBitCriteria(data, 1, 0)

    private fun co2(data: List<List<Int>>): List<Int> = filterOnBitCriteria(data, 0, 1)

    private fun filterOnBitCriteria(
        data: List<List<Int>>,
        bitToKeepIfCommon: Int,
        bitToKeepIfNotCommon: Int,
        currentIndex: Int = 0
    ): List<Int> {
        return if (data.size <= 1 || currentIndex >= data.first().size) {
            data.first()
        } else {
            val is1MostCommon = data.sumOf { it[currentIndex] } >= data.size.toFloat() / 2
            val bitToKeep = if (is1MostCommon) bitToKeepIfCommon else bitToKeepIfNotCommon
            filterOnBitCriteria(
                data.filter { it[currentIndex] == bitToKeep },
                bitToKeepIfCommon,
                bitToKeepIfNotCommon,
                currentIndex + 1
            )
        }
    }
}