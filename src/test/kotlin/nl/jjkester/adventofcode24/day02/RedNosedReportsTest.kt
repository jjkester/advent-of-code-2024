package nl.jjkester.adventofcode24.day02

import assertk.assertThat
import assertk.assertions.containsExactly
import assertk.assertions.isEqualTo
import nl.jjkester.adventofcode24.day02.model.Report
import org.junit.jupiter.api.Test

class RedNosedReportsTest {

    @Test
    fun parseReports() {
        assertThat(RedNosedReports.parseReports(testInput)).containsExactly(
            Report(listOf(7, 6, 4, 2, 1)),
            Report(listOf(1, 2, 7, 8, 9)),
            Report(listOf(9, 7, 6, 2, 1)),
            Report(listOf(1, 3, 2, 4, 5)),
            Report(listOf(8, 6, 4, 4, 1)),
            Report(listOf(1, 3, 6, 7, 9))
        )
    }

    @Test
    fun countSafeReportsWithoutProblemDampener() {
        assertThat(RedNosedReports.countSafeReports(parsedTestInput, false)).isEqualTo(2)
    }

    @Test
    fun countSafeReportsWithProblemDampener() {
        assertThat(RedNosedReports.countSafeReports(parsedTestInput, true)).isEqualTo(4)
    }

    companion object {
        val testInput = """
            7 6 4 2 1
            1 2 7 8 9
            9 7 6 2 1
            1 3 2 4 5
            8 6 4 4 1
            1 3 6 7 9
        """.trimIndent()

        val parsedTestInput by lazy(LazyThreadSafetyMode.NONE) {
            RedNosedReports.parseReports(testInput)
        }
    }
}
