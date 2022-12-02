package aoc.day2

import aoc.day2.Shape.PAPER
import aoc.day2.Shape.ROCK
import aoc.day2.Shape.SCISSORS
import aoc.utils.FileUtils

fun main() {
    val lines = FileUtils.readInput("day2").readLines()
        .map { it.split(" ") }
        .map { Pair(toShape(it[0]), toShape(it[1])) }

    val part1 = lines.sumOf { it.second.match(it.first) }
    val part2 = lines.map { assignMatchResult(it) }.sumOf { it.second.match(it.first) }

    println("Part 1: $part1") //14163
    println("Part 2: $part2") //12091
}

fun toShape(shapeStr: String): Shape {
    return when(shapeStr) {
        "A", "X" -> ROCK
        "B", "Y" -> PAPER
        "C", "Z" -> SCISSORS
        else -> throw IllegalArgumentException("invalid shape string")
    }
}

fun assignMatchResult(pair: Pair<Shape, Shape>) : Pair<Shape, Shape> {
    val result = when (pair.second) {
        ROCK -> pair.first.wins()
        PAPER -> pair.first
        SCISSORS -> pair.first.loses()
    }
    return Pair(pair.first, result)
}

enum class Shape(val value: Int) {
    ROCK(1) {
        override fun wins() = SCISSORS
        override fun loses() = PAPER
    },
    PAPER(2) {
        override fun wins() = ROCK
        override fun loses() = SCISSORS
    },
    SCISSORS(3) {
        override fun wins() = PAPER
        override fun loses() = ROCK
    };

    abstract fun wins(): Shape
    abstract fun loses(): Shape
    fun match(opponent: Shape) : Int {
        return value + when (this) {
            opponent.wins() -> 0
            opponent.loses() -> 6
            else -> 3
        }
    }
}



