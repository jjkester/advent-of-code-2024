package nl.jjkester.adventofcode24.day03

import nl.jjkester.adventofcode24.day03.model.Instruction
import nl.jjkester.adventofcode24.execution.isDay

object MullItOver {

    fun parseInstructions(input: String): List<Instruction> = Instruction.parse(input)

    fun sumOfResults(instructions: List<Instruction>): Int = instructions
        .filterIsInstance<Instruction.Mul>()
        .sumOf { it.result }

    fun sumOfConditionalResults(instructions: List<Instruction>): Int {
        var enabled = true

        return instructions.asSequence()
            .onEach {
                enabled = when (it) {
                    is Instruction.Do -> true
                    is Instruction.Dont -> false
                    else -> enabled
                }
            }
            .filterIsInstance<Instruction.Mul>()
            .sumOf { if (enabled) it.result else 0 }
    }

}

fun main() {
    MullItOver.isDay(3) {
        parsedWith { parseInstructions(it) } first { sumOfResults(it) } then { sumOfConditionalResults(it) }
    }
}
