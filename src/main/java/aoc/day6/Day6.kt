package aoc.day6

import aoc.utils.FileUtils
import java.nio.charset.StandardCharsets.UTF_8

fun main() {
    val input = FileUtils.readInput("day6").readText(UTF_8)

    println("Part 1: ${ run(input, 4) }") //1794
    println("Part 2: ${ run(input, 14) }") //2851
}

fun run(input: String, segmentSize: Int) : Int {
    var current = 0
    while (current + segmentSize <= input.length) {
        val chars = input.substring(current, current + segmentSize).toMutableList()
        val knowChars = mutableListOf<Char>()
        for(c in chars) { if (!knowChars.contains(c)) knowChars.add(c) else break }
        if(knowChars.size == segmentSize) { break }
        current++
    }
    return current + segmentSize;
}







