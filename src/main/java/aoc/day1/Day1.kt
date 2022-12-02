package aoc.day1

import aoc.utils.FileUtils

fun main() {
    val lines = FileUtils.readInput("day1").readLines()

    val calories = mutableListOf<Int>()
    lines.fold(0) { acc, element ->
        if (element.isEmpty()) {
            calories.add(acc)
            0
        } else acc + element.toInt()}
    calories.sortDescending()

    println("Part 1: " + calories[0]) //66487
    println("Part 2: " + calories.take(3).sum()) //197301
}