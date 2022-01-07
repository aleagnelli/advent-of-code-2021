import kotlin.math.max

object Day21 {
    private data class Player(val pos: Int, val score: Int = 0) {
        fun move(times: Int): Player {
            val newPos = ((pos + times) % 10).let { if (it == 0) 10 else it }
            return Player(newPos, score + newPos)
        }
    }

    private class Dice(private var value: Int) {
        private fun advance() {
            value = if (value == 100) 1 else value + 1
        }

        fun roll(times: Int): Int {
            var result = 0
            repeat(times) {
                result += value
                advance()
            }
            return result
        }
    }

    private fun parsePlayer(input: String) = Player(input.split(": ")[1].toInt())

    private fun practice(players: Pair<Player, Player>): Int {
        var playersPlaying = players
        var rolls = 0
        val dice = Dice(1)
        while (playersPlaying.first.score < 1000 && playersPlaying.second.score < 1000) {
            val steps = dice.roll(3)
            rolls += 3
            playersPlaying = playersPlaying
                .copy(first = playersPlaying.first.move(steps))
                .swap()
        }
        return playersPlaying.toList().minOf { it.score } * rolls
    }

    fun part1(input: List<String>): Int {
        val players = input.map(::parsePlayer).let { Pair(it[0], it[1]) }
        return practice(players)
    }

    private val splits = run {
        (1..3).flatMap { f ->
            (1..3).flatMap { s ->
                (1..3).map { t -> f + s + t }
            }
        }.groupingBy { it }.eachCount()
    }

    private fun realGame(universes: Map<Pair<Player, Player>, Long>, wins: Pair<Long, Long>): Long {
        return if (universes.isEmpty()) {
            max(wins.first, wins.second)
        } else {
            val newUniverses = universes
                .flatMap { (players, universePresent) ->
                    splits.map { (steps, frequency) ->
                        val newPlayers = players.copy(first = players.first.move(steps))
                        val count = universePresent * frequency
                        newPlayers to count
                    }
                }
                .groupBy { (players, _) -> players }
                .mapValues { (_, group) -> group.sumOf { (_, c) -> c } }
            val newWins = newUniverses.filter { (players, _) -> players.first.score >= 21 }.values.sum()
            val incompleteUniverses = newUniverses
                .filter { (players, _) -> players.first.score < 21 }
                .mapKeys { (players, _) -> players.swap() }
            realGame(incompleteUniverses, wins.copy(first = wins.first + newWins).swap())
        }
    }

    fun part2(input: List<String>): Long {
        val players = input.map(::parsePlayer).let { Pair(it[0], it[1]) }
        return realGame(mapOf(players to 1), Pair(0, 0))
    }
}
