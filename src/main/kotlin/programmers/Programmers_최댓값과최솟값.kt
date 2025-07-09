package main.kotlin.programmers

import main.kotlin.programmers.Programmers_최댓값과최솟값.Companion.solution
import java.util.*

class Programmers_최댓값과최솟값 {
    companion object {
        fun solution(s: String): String {
            val st = StringTokenizer(s, " ")
            val numList = mutableListOf<Int>()

            while (st.hasMoreTokens()) {
                numList.add(st.nextToken().toInt())
            }

            val max = numList.maxOrNull()!!
            val min = numList.minOrNull()!!

            return "$min $max"
        }
    }
}

fun main() {
//    val s = "1 2 3 4"
//    val s = "-1 -2 -3 -4"
    val s = "-1 -1"

    println(solution(s))
}