package nl.jjkester.adventofcode24.day01

import assertk.all
import assertk.assertThat
import assertk.assertions.containsExactly
import assertk.assertions.isEqualTo
import nl.jjkester.adventofcode24.assertions.first
import nl.jjkester.adventofcode24.assertions.second
import org.junit.jupiter.api.Test

class HistorianHysteriaTest {

    @Test
    fun parseLists() {
        assertThat(HistorianHysteria.parseLists(testInput))
            .all {
                first().containsExactly(3, 4, 2, 1, 3, 3)
                second().containsExactly(4, 3, 5, 3, 9, 3)
            }
    }

    @Test
    fun totalDistanceBetweenLists() {
        assertThat(HistorianHysteria.totalDistanceBetweenLists(parsedTestInput.first, parsedTestInput.second))
            .isEqualTo(11)
    }

    @Test
    fun totalSimilarityScores() {
        assertThat(HistorianHysteria.totalSimilarityScore(parsedTestInput.first, parsedTestInput.second))
            .isEqualTo(31)
    }

    companion object {
        private val testInput = """
            3   4
            4   3
            2   5
            1   3
            3   9
            3   3
        """.trimIndent()

        private val parsedTestInput by lazy(LazyThreadSafetyMode.NONE) {
            HistorianHysteria.parseLists(testInput)
        }
    }
}
