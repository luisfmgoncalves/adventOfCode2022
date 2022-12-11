package aoc.day9

import aoc.utils.FileUtils
import kotlin.math.sign

fun main() {
    val input = FileUtils.readInput("day9").readLines()
        .map { it.split(" ") }.map { it[0].repeat(it[1].toInt()) }.toList()

    var rope = Rope(2)
    input.map { rope.move(it) }

    println("Part 1: ${rope.tailMoves.count()}") //6044

    rope = Rope(10)
    input.map { rope.move(it) }

    println("Part 2: ${rope.tailMoves.count()}") //2384
}

open class Rope(knobsNum: Int) {
    private var knobs: MutableList<Pair<Int, Int>> = MutableList(knobsNum) { Pair(0,0) }
    var tailMoves: MutableList<Pair<Int, Int>> = mutableListOf(Pair(0, 0))

    fun move(section: String) {
        section.toList().map {
            moveHead(it)
            knobs.windowed(2, 1) { (head, tail) ->
                if(!isNextTo(head, tail)) {
                    moveTail(head, tail, knobs.indexOf(tail))
                    if(knobs.last() !in tailMoves) { tailMoves.add(knobs.last()) }
                }
            }
        }
    }

    private fun moveHead(dir: Char) {
        val head = knobs[0]
        knobs[0] = when(dir) {
            'U' -> Pair(head.first, head.second.plus(1))
            'D' -> Pair(head.first, head.second.minus(1))
            'R' -> Pair(head.first.plus(1), head.second)
            'L' -> Pair(head.first.minus(1), head.second)
            else -> throw Exception("unknown direction: $dir")
        }
    }

    private fun moveTail(head: Pair<Int, Int>, tail: Pair<Int, Int>, tailIndex: Int) {
        knobs[tailIndex] = Pair(
            (head.first - tail.first).sign + tail.first,
            (head.second - tail.second).sign + tail.second)
    }

    private fun isNextTo(a: Pair<Int, Int>, b: Pair<Int, Int>): Boolean {
        return a.first - b.first <= 1 && a.second - b.second <= 1 &&
                b.first - a.first <= 1 && b.second - a.second <= 1
    }
}

