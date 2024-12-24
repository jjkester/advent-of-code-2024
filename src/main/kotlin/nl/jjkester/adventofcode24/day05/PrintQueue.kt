package nl.jjkester.adventofcode24.day05

import nl.jjkester.adventofcode24.day05.model.Instructions
import nl.jjkester.adventofcode24.execution.isDay

object PrintQueue {

    fun parseInstructions(input: String): Instructions = Instructions.parse(input)

    fun sumOfValidMiddlePages(instructions: Instructions): Int =
        instructions.validUpdates.sumOf { it[it.size / 2] }

    fun sumOfCorrectedMiddlePages(instructions: Instructions): Int =
        instructions.correctedUpdates.sumOf { it[it.size / 2] }
}

fun main() {
    PrintQueue.isDay(5) {
        parsedWith { parseInstructions(it) } first { sumOfValidMiddlePages(it) } then { sumOfCorrectedMiddlePages(it) }
    }
}
