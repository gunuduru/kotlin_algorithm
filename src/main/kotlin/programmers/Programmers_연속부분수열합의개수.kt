package main.kotlin.programmers

import main.kotlin.programmers.Programmers_연속부분수열합의개수.Companion.solution

class Programmers_연속부분수열합의개수 {
    companion object {
        fun solution(elements: IntArray): Int {
            val result = hashSetOf<Int>()
            val concatElements = elements + elements // 두개를 합침 -> 회전의 느낌을 제공
            val n = elements.size

            // 1 ~ n 범위만큼의 슬라이딩 윈도우
            for (len in 1..n) {
                for (stt in elements.indices) {
                    val curSum = (stt until stt + len).sumOf { concatElements[it] }
                    result.add(curSum)
                }
            }

            return result.size
        }
    }
}

fun main() {
    val elements = intArrayOf(7, 9, 1, 1, 4)

    println(solution(elements = elements))
}