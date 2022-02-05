import kotlin.math.max
import kotlin.math.min

object Day22 {
    private enum class State { ON, OFF }
    private data class Cube(val state: State, val x: Range, val y: Range, val z: Range) {
        fun intersection(other: Cube) =
            x.intersection(other.x)?.let { newX ->
                y.intersection(other.y)?.let { newY ->
                    z.intersection(other.z)?.let { newZ ->
                        val state = if (state == State.ON) State.OFF else State.ON
                        Cube(state, newX, newY, newZ)
                    }
                }
            }
    }

    private data class Range(val min: Long, val max: Long) {
        val length = max - min + 1

        fun intersection(other: Range): Range? =
            if (min > other.max || max < other.min) null
            else Range(max(min, other.min), min(max, other.max))
    }

    private fun parseCubes(input: List<String>) = input.map(::parseCube)

    private fun parseCube(input: String): Cube {
        val parts = input.split(" ")
        val state = State.valueOf(parts[0].uppercase())
        val ranges = parts[1].split(",").map(::parseRange)
        return Cube(state, ranges[0], ranges[1], ranges[2])
    }

    private fun parseRange(input: String) =
        input.drop(2).split("..").let { Range(it[0].toLong(), it[1].toLong()) }

    private fun intersectAll(cubes: List<Cube>, cubeToAdd: Cube): List<Cube> {
        return cubes
            .fold(emptyList<Cube>()) { newCubes, cube ->
                newCubes.toMutableList().also { list ->
                    cube.intersection(cubeToAdd)?.let { list.add(it) }
                }
            }
            .toMutableList()
            .also { if (cubeToAdd.state == State.ON) it.add(cubeToAdd) }
    }

    private fun countCube(cubes: List<Cube>): Long = cubes.sumOf { countCube(it) }
    private fun countCube(cube: Cube): Long {
        val multiplier = if (cube.state == State.ON) 1L else -1L
        return multiplier * cube.x.length * cube.y.length * cube.z.length
    }

    fun part1(input: List<String>): Long {
        val allCubes = parseCubes(input)
            .filter { it.x.min in (-50..50) }
            .fold(emptyList<Cube>()) { cubes, currentCube ->
                cubes + intersectAll(cubes, currentCube)
            }
        return countCube(allCubes)
    }

    fun part2(input: List<String>): Long {
        val allCubes = parseCubes(input)
            .fold(emptyList<Cube>()) { cubes, currentCube ->
                cubes + intersectAll(cubes, currentCube)
            }
        return countCube(allCubes)
    }
}
