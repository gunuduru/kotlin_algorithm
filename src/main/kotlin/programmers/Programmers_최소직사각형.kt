package main.kotlin.programmers

import main.kotlin.programmers.Programmers_최소직사각형.Companion.solution
import kotlin.math.max
import kotlin.math.min

class Programmers_최소직사각형 {
    companion object {
        fun solution(sizes: Array<IntArray>): Int {
            var maxWidth = 0
            var maxHeight = 0

            sizes.forEach { size ->
                // 긴 것을 width로, 짧은 것을 height로 둔다 (왜냐면 회전 가능하므로)
                val w = max(size[0], size[1])
                val h = min(size[0], size[1])

                maxWidth = max(maxWidth, w)
                maxHeight = max(maxHeight, h)
            }

            return maxWidth * maxHeight
        }
    }
}

fun main() {
    val sizes = arrayOf(intArrayOf(60, 50), intArrayOf(30, 70), intArrayOf(60, 30), intArrayOf(80, 40))

    println(solution(sizes))
}