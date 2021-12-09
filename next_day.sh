#!/usr/bin/env bash

last_day="10#$(find src/main/kotlin/Day* | sort -r | head -n1 | grep -oE '([0-9]){2}')"
next_day=$((last_day + 1))
day=$(printf "%02d" "${next_day}")

cat <<EOF >"src/main/kotlin/Day${day}.kt"
object Day${day} {
    fun part1(input: List<String>): Int {
        TODO()
    }

    fun part2(input: List<String>): Int {
        TODO()
    }
}
EOF
touch "src/main/resources/day_${day}.txt"
cat <<EOF >"src/test/kotlin/Day${day}Test.kt"
import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class Day${day}Test {
    private val sample = TODO()

    private val puzzleInput = readLines(javaClass, "day_${day}.txt")

    @Test
    fun testSampleInputPart1() {
        val actual = Day${day}.part1(sample)
        assertEquals(0, actual)
    }

    @Test
    fun testPuzzleInputPart1() {
        val solution = Day${day}.part1(puzzleInput)
        assertEquals(0, solution)
    }

    @Test
    fun testSampleInputPart2() {
        val actual = Day${day}.part2(sample)
        assertEquals(0, actual)
    }

    @Test
    fun testPuzzleInputPart2() {
        val solution = Day${day}.part2(puzzleInput)
        assertEquals(0, solution)
    }
}
EOF
