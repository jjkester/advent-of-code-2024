package nl.jjkester.adventofcode24.predef.graph

/**
 * A vertex in a graph that can be changed after creation.
 *
 * @param T The type of element represented by this vertex.
 */
interface MutableVertex<T> : Vertex<T> {

    override var element: T
}
