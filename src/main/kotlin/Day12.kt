object Day12 {
    private fun String.isBig() = this == this.uppercase()
    private fun String.isSmall() = this == this.lowercase()

    private fun parseInput(input: List<String>): Map<String, List<String>> = input.flatMap {
        val path = it.split("-")
        listOf(path[0] to path[1], path[1] to path[0])
    }
        .groupBy { it.first }
        .mapValues { e -> e.value.map { it.second } }

    private fun findPaths(
        graph: Map<String, List<String>>,
        currentNode: String,
        canRevisitSmall: Boolean = false,
        pathBuilder: List<String> = emptyList()
    ): List<List<String>> {
        return when (currentNode) {
            "end" -> listOf(pathBuilder + currentNode)
            in graph -> graph[currentNode]!!.filter { it != "start" }.flatMap {
                val isFirstVisit = it !in pathBuilder
                when {
                    !canRevisitSmall && (isFirstVisit || it.isBig()) || canRevisitSmall && !isFirstVisit && it.isSmall() ->
                        findPaths(graph, it, false, pathBuilder + currentNode)
                    !canRevisitSmall -> emptyList()
                    else -> findPaths(graph, it, true, pathBuilder + currentNode)
                }
            }
            else -> error("$currentNode is not in the graph")
        }
    }

    fun part1(input: List<String>): Int {
        val graph = parseInput(input)
        return findPaths(graph, "start").size
    }

    fun part2(input: List<String>): Int {
        val graph = parseInput(input)
        return findPaths(graph, "start", true).size
    }
}
