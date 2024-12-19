package nl.jjkester.adventofcode24.day01

import nl.jjkester.adventofcode24.execution.isDay
import kotlin.math.absoluteValue

/**
 * https://adventofcode.com/2024/day/1
 */
object HistorianHysteria {

    fun parseLists(input: String): Pair<List<Int>, List<Int>> = input.lineSequence()
        .map { it.split("""\s+""".toRegex()).map(String::toInt) }
        .map { it.first() to it.last() }
        .unzip()

    fun totalDistanceBetweenLists(lists: Pair<List<Int>, List<Int>>): Int =
        totalDistanceBetweenLists(lists.first, lists.second)

    fun totalDistanceBetweenLists(first: List<Int>, second: List<Int>): Int =
        first.sorted()
            .zip(second.sorted())
            .sumOf { (f, s) -> (f - s).absoluteValue }

    fun totalSimilarityScore(lists: Pair<List<Int>, List<Int>>): Int = totalSimilarityScore(lists.first, lists.second)

    fun totalSimilarityScore(first: List<Int>, second: List<Int>): Int = first
        .sumOf { it * second.count { i -> i == it } }
}

fun main() {
    HistorianHysteria.isDay(1) {
        parsedWith { parseLists(it) } first { totalDistanceBetweenLists(it) } then { totalSimilarityScore(it) }
    }
}
