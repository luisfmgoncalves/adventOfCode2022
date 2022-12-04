package aoc.day3

import aoc.utils.FileUtils

fun main() {
    val lines = FileUtils.readInput("day3").readLines()

    val part1 = lines
        .map { it.chunked(it.length / 2) }
        .map { getBadge(it) }
        .sumOf { getCharValue(it) }

   val part2 = lines.chunked(3)
       .map { getBadge(it) }
       .sumOf { getCharValue(it) }

    println("Part 1: $part1") //7831
    println("Part 2: $part2") //2683
}

//determine the repeated characters in all string of a (variable size) list
fun getBadge(list: List<String>): Char? {
    for (c in list[0].toCharArray()) {
        var present = true
        for(i in 1 until list.size) {
            if (list[i].indexOf(c) < 0) {
                present = false
                break
            }
        }
        if(present) return c
    }
    return null
}

val lowerCases = ('a'..'z').toMutableList()
val upperCases = ('A'..'Z').toMutableList()
fun getCharValue(character: Char?): Int {
    return if(lowerCases.contains(character)) {
        lowerCases.indexOf(character) + 1
    } else {
        upperCases.indexOf(character) + 27
    }
}




