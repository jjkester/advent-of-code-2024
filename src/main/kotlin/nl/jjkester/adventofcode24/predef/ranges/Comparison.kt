package nl.jjkester.adventofcode24.predef.ranges

/**
 * Whether this [ClosedRange] fully contains the [other] [ClosedRange].
 */
operator fun <T : Comparable<T>> ClosedRange<T>.contains(other: ClosedRange<T>): Boolean =
    other.start in this && other.endInclusive in this

/**
 * Whether this [ClosedRange] overlaps the [other] [ClosedRange].
 */
infix fun <T : Comparable<T>> ClosedRange<T>.overlaps(other: ClosedRange<T>): Boolean =
    other.start in this || other.endInclusive in this || start in other || endInclusive in other
