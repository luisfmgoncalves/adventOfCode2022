package aoc.utils

import java.io.File

object FileUtils {

    fun readInput(day: String): File {
        val dir = System.getProperty("user.dir")
        return File("$dir/src/main/java/aoc/$day/input.txt")
    }

}