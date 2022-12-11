package aoc.day10

import aoc.utils.FileUtils

fun main() {
    val input = FileUtils.readInput("day10").readLines()

    println("Part 1: ${solvePart1(input)}") //13520

    println("Part 2:") //PGPHBEAB
    solvePart2(input)

//    ###...##..###..#..#.###..####..##..###..
//    #..#.#..#.#..#.#..#.#..#.#....#..#.#..#.
//    #..#.#....#..#.####.###..###..#..#.###..
//    ###..#.##.###..#..#.#..#.#....####.#..#.
//    #....#..#.#....#..#.#..#.#....#..#.#..#.
//    #.....###.#....#..#.###..####.#..#.###..
}

fun solvePart2(input: List<String>) {
    var x = 1
    var currentCycle = 0
    var currentLine = ""
    for (line in input) {
        if (line.startsWith("noop")) {
            if(currentCycle == 40) {
                println(currentLine)
                currentCycle = 0
                currentLine = ""
            }
            currentLine += if(currentCycle in listOf(x - 1, x, x + 1)) '#' else "."
            currentCycle++
        } else {
            val amount = line.split(" ")[1].toInt()
            for (i in 0 .. 1) {
                if (currentCycle == 40) {
                    println(currentLine)
                    currentCycle = 0
                    currentLine = ""
                }
                currentLine += if(currentCycle in listOf(x - 1, x, x + 1)) '#' else "."
                if (i == 1) { x += amount }
                currentCycle++
            }
        }
    }
    println(currentLine)
}

fun solvePart1(input: List<String>): Int {
    val cycles = listOf(20, 60, 100, 140, 180, 220)
    var x = 1
    var currentCycle = 1
    var signalStrengths = 0
    for (line in input) {
        if (line.startsWith("noop")) {
            if(cycles.contains(currentCycle)) { signalStrengths += currentCycle * x }
            currentCycle++
        } else {
            val amount = line.split(" ")[1].toInt()
            for (i in 0 .. 1) {
                if (cycles.contains(currentCycle)) { signalStrengths += currentCycle * x }
                if (i == 1) { x += amount }
                currentCycle++
            }
        }
    }
    return signalStrengths
}

