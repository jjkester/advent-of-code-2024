package nl.jjkester.adventofcode24.day02.model

import assertk.assertThat
import assertk.assertions.isEqualTo
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource

class ReportTest {

    @ParameterizedTest
    @MethodSource("levels")
    fun isSafe(levels: List<Int>, usingProblemDampener: Boolean, expected: Boolean) {
        assertThat(Report(levels).isSafe(usingProblemDampener)).isEqualTo(expected)
    }

    companion object {

        @JvmStatic
        fun levels() = arrayOf(
            Arguments.of(listOf(7, 6, 4, 2, 1), false, true),
            Arguments.of(listOf(1, 2, 7, 8, 9), false, false),
            Arguments.of(listOf(9, 7, 6, 2, 1), false, false),
            Arguments.of(listOf(1, 3, 2, 4, 5), false, false),
            Arguments.of(listOf(8, 6, 4, 4, 1), false, false),
            Arguments.of(listOf(1, 3, 6, 7, 9), false, true),
            Arguments.of(listOf(7, 6, 4, 2, 1), true, true),
            Arguments.of(listOf(1, 2, 7, 8, 9), true, false),
            Arguments.of(listOf(9, 7, 6, 2, 1), true, false),
            Arguments.of(listOf(1, 3, 2, 4, 5), true, true),
            Arguments.of(listOf(8, 6, 4, 4, 1), true, true),
            Arguments.of(listOf(1, 3, 6, 7, 9), true, true)
        )
    }
}
