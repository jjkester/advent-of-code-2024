package nl.jjkester.adventofcode24.day03.model

sealed class Instruction {

    data object Do : Instruction()

    data object Dont : Instruction()

    data class Mul(val first: Int, val second: Int) : Instruction() {

        val result: Int = first * second

        companion object {
            private val regex = Regex("""mul\((?<x>\d+),(?<y>\d+)\)""")

            fun parse(input: String): Mul {
                val (x, y) = requireNotNull(regex.matchEntire(input)?.destructured) { "Input is not a valid Mul instruction" }
                return Mul(x.toInt(), y.toInt())
            }
        }
    }

    companion object {
        private val regex = Regex("""(do\(\)|don't\(\)|mul\((\d+),(\d+)\))""", RegexOption.MULTILINE)

        fun parse(input: String): List<Instruction> = regex.findAll(input)
            .map { it.value }
            .map {
                when {
                    it.startsWith("do(") -> Do
                    it.startsWith("don't(") -> Dont
                    it.startsWith("mul(") -> Mul.parse(it)
                    else -> error("Unknown instruction")
                }
            }
            .toList()
    }
}
