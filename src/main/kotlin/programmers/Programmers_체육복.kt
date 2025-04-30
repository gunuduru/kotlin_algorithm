package main.kotlin.programmers

import main.kotlin.programmers.Programmers_체육복.Companion.solution

class Programmers_체육복 {
    companion object {
        // 바로 앞번호나 뒷번호 학생에게만 체육복을 빌려줄 수 있음
        fun solution(n: Int, lost: IntArray, reserve: IntArray): Int {
            val uniform = IntArray(n + 1) { 1 }
            uniform[0] = 0 // 0번 인덱스는 사용하지 않는다.
            for (num in lost) {
                uniform[num]--
            }

            for (num in reserve) {
                uniform[num]++
            }

            for (num in (1..n)) {
                if (uniform[num] == 0) {
                    // 체육복이 없으므로 앞뒤에서 가져와야 한다.
                    if (num > 1 && uniform[num - 1] == 2) {
                        uniform[num - 1]--
                        uniform[num]++
                    } else if (num < n && uniform[num + 1] == 2) {
                        uniform[num + 1]--
                        uniform[num]++
                    }
                }
            }

            return uniform.count { it > 0 }
        }
    }
}

fun main() {
    val n = 5
    val lost = intArrayOf(2, 4)
    val reserve = intArrayOf(1, 3, 5)

    println(solution(n, lost, reserve))
}