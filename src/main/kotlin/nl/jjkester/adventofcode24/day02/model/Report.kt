package nl.jjkester.adventofcode24.day02.model

import kotlin.math.absoluteValue

data class Report(val levels: List<Int>) {

    init {
        require(levels.size > 1) { "A report must contain at least two levels" }
    }

    fun isSafe(usingProblemDampener: Boolean = false): Boolean {
        val initiallySafe = isInitiallySafe(levels)

        return if (initiallySafe || !usingProblemDampener) {
            initiallySafe
        } else {
            (0..levels.lastIndex).asSequence()
                .map { index -> levels.toMutableList().apply { removeAt(index) } }
                .map { isInitiallySafe(it) }
                .firstOrNull { it } == true
        }
    }

    companion object {

        fun parse(input: String): Report = Report(input.split(" ").map(String::toInt))

        private fun isInitiallySafe(levels: List<Int>): Boolean {
            val observations = levels
                .asSequence()
                .zipWithNext()
                .map { Observation(it.first, it.second) }
                .toList()

            val firstDirection = observations.first().direction

            return observations.all { it.direction == firstDirection && it.deviation in 1..3 }
        }
    }

    private data class Observation(val first: Int, val second: Int) {

        val direction: Direction = when {
            first - second < 0 -> Direction.INCREASE
            first - second > 0 -> Direction.DECREASE
            else -> Direction.STABLE
        }

        val deviation: Int = (first - second).absoluteValue

        enum class Direction {
            INCREASE, DECREASE, STABLE
        }
    }
}
