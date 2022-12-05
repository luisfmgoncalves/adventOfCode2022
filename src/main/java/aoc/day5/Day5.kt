package aoc.day5

import aoc.utils.FileUtils
import java.nio.charset.StandardCharsets.UTF_8

fun main() {
    val input = FileUtils.readInput("day5").readText(UTF_8)

    //crates
    val crates = mutableListOf("RPCDBG", "HVG", "NSQDJPM", "PSLGDCNM", "JBNCPFLS", "QBDZVGTS", "BZMHFTQ", "CMDBF", "FCQG")
        .map { it.toList() }.toMutableList() // todo: parse this from the file

    //movements
    val actions = mutableListOf<Action>()
    """move (\d+) from (\d+) to (\d+)""".toRegex().findAll(input)
        .iterator().forEach { actions.add(
            Action(it.groupValues[1].toInt(),
            it.groupValues[2].toInt()-1,
            it.groupValues[3].toInt()-1))}

    val part1 = executeActions(crates.toMutableList(), actions, true)
        .joinToString("") { it.last().toString() }

    val part2 = executeActions(crates.toMutableList(), actions, false)
        .joinToString("") { it.last().toString() }

    println("Part 1: $part1")//TLNGFGMFN
    println("Part 2: $part2")//FGLQJCMBD
}

fun executeActions(crates: MutableList<List<Char>>, actions: MutableList<Action>, reverse: Boolean): MutableList<List<Char>> {
    actions.forEach {
        val toMove = crates[it.fromIndex].subList(crates[it.fromIndex].size - it.number, crates[it.fromIndex].size)
        crates[it.fromIndex] = crates[it.fromIndex].dropLast(it.number).toMutableList()
        crates[it.toIndex] += if (reverse) toMove.reversed() else toMove
    }
    return crates
}

class Action(val number: Int, val fromIndex: Int, val toIndex: Int )







