package nl.jjkester.adventofcode24.day04

import nl.jjkester.adventofcode24.day04.model.WordSearch
import nl.jjkester.adventofcode24.execution.isDay
import nl.jjkester.adventofcode24.predef.space.coordinateOf
import nl.jjkester.adventofcode24.predef.space.vectorOf

object CeresSearch {

    private val diagonalVectors = listOf(
        vectorOf(1, 1),
        vectorOf(1, -1),
        vectorOf(-1, 1),
        vectorOf(-1, -1)
    )

    fun parseWordSearch(input: String): WordSearch = WordSearch.parse(input)

    fun xmasCount(wordSearch: WordSearch): Int = wordSearch.findAll("XMAS").count()

    fun xMasCount(wordSearch: WordSearch): Int = sequence {
        val results = wordSearch.findAll("MAS", diagonalVectors).toMutableList()

        while (results.isNotEmpty()) {
            val result = results.removeFirst()
            val (first, middle, last) = result
            val (corner1, corner2) = listOf(
                coordinateOf(middle.x + 1, middle.y + 1),
                coordinateOf(middle.x + 1, middle.y - 1),
                coordinateOf(middle.x - 1, middle.y + 1),
                coordinateOf(middle.x - 1, middle.y - 1)
            ) - first - last
            val contra = listOf(corner1, middle, corner2)

            if (results.remove(contra) || results.remove(contra.asReversed())) {
                yield(result to contra)
            }
        }
    }.count()
}

fun main() {
    CeresSearch.isDay(4) {
        parsedWith { parseWordSearch(it) } first { xmasCount(it) } then { xMasCount(it) }
    }
}
