package nl.jjkester.adventofcode24.day04.model

import assertk.all
import assertk.assertThat
import assertk.assertions.containsExactly
import assertk.assertions.containsExactlyInAnyOrder
import assertk.assertions.isEqualTo
import nl.jjkester.adventofcode24.predef.space.coordinateOf
import org.junit.jupiter.api.Test

class WordSearchTest {

    @Test
    fun parse() {
        val input = """
            ABC
            DEF
        """.trimIndent()

        assertThat(WordSearch.parse(input)).all {
            transform { it.x }.isEqualTo(0..2)
            transform { it.y }.isEqualTo(0..1)
            transform { it.rows }.containsExactly(
                listOf('A', 'B', 'C'),
                listOf('D', 'E', 'F')
            )
        }
    }

    @Test
    fun findAll() {
        val input = """
            ..X...
            .SAMX.
            .A..A.
            XMAS.S
            .X....
        """.trimIndent()

        assertThat(WordSearch.parse(input).findAll("XMAS")).containsExactlyInAnyOrder(
            listOf(coordinateOf(2, 0), coordinateOf(3, 1), coordinateOf(4, 2), coordinateOf(5, 3)),
            listOf(coordinateOf(4, 1), coordinateOf(3, 1), coordinateOf(2, 1), coordinateOf(1, 1)),
            listOf(coordinateOf(0, 3), coordinateOf(1, 3), coordinateOf(2, 3), coordinateOf(3, 3)),
            listOf(coordinateOf(1, 4), coordinateOf(1, 3), coordinateOf(1, 2), coordinateOf(1, 1))
        )
    }
}
