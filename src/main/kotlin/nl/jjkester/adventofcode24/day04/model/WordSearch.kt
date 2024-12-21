package nl.jjkester.adventofcode24.day04.model

import nl.jjkester.adventofcode24.predef.space.Coordinate2D
import nl.jjkester.adventofcode24.predef.space.MultikAreaMap
import nl.jjkester.adventofcode24.predef.space.Vector2D
import nl.jjkester.adventofcode24.predef.space.get
import nl.jjkester.adventofcode24.predef.space.pathSequence
import nl.jjkester.adventofcode24.predef.space.vectorOf
import org.jetbrains.kotlinx.multik.api.Multik
import org.jetbrains.kotlinx.multik.api.d2arrayIndices
import org.jetbrains.kotlinx.multik.ndarray.data.D2Array

class WordSearch private constructor(data: D2Array<Int>) : MultikAreaMap<Char, Int>(data) {

    override fun serialize(element: Char): Int = element.code

    override fun deserialize(value: Int): Char = value.toChar()

    fun findAll(word: String, vectors: Iterable<Vector2D> = searchVectors): Sequence<List<Coordinate2D>> {
        val regex = Regex.fromLiteral(word)
        return vectors.asSequence()
            .flatMap { pathSequence(it) }
            .flatMap { path -> regex
                .findAll(path.joinToString("") { this[it].toString() })
                .map { path.subList(it.range.first, it.range.last + 1) }
            }
    }

    companion object {
        private val searchVectors = listOf(
            vectorOf(1, 0),
            vectorOf(0, 1),
            vectorOf(-1, 0),
            vectorOf(0, -1),
            vectorOf(1, 1),
            vectorOf(1, -1),
            vectorOf(-1, 1),
            vectorOf(-1, -1)
        )

        fun parse(input: String): WordSearch {
            val contents = input.lines()
            val width = contents.maxOf { it.length }
            val height = contents.size

            return WordSearch(
                Multik.d2arrayIndices(width, height) { x, y -> contents[y][x].code }
            )
        }
    }
}
