package aoc.utils

import java.io.File
import kotlin.test.Test
import kotlin.test.assertEquals

internal class FileUtilsTest {

    private val subject: FileUtils = FileUtils

    @Test
    fun testReadInputFile() {
        val dir = System.getProperty("user.dir")
        val day = "day1"

        assertEquals(File("$dir/src/main/java/aoc/$day/input.txt"), subject.readInput(day))
    }
}