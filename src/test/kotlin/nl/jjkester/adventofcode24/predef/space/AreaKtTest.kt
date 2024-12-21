package nl.jjkester.adventofcode24.predef.space

import assertk.assertThat
import assertk.assertions.containsExactly
import assertk.assertions.isEqualTo
import assertk.assertions.isFalse
import assertk.assertions.isTrue
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource
import org.junit.jupiter.params.provider.ValueSource
import org.mockito.kotlin.*
import kotlin.random.Random

class AreaKtTest {

    private val area: Area = mock()

    @ParameterizedTest
    @ValueSource(booleans = [true, false])
    fun `contains extension function delegates to contains on interface`(returnValue: Boolean) {
        val x = Random.nextInt()
        val y = Random.nextInt()
        val coordinate = coordinateOf(x, y)

        area.stub {
            on { contains(any(), any()) } doReturn returnValue
        }

        assertThat(area.contains(coordinate))
            .isEqualTo(returnValue)

        verify(area).contains(x, y)
        verifyNoMoreInteractions(area)
    }

    @Test
    fun `overlaps extension function is true when both ranges overlap`() {
        area.stub {
            on { x } doReturn 10..15
            on { y } doReturn 20..25
        }

        val other = mock<Area> {
            on { x } doReturn 9..14
            on { y } doReturn 21..26
        }

        assertThat(area.overlaps(other))
            .isTrue()
    }

    @Test
    fun `overlaps extension function is false when only x range overlaps`() {
        area.stub {
            on { x } doReturn 10..15
            on { y } doReturn 20..25
        }

        val other = mock<Area> {
            on { x } doReturn 9..14
            on { y } doReturn 26..31
        }

        assertThat(area.overlaps(other))
            .isFalse()
    }

    @Test
    fun `overlaps extension function is false when only y range overlaps`() {
        area.stub {
            on { x } doReturn 10..15
            on { y } doReturn 20..25
        }

        val other = mock<Area> {
            on { x } doReturn 4..9
            on { y } doReturn 21..26
        }

        assertThat(area.overlaps(other))
            .isFalse()
    }

    @Test
    fun `overlaps extension function is false when no ranges overlap`() {
        area.stub {
            on { x } doReturn 10..15
            on { y } doReturn 20..25
        }

        val other = mock<Area> {
            on { x } doReturn 4..9
            on { y } doReturn 26..31
        }

        assertThat(area.overlaps(other))
            .isFalse()
    }

    @ParameterizedTest
    @MethodSource("areaVectorsWithPaths")
    fun `pathSequence extension function returns expected paths`(
        area: Area,
        vector: Vector2D,
        expectedPaths: Array<List<Coordinate2D>>
    ) {
        assertThat(area.pathSequence(vector))
            .containsExactly(*expectedPaths)
    }

    companion object {

        @JvmStatic
        fun areaVectorsWithPaths(): Array<Arguments> = arrayOf(
            pathSequenceTestCase(
                area = RangeArea(1..5, 2..5),
                vector = vectorOf(1, 1),
                listOf(coordinateOf(1, 5)),
                listOf(coordinateOf(1, 4), coordinateOf(2, 5)),
                listOf(coordinateOf(1, 3), coordinateOf(2, 4), coordinateOf(3, 5)),
                listOf(coordinateOf(1, 2), coordinateOf(2, 3), coordinateOf(3, 4), coordinateOf(4, 5)),
                listOf(coordinateOf(2, 2), coordinateOf(3, 3), coordinateOf(4, 4), coordinateOf(5, 5)),
                listOf(coordinateOf(3, 2), coordinateOf(4, 3), coordinateOf(5, 4)),
                listOf(coordinateOf(4, 2), coordinateOf(5, 3)),
                listOf(coordinateOf(5, 2))
            ),
            pathSequenceTestCase(
                area = RangeArea(0..2, 5..7),
                vector = vectorOf(-1, 1),
                listOf(coordinateOf(0, 5)),
                listOf(coordinateOf(1, 5), coordinateOf(0, 6)),
                listOf(coordinateOf(2, 5), coordinateOf(1, 6), coordinateOf(0, 7)),
                listOf(coordinateOf(2, 6), coordinateOf(1, 7)),
                listOf(coordinateOf(2, 7))
            ),
            pathSequenceTestCase(
                area = RangeArea(-3..-2, -1..0),
                vector = vectorOf(1, -1),
                listOf(coordinateOf(-3, -1)),
                listOf(coordinateOf(-3, 0), coordinateOf(-2, -1)),
                listOf(coordinateOf(-2, 0)),
            ),
            pathSequenceTestCase(
                area = RangeArea(1..5, 2..5),
                vector = vectorOf(-1, -1),
                listOf(coordinateOf(1, 5)).reversed(),
                listOf(coordinateOf(1, 4), coordinateOf(2, 5)).reversed(),
                listOf(coordinateOf(1, 3), coordinateOf(2, 4), coordinateOf(3, 5)).reversed(),
                listOf(coordinateOf(1, 2), coordinateOf(2, 3), coordinateOf(3, 4), coordinateOf(4, 5)).reversed(),
                listOf(coordinateOf(2, 2), coordinateOf(3, 3), coordinateOf(4, 4), coordinateOf(5, 5)).reversed(),
                listOf(coordinateOf(3, 2), coordinateOf(4, 3), coordinateOf(5, 4)).reversed(),
                listOf(coordinateOf(4, 2), coordinateOf(5, 3)).reversed(),
                listOf(coordinateOf(5, 2)).reversed()
            ),
            pathSequenceTestCase(
                area = RangeArea(0..2, 3..4),
                vector = vectorOf(0, 1),
                listOf(coordinateOf(0, 3), coordinateOf(0, 4)),
                listOf(coordinateOf(1, 3), coordinateOf(1, 4)),
                listOf(coordinateOf(2, 3), coordinateOf(2, 4))
            ),
            pathSequenceTestCase(
                area = RangeArea(0..2, 3..4),
                vector = vectorOf(0, -1),
                listOf(coordinateOf(0, 3), coordinateOf(0, 4)).reversed(),
                listOf(coordinateOf(1, 3), coordinateOf(1, 4)).reversed(),
                listOf(coordinateOf(2, 3), coordinateOf(2, 4)).reversed()
            ),
            pathSequenceTestCase(
                area = RangeArea(0..2, 3..4),
                vector = vectorOf(1, 0),
                listOf(coordinateOf(0, 3), coordinateOf(1, 3), coordinateOf(2, 3)),
                listOf(coordinateOf(0, 4), coordinateOf(1, 4), coordinateOf(2, 4))
            ),
            pathSequenceTestCase(
                area = RangeArea(0..2, 3..4),
                vector = vectorOf(-1, 0),
                listOf(coordinateOf(0, 3), coordinateOf(1, 3), coordinateOf(2, 3)).reversed(),
                listOf(coordinateOf(0, 4), coordinateOf(1, 4), coordinateOf(2, 4)).reversed()
            ),
            pathSequenceTestCase(
                area = RangeArea(0..2, 0..4),
                vector = vectorOf(2, 3),
                listOf(coordinateOf(0, 4)),
                listOf(coordinateOf(0, 3)),
                listOf(coordinateOf(0, 2)),
                listOf(coordinateOf(0, 1), coordinateOf(2, 4)),
                listOf(coordinateOf(0, 0), coordinateOf(2, 3)),
                listOf(coordinateOf(1, 0)),
                listOf(coordinateOf(2, 0))
            )
        )

        private fun pathSequenceTestCase(area: Area, vector: Vector2D, vararg expectedPaths: List<Coordinate2D>) =
            Arguments.of(area, vector, expectedPaths)
    }
}
