package nl.jjkester.adventofcode24.predef.space

import nl.jjkester.adventofcode24.predef.ranges.overlaps
import nl.jjkester.adventofcode24.predef.ranges.size

/**
 * An area in a two-dimensional space.
 */
interface Area {

    /**
     * Range on the x-axis that is covered by this area.
     */
    val x: IntRange

    /**
     * Range on the y-axis that is covered by this area.
     */
    val y: IntRange

    /**
     * Size of the area.
     */
    val size: Int

    /**
     * Returns whether this area is empty.
     *
     * @return Whether this area is empty.
     */
    fun isEmpty(): Boolean

    /**
     * Returns whether the [x] and [y] coordinate is within the bounds of this area.
     *
     * @param x Point on the x-axis.
     * @param y Point on the y-axis.
     * @return Whether the [x] and [y] coordinate is within the bounds of this area.
     */
    fun contains(x: Int, y: Int): Boolean
}

/**
 * Returns whether the [coordinate] is within the bounds of this area.
 *
 * @param coordinate Coordinate on the x- and y-axes.
 * @return Whether the [coordinate] is within the bounds of this area.
 */
operator fun Area.contains(coordinate: Coordinate2D): Boolean = contains(coordinate.x, coordinate.y)

/**
 * Returns whether the [other] area overlaps with this area.
 *
 * @param other The area to check overlap with.
 * @return Whether the [other] area overlaps with this area.
 */
infix fun Area.overlaps(other: Area): Boolean = x.overlaps(other.x) && y.overlaps(other.y)

/**
 * Returns a sequence of paths from edge to edge of the area in the direction of the [vector].
 *
 * @param vector The direction of paths.
 * @return Paths over the area in the direction of the [vector].
 */
fun Area.pathSequence(vector: Vector2D): Sequence<List<Coordinate2D>> {
    val start = listOf(coordinateOf(x.first, y.first))
    val highX = listOf(coordinateOf(x.last, y.first))
    val highY = listOf(coordinateOf(x.first, y.last))
    val end = listOf(coordinateOf(x.last, y.last))

    val (startIncX, endIncX) = if (x.size < 3) {
        emptyList<Coordinate2D>() to emptyList<Coordinate2D>()
    } else {
        (x.first + 1..x.last - 1).run {
            map { coordinateOf(it, y.first) } to map { coordinateOf(it, y.last) }
        }
    }
    val (startIncY, endIncY) = if (y.size < 3) {
        emptyList<Coordinate2D>() to emptyList<Coordinate2D>()
    } else {
        (y.first + 1..y.last - 1).run {
            map { coordinateOf(x.first, it) } to map { coordinateOf(x.last, it) }
        }
    }

    val startCoordinates = when {
        vector.x > 0 && vector.y > 0 -> highY + startIncY.asReversed() + start + startIncX + highX
        vector.x > 0 && vector.y < 0 -> start + startIncY + highY + endIncX + end
        vector.x < 0 && vector.y > 0 -> start + startIncX + highX + endIncY + end
        vector.x < 0 && vector.y < 0 -> highY + endIncX + end + endIncY.asReversed() + highX
        vector.x == 0 && vector.y > 0 -> start + startIncX + highX
        vector.x == 0 && vector.y < 0 -> highY + endIncX + end
        vector.x > 0 && vector.y == 0 -> start + startIncY + highY
        vector.x < 0 && vector.y == 0 -> highX + endIncY + end
        else -> emptyList<Coordinate2D>()
    }

    return startCoordinates.asSequence()
        .map { startCoordinate ->
            generateSequence(startCoordinate) { it + vector }
                .takeWhile { it in this }
                .toList()
        }
}
