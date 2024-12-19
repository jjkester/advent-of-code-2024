package nl.jjkester.adventofcode24.day02

import nl.jjkester.adventofcode24.day02.model.Report
import nl.jjkester.adventofcode24.execution.isDay

/**
 * https://adventofcode.com/2024/day/2
 */
object RedNosedReports {

    fun parseReports(input: String): List<Report> = input.lineSequence()
        .map(Report::parse)
        .toList()

    fun countSafeReports(reports: List<Report>, usingProblemDampener: Boolean): Int =
        reports.count { it.isSafe(usingProblemDampener) }
}

fun main() {
    RedNosedReports.isDay(2) {
        parsedWith { parseReports(it) } first { countSafeReports(it, false) } then { countSafeReports(it, true) }
    }
}
