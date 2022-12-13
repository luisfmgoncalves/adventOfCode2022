package aoc.day11

import aoc.utils.FileUtils

var monkeys: Map<Int, Monkey> = mapOf()

fun main() {
    monkeys = FileUtils.readInput("day11").readLines()
        .windowed(6, 7){ Monkey(it) }.toList()
        .mapIndexed { index: Int, monkey:Monkey -> index to monkey }
        .toMap()

//    runRounds(20) { it / 3 }
//    val part1 = monkeys.values.map { it.inspected }.sortedDescending().subList(0, 2).reduce {acc, i -> acc * i}
//    println("Part 1: $part1")

    val common = monkeys.values.fold(1L) { acc, monkey -> acc * monkey.test }
    runRounds(10000) { it % common }
    val part2 = monkeys.values.map { it.inspected }.sortedDescending().let { it[0].toLong() * it[1].toLong() }
    println("Part 2: $part2")
}

fun runRounds(num: Int, toWorryLevel: (Long) -> Long) {
    for (i in 0 until num) {
        for(monkey in monkeys.values) {
            val iterator = monkey.items.iterator()
            while (iterator.hasNext()) {
                val recipient = monkey.inspect(iterator.next(), toWorryLevel)
                monkeys[recipient.first]?.items?.add(recipient.second)
            }
            monkey.items.clear()
        }
    }
    printCurrentState()
    println("----------------------------------")
}

fun printCurrentState() {
    for((key, value) in monkeys) {
        println("$key ->. ${value.items}, inspections: ${value.inspected}")
    }
}

data class Monkey(var lines: List<String>) {
    var items = parseItems(lines[1])
    val operation = parseOperation(lines[2])
    val test = parseTest(lines[3])
    private val monkeys = listOf(parseMonkey(lines[4]), parseMonkey(lines[5]))
    var inspected = 0

    fun inspect(old: Long, toWorryLevel: (Long) -> Long): Pair<Int,Long> {
        val worryLevel = toWorryLevel(operation(old))
        val throwToMonkey = if(worryLevel % test == 0L) monkeys[0] else monkeys[1]
        inspected++
        return Pair(throwToMonkey, worryLevel)
    }

    private fun parseItems(items: String): MutableList<Long> {
        return Regex("\\d+").findAll(items.trim()).map { it.value }.toList().map { it.toLong() }.toMutableList()
    }

    private fun parseOperation(line: String): (Long) -> Long {
        val value = line.substringAfterLast(" ")
        return when {
            value == "old" -> ({ it * it })
            line.contains("*") -> ({ it * value.toLong() })
            else -> ({ it + value.toLong() })
        }
    }

    private fun parseTest(items: String): Long {
        return items.substringAfterLast(" ").toLong()
    }

    private fun parseMonkey(items: String): Int {
        return items.substringAfterLast(" ").toInt()
    }
}
