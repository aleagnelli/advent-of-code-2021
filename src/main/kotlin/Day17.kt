object Day17 {
    private data class Bound(val min: Int, val max: Int)
    private data class Area(val x: Bound, val y: Bound)

    private data class Position(val x: Int, val y: Int)
    private data class Velocity(val x: Int, val y: Int)
    private data class Probe(val pos: Position, val velocity: Velocity) {
        fun step(): Probe = this.copy(
            pos = pos.copy(x = pos.x + velocity.x, y = pos.y + velocity.y),
            velocity = velocity.copy(
                x = when {
                    velocity.x < 0 -> velocity.x + 1
                    velocity.x > 0 -> velocity.x - 1
                    else -> 0
                },
                y = velocity.y - 1
            )
        )
    }

    private fun parseBound(input: String) = input.split("..").let { b -> Bound(b[0].toInt(), b[1].toInt()) }
    private fun parseInput(input: String) = input.replace("[a-z|:=\\s]".toRegex(), "").split(",")
        .let { Area(parseBound(it[0]), parseBound(it[1])) }

    private fun guessY(target: Area): List<Sequence<Probe>> {
        return (target.y.min..1000)
            .map { velocityTested ->
                val startingProbe = Probe(Position(0, 0), Velocity(0, velocityTested))
                generateSequence(startingProbe) { it.step() }.takeWhile { it.pos.y >= target.y.min }
            }
            .filter { it.last().pos.y <= target.y.max }
    }

    private fun guessX(target: Area): List<Sequence<Probe>> {
        return (1..target.x.max)
            .map { velocityTested ->
                val startingProbe = Probe(Position(0, 0), Velocity(velocityTested, 0))
                generateSequence(startingProbe) { it.step() }
                    .takeWhile { it.pos.x <= target.x.max && it.velocity.x != 0 }
            }
            .filter { it.last().pos.x >= target.x.min }
    }

    fun part1(input: String): Int {
        val target = parseInput(input)
        return guessY(target).maxOf { it.maxOf { s -> s.pos.y } }
    }

    fun part2(input: String): Int {
        val target = parseInput(input)
        val possibleX = guessX(target).map { it.first().velocity.x }
        val possibleY = guessY(target).map { it.first().velocity.y }
        val possibleVelocity = possibleX.flatMap { x -> possibleY.map { y -> Velocity(x, y) } }
        val velocities = possibleVelocity.filter { velocity ->
            val startingProbe = Probe(Position(0, 0), velocity)
            val finalPos = generateSequence(startingProbe) { it.step() }
                .takeWhile { it.pos.x <= target.x.max && it.pos.y >= target.y.min }
                .last().pos
            finalPos.y <= target.y.max && finalPos.x >= target.x.min
        }
        return velocities.size
    }
}
