package nl.jjkester.adventofcode24.day05.model

import nl.jjkester.adventofcode24.predef.sections

class Instructions(val rules: List<Pair<Int, Int>>, val updates: List<List<Int>>) {

    val validUpdates by lazy { updates.filter { isUpdateValid(it) } }

    val correctedUpdates by lazy { updates.mapNotNull { update -> correctUpdate(update).takeIf { update != it } } }

    fun isUpdateValid(update: List<Int>): Boolean = rules.asSequence()
        .filter { rule -> rule.first in update && rule.second in update }
        .all { rule -> update.indexOf(rule.first) < update.indexOf(rule.second) }

    fun correctUpdate(update: List<Int>): List<Int> {
        var result = update.toMutableList()

        while (!isUpdateValid(result)) {
            repeat(result.size - 1) { i ->
                val first = result[i]
                val second = result[i + 1]

                if (second to first in rules) {
                    result[i] = second
                    result[i + 1] = first
                }
            }
        }

        return result
    }

    companion object {

        fun parse(input: String): Instructions {
            val (rulesInput, updatesInput) = input.sections()

            val rules = rulesInput.lineSequence()
                .map { it.split("|") }
                .map { (first, second) -> first.toInt() to second.toInt() }
                .toList()

            val updates = updatesInput.lineSequence()
                .map { line ->
                    line
                        .splitToSequence(",")
                        .map { it.toInt() }
                        .toList()
                }
                .toList()

            return Instructions(rules, updates)
        }
    }
}
