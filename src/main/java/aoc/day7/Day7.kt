package aoc.day7

import aoc.utils.FileUtils

fun main() {
    val input = FileUtils.readInput("day7").readLines()
    val root = buildDirectory(input)

    val part1 = root.find { it.getSize() <= 100000 }.sumOf { it.getSize() }
    val part2 = root.find { it.getSize() >= 30000000 - (70000000 - root.getSize()) }.minOf { it.getSize() }

    println("Part 1: $part1") //1325919
    println("Part 2: $part2") //2050735
}

val CD = """^\$ cd (.*)$""".toRegex()
val DIR = """^dir (.*)$""".toRegex()
val FILE = """^(\d+) (.*)$""".toRegex()

fun buildDirectory(input: List<String>): Folder {
    val root = Folder("/")
    var current = root
    for (line in input) {
        if(CD.matches(line)) {
            val moveTo = getMoveTo(line)
            current = when(moveTo) {
                ".." -> current.parent
                else -> current.findFolder(moveTo)
            } ?: current
        } else if(DIR.matches(line)) {
            val dirName = getDirName(line)
            current.addFolder(dirName)
        } else if(FILE.matches(line)) {
            val fileDetails = getFileDetails(line)
            current.addFile(fileDetails.second, fileDetails.first)
        }
    }
    return root
}

fun getMoveTo(instruction: String): String {
    return CD.find(instruction)?.groupValues?.get(1)
        ?: throw Exception("Could not parse 'cd' command in $instruction")
}

fun getDirName(instruction: String): String {
    return DIR.find(instruction)?.groupValues?.get(1)
        ?: throw Exception("Could not parse 'dir' name in $instruction")
}

fun getFileDetails(instruction: String): Pair<Int, String> {
    val matchResult = FILE.find(instruction)
    if(matchResult != null) {
        return Pair(matchResult.groupValues[1].toInt(), matchResult.groupValues[2])
    }
    throw Exception("Could not parse file details in $instruction")
}