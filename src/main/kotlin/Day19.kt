import kotlin.math.abs

class Day19(input: String) {
    companion object {
        private val perspectives = listOf<(Point) -> Point>(
            { (x, y, z) -> Point(+x, +y, +z) },
            { (x, y, z) -> Point(+y, +z, +x) },
            { (x, y, z) -> Point(+z, +x, +y) },
            { (x, y, z) -> Point(+z, +y, -x) },
            { (x, y, z) -> Point(+y, +x, -z) },
            { (x, y, z) -> Point(+x, +z, -y) },
            { (x, y, z) -> Point(+x, -y, -z) },
            { (x, y, z) -> Point(+y, -z, -x) },
            { (x, y, z) -> Point(+z, -x, -y) },
            { (x, y, z) -> Point(+z, -y, +x) },
            { (x, y, z) -> Point(+y, -x, +z) },
            { (x, y, z) -> Point(+x, -z, +y) },
            { (x, y, z) -> Point(-x, +y, -z) },
            { (x, y, z) -> Point(-y, +z, -x) },
            { (x, y, z) -> Point(-z, +x, -y) },
            { (x, y, z) -> Point(-z, +y, +x) },
            { (x, y, z) -> Point(-y, +x, +z) },
            { (x, y, z) -> Point(-x, +z, +y) },
            { (x, y, z) -> Point(-x, -y, +z) },
            { (x, y, z) -> Point(-y, -z, +x) },
            { (x, y, z) -> Point(-z, -x, +y) },
            { (x, y, z) -> Point(-z, -y, -x) },
            { (x, y, z) -> Point(-y, -x, -z) },
            { (x, y, z) -> Point(-x, -z, -y) }
        )

        data class Delta(val x: Int, val y: Int, val z: Int) {
            fun manhattan(d2: Delta) = abs(x - d2.x) + abs(y - d2.y) + abs(z - d2.z)
        }

        data class Point(val x: Int, val y: Int, val z: Int) {
            operator fun minus(p2: Point) = Delta(x - p2.x, y - p2.y, z - p2.z)
            operator fun plus(delta: Delta) = Point(x + delta.x, y + delta.y, z + delta.z)
        }

        fun parse(input: String): Map<String, Set<Point>> = input.split("\n\n").associate(::parseScanner)

        private fun parseScanner(input: String): Pair<String, Set<Point>> = input.lines()
            .let { it[0].replace("[a-z|\\-\\s]".toRegex(), "") to it.drop(1).map(::parseCoordinate).toSet() }

        fun parseCoordinate(rawCoordinate: String): Point =
            rawCoordinate.split(",").map { it.toInt() }.let { Point(it[0], it[1], it[2]) }


        fun overlap(fixed: Set<Point>, check: Set<Point>): Pair<(Point) -> Point, Delta>? {
            val fixedDistances = distances(fixed)
            val deltasForEachPerspective = createAllPerspectives(check)
                .map { (prosp, points) -> prosp to distances(points) }
            return deltasForEachPerspective
                .firstNotNullOfOrNull { (orientationApplied, deltas) ->
                    fixedDistances.firstNotNullOfOrNull { (x, xv) ->
                        deltas.firstNotNullOfOrNull { (y, yv) ->
                            if (xv.intersect(yv).size >= 12) Pair(orientationApplied, x - y) else null
                        }
                    }
                }
        }

        private fun createAllPerspectives(points: Set<Point>): List<Pair<(Point) -> Point, Set<Point>>> =
            perspectives.map { it to points.map(it).toSet() }

        private fun distances(points: Set<Point>) =
            points.associateWith { p -> points.map { p2 -> p - p2 }.toSet() }

        fun orientAll(beacons: Set<Point>, perspective: (Point) -> Point, delta: Delta) =
            beacons.map { perspective(it) + delta }.toSet()

        private fun findAll(
            unmapped: List<Set<Point>>,
            mapped: Set<Set<Point>>,
            fixed: Set<Point>,
            scannerPosition: Set<Delta>
        ): Pair<Set<Point>, Set<Delta>> {
            return if (unmapped.isEmpty()) {
                Pair(fixed, scannerPosition)
            } else {
                val check = unmapped.first()
                val overlap = overlap(fixed, check)
                val found = overlap?.let { (p, d) -> orientAll(check, p, d) }
                if (found != null) {
                    findAll(
                        unmapped.drop(1),
                        mapped + setOf(check),
                        fixed + found,
                        scannerPosition + setOf(overlap.second)
                    )
                } else {
                    findAll(unmapped.drop(1).toMutableList().also { it.add(check) }, mapped, fixed, scannerPosition)
                }
            }
        }
    }

    private val beaconsMapped = parse(input)
    private val results = findAll(
        beaconsMapped.filter { it.key != "0" }.values.toList(),
        setOf(beaconsMapped["0"]!!),
        beaconsMapped["0"]!!,
        setOf(Delta(0, 0, 0))
    )

    fun part1(): Int = results.first.size

    fun part2(): Int {
        val locations = results.second
        return locations.flatMap { d1 -> locations.map { d2 -> d1.manhattan(d2) } }.maxOrNull()!!
    }
}