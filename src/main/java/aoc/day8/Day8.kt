package aoc.day8

import aoc.utils.FileUtils

var matrix: List<List<Int>> = mutableListOf()

fun main() {
    val input = FileUtils.readInput("day8").readLines()
    matrix = input.map { it.toMutableList().map { it.toString().toInt() }.toList() }

    val rows = matrix.size
    val cols = matrix[0].size

    var value = rows * 2 + cols * 2 - 4
    for (row in 1 until rows - 1) {
        for(col in 1 until cols - 1) {
            value += if (isVisible(row, col, matrix[row][col])) 1 else 0
        }
    }

    println("Part 1: $value") //1717
    println("Part 2:  ${generateAmounts(rows, cols).map { it.maxOrNull() ?: 0 }.maxOf { it }}") //321975

}

fun generateAmounts(rows: Int, cols: Int): List<List<Int>> {
    val state: MutableList<MutableList<Int>> = mutableListOf(mutableListOf())
    for (i in 0 until rows) {
        state.add(mutableListOf())
    }
    for (row in 0 until rows) {
        for(col in 0 until cols) {
            if( row == 0 || row == rows - 1 || col == 0 || col == cols - 1) {
                state[row].add(0)
            } else {
                val up = up(row, col, matrix[row][col])
                val down = down(row, col, matrix[row][col])
                val left = left(row, col, matrix[row][col])
                val right = right(row, col, matrix[row][col])
                state[row].add(up * down * left * right)
            }
        }
    }
    return state
}

fun right(row: Int, col: Int, value: Int): Int {
    var current = 0
    for (v in getRightOf(row, col)) {
        current++
        if (v >= value) break
    }
    return current
}

fun left(row: Int, col: Int, value: Int): Int {
    var current = 0
    for (v in getLeftOf(row, col)) {
        current++
        if (v >= value) break
    }
    return current
}

fun up(row: Int, col: Int, value: Int): Int {
    var current = 0
    for(v in getUpOf(row, col)) {
        current++
        if (v >= value) break
    }
    return current
}

fun down(row: Int, col: Int, value: Int): Int {
    var current = 0
    for(v in getDownOf(row, col)) {
        current++
        if (v >= value) break
    }
    return current
}

fun isVisible(row: Int, col: Int, value: Int): Boolean {
    return getRightOf(row, col).all { it < value } ||
            getLeftOf(row, col).all { it < value } ||
            getUpOf(row, col).all { it < value } ||
            getDownOf(row, col).all { it < value }
}

fun getRightOf(row: Int, col: Int): List<Int> {
    return matrix[row].subList(col + 1, matrix[row].size)
}

fun getLeftOf(row: Int, col: Int): List<Int> {
    return matrix[row].subList(0, col).reversed()
}

fun getUpOf(row: Int, col: Int) : List<Int> {
    return (row - 1 downTo 0).map { matrix[it][col] }.toList()
}

fun getDownOf(row: Int, col: Int) : List<Int> {
    return (row + 1 until  matrix.size).map { matrix[it][col] }.toList()
}