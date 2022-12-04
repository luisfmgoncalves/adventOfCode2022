package aoc.day4

import aoc.utils.FileUtils

fun main() {
    val lines = FileUtils.readInput("day4").readLines()
        .map { it.split(",") }
        .map { Pair(toSection(it[0]), toSection(it[1])) }

    val part1 = lines.count { it.first.includes(it.second) || it.second.includes(it.first) }
    val part2 = lines.count { it.first.overlaps(it.second) || it.second.overlaps(it.first) }

    println("Part 1: $part1") //599
    println("Part 2: $part2") //928
}

fun toSection(input: String): Section {
    val parts = input.split("-")
    return Section(parts[0].toInt(), parts[1].toInt())
}

class Section(private val lower: Int, private val upper: Int) {
    fun includes(other: Section): Boolean {
        return lower <= other.lower && upper >= other.upper
    }
    fun overlaps(other: Section) : Boolean {
        return other.lower in lower..upper || other.upper in lower.. upper
    }
}



