package nl.jjkester.adventofcode24.day03

import assertk.assertThat
import assertk.assertions.containsExactly
import assertk.assertions.isEqualTo
import nl.jjkester.adventofcode24.day03.model.Instruction
import org.junit.jupiter.api.Test

class MullItOverTest {

    @Test
    fun parseInstructions() {
        assertThat(MullItOver.parseInstructions(testInput)).containsExactly(
            Instruction.Mul(2, 4),
            Instruction.Dont,
            Instruction.Mul(5, 5),
            Instruction.Mul(11, 8),
            Instruction.Do,
            Instruction.Mul(8, 5)
        )
    }

    @Test
    fun sumOfResults() {
        assertThat(MullItOver.sumOfResults(parsedTestInput)).isEqualTo(161)
    }

    @Test
    fun sumOfConditionalResults() {
        assertThat(MullItOver.sumOfConditionalResults(parsedTestInput)).isEqualTo(48)
    }

    companion object {
        private val testInput = "xmul(2,4)&mul[3,7]!^don't()_mul(5,5)+mul(32,64](mul(11,8)undo()?mul(8,5))"

        private val parsedTestInput by lazy(LazyThreadSafetyMode.NONE) {
            MullItOver.parseInstructions(testInput)
        }
    }
}
