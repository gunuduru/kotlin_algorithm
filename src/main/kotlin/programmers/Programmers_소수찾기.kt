package main.kotlin.programmers

import main.kotlin.programmers.Programmers_소수찾기.Companion.solution
import kotlin.math.sqrt

class Programmers_소수찾기 {
    companion object {
        fun solution(numbers: String): Int {
            // 1. 종이 조각으로 숫자 만들기
            val result = mutableSetOf<Int>()
            val visited = BooleanArray(numbers.length)

            fun dfs(current: String) {
                // 현재 문자열이 비어있지 않다면 이를 추가
                if (current.isNotEmpty()) {
                    result.add(current.toInt())
                }

                for (i in numbers.indices) {
                    if (!visited[i]) {
                        visited[i] = true
                        dfs(current + numbers[i]) // 기존에 "01"이었다면 -> "01" + '1' 로 dfs
                        visited[i] = false // 현재 문자 방문 취소 -> 이어서 진행
                    }
                }
            }

            dfs("")


            // 2. 소수인지 판별
            return result.count { it.isPrime() }
        }


        private fun Int.isPrime(): Boolean {
            if (this < 2) return false

            for (i in 2..sqrt(this.toDouble()).toInt()) {
                if (this % i == 0) return false
            }

            return true
        }
    }
}

fun main() {
    val numbers = "17"

    println(solution(numbers))
}