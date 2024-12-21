package nl.jjkester.adventofcode24.day04

import assertk.assertThat
import assertk.assertions.isEqualTo
import org.junit.jupiter.api.Test

class CeresSearchTest {

    @Test
    fun parseWordSearch() {
        assertThat(CeresSearch.parseWordSearch(testInput))
            .transform { wordSearch ->
                wordSearch.rows.joinToString(System.lineSeparator()) { it.joinToString("") }
            }
            .isEqualTo(testInput)
    }

    @Test
    fun xmasCount() {
        assertThat(CeresSearch.xmasCount(parsedTestInput)).isEqualTo(18)
    }

    @Test
    fun xMasCount() {
        assertThat(CeresSearch.xMasCount(parsedTestInput)).isEqualTo(9)
    }

    companion object {
        private val testInput = """
            MMMSXXMASM
            MSAMXMSMSA
            AMXSXMAAMM
            MSAMASMSMX
            XMASAMXAMM
            XXAMMXXAMA
            SMSMSASXSS
            SAXAMASAAA
            MAMMMXMMMM
            MXMXAXMASX
        """.trimIndent()

        private val parsedTestInput by lazy(LazyThreadSafetyMode.NONE) {
            CeresSearch.parseWordSearch(testInput)
        }
    }
}
