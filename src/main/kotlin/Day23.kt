import kotlin.math.pow

enum class Amphipod(val index: Int) {
    A(0), B(1), C(2), D(3);

    val baseCost = 10.0.pow(index.toDouble()).toInt()
}

data class Room(val spots: List<Amphipod>) {
    fun canMoveThere(amphipod: Amphipod) = spots.all { it == amphipod }
    fun add(amphipod: Amphipod) = Room(spots + amphipod)
    fun allCorrect(i: Int) = spots.all { it.index == i }
    fun moveOut(): Room = Room(spots.drop(1))
}
typealias Hallway = List<Amphipod?>
typealias Rooms = List<Room>

class Solution(private val roomsSize: Int) {
    private data class State(val cost: Int, val rooms: Rooms, val hallway: Hallway)

    private fun possibleMoves(rooms: Rooms, hallway: Hallway): List<State> {
        val possibleRoomsMoves = movesToRoom(rooms, hallway)
        val possibleHallwayMoves = movesToHallway(rooms, hallway)
        return if (possibleRoomsMoves.isNotEmpty()) {
            possibleRoomsMoves.take(1) + possibleHallwayMoves
        } else {
            possibleHallwayMoves
        }
    }

    private fun movesToRoom(rooms: Rooms, hallway: Hallway): List<State> {
        return hallway
            .asSequence().withIndex()
            .filter { it.value != null }
            .filter { rooms[it.value!!.index].canMoveThere(it.value!!) }
            .map { it to calculateMoveCost(rooms[it.value!!.index], hallway, it.value!!.index, it.index, 1) }
            .filter { it.second != null }
            .map { (hallwaySpot, cost) ->
                State(
                    cost!!,
                    rooms.toMutableList().also {
                        it[hallwaySpot.value!!.index] = it[hallwaySpot.value!!.index].add(hallwaySpot.value!!)
                    },
                    hallway.update(hallwaySpot.index, null)
                )
            }.toList()
    }

    private fun movesToHallway(rooms: Rooms, hallway: Hallway): List<State> {
        return rooms
            .withIndex()
            .filter { !it.value.allCorrect(it.index) }
            .flatMap { (rIndex, room) ->
                hallway
                    .asSequence().withIndex()
                    .filter { it.value == null }
                    .map { it to calculateMoveCost(room, hallway, rIndex, it.index, 0) }
                    .filter { it.second != null }
                    .map { (hallwaySpot, cost) ->
                        State(
                            cost!!,
                            rooms.toMutableList().also {
                                it[rIndex] = it[rIndex].moveOut()
                            },
                            hallway.update(hallwaySpot.index, room.spots.first())
                        )
                    }.toList()
            }
    }

    // each row represent the cost from/to the room with that index
    private val ROOM_DISTANCE = listOf(
        listOf(2, 1, 1, 3, 5, 7, 8),
        listOf(4, 3, 1, 1, 3, 5, 6),
        listOf(6, 5, 3, 1, 1, 3, 4),
        listOf(8, 7, 5, 3, 1, 1, 2),
    )

    private fun calculateMoveCost(room: Room, hallway: Hallway, rIndex: Int, hIndex: Int, toRoom: Int): Int? {
        val (start, end) = if (rIndex + 1 < hIndex) {
            Pair(rIndex + 2, hIndex + ((toRoom + 1) % 2))
        } else {
            Pair(hIndex + toRoom, rIndex + 2)
        }

        return if (hallway.subList(start, end).any { it != null }) {
            null
        } else {
            val amphipod = if (toRoom == 1) hallway[hIndex]!! else room.spots[0]
            val distance = (ROOM_DISTANCE[rIndex][hIndex] + (toRoom + roomsSize - room.spots.size))
            return amphipod.baseCost * distance
        }
    }

    private fun isFinal(rooms: Rooms) = rooms.withIndex()
        .all { (index, room) -> room.spots.size == roomsSize && room.allCorrect(index) }

    private val cache: MutableMap<Pair<Rooms, Hallway>, Int?> = mutableMapOf()
    fun solve(rooms: Rooms, hallway: Hallway): Int? {
        val cacheKey = Pair(rooms, hallway)
        return if (cache.containsKey(cacheKey)) {
            cache[cacheKey]
        } else if (isFinal(rooms)) {
            0
        } else {
            var best: Int? = null
            for (state in possibleMoves(rooms, hallway)) {
                val solvedCost = solve(state.rooms, state.hallway) ?: continue
                val cost = state.cost + solvedCost
                best = if (best == null || cost < best) cost else best
            }
            cache[cacheKey] = best
            return best
        }
    }
}

object Day23 {
    private val emptyHallway = List<Amphipod?>(7) { null }
    private fun parseRooms(input: List<String>): Rooms {
        return input.drop(2).dropLast(1)
            .map { it.replace("[#\\s]".toRegex(), "") }
            .map { it.toCharArray().map { c -> c.toString() } }
            .let { it[0].zip(it[1]) }
            .map { (f, s) -> Room(listOf(Amphipod.valueOf(f), Amphipod.valueOf(s))) }
    }

    fun part1(input: List<String>): Int {
        val rooms = parseRooms(input)
        return Solution(2).solve(rooms, emptyHallway)!!
    }

    fun part2(input: List<String>): Int {
        val rooms = parseRooms(input)
        val extraRooms = listOf(
            Room(listOf(Amphipod.D, Amphipod.D)),
            Room(listOf(Amphipod.C, Amphipod.B)),
            Room(listOf(Amphipod.B, Amphipod.A)),
            Room(listOf(Amphipod.A, Amphipod.C))
        )
        val completedRooms = rooms.zip(extraRooms).map { (x, y) ->
            y.spots.toMutableList().also {
                it.add(0, x.spots[0])
                it.add(x.spots[1])
            }.let { Room(it) }
        }
        return Solution(4).solve(completedRooms, emptyHallway)!!
    }
}
