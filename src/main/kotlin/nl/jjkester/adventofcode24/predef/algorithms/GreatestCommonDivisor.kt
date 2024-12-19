package nl.jjkester.adventofcode24.predef.algorithms

/**
 * Greatest common divisor algorithm to compute the greatest number that divides both [a] and [b].
 *
 * @param a First number.
 * @param b Second number.
 * @return The greatest common divisor of [a] and [b].
 */
fun gcd(a: Int, b: Int): Int = gcd(a.toLong(), b.toLong()).toInt()

/**
 * Greatest common divisor algorithm to compute the greatest number that divides both [a] and [b].
 *
 * @param a First number.
 * @param b Second number.
 * @return The greatest common divisor of [a] and [b].
 */
tailrec fun gcd(a: Long, b: Long): Long = if (b == 0L) a else gcd(b, a % b)
