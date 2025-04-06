package main.kotlin.programmers

import main.kotlin.programmers.Programmers_입국심사.Companion.solution

class Programmers_입국심사 {
    companion object {
        private var answer: Long = 0
        private var people = 0
        private var timesList: List<Long> = listOf()

        // n = 입국심사 기다리는 사람
        // times[i] = 심사관 i가 한명을 심사하는 데 걸리는 시간
        // 더 빨리 끝나는 심사대가 있으면 기다렸다가 그곳으로 가서 심사받음
        fun solution(n: Int, times: IntArray): Long {
            // 이분탐색 범위: 1 ~ (가장 느린 심사관이 모두 맡는 경우) = 1 ~ max(times) * n
            times.sort()
            people = n
            timesList = times.map { it.toLong() }

            binarySearch(1, timesList.last() * people)

            return answer
        }

        private fun binarySearch(min: Long, max: Long) {
            if (min > max) return

            val mid = (min + max) / 2

            val res = timesList.sumOf { mid / it } // 각 심사관이 mid분만큼의 시간동안 심사할 수 있는 인원의 총합

            if (res < people) binarySearch(mid + 1, max) // 더 많은 인원을 심사해야 하므로, 우측 시간 범위로 탐색
            else {
                answer = mid // 일단 현재의 최적 인원수
                binarySearch(min, mid - 1) // 좌측 시간범위로 탐색
            }
        }
    }
}

fun main() {
    val n = 6
    val times = intArrayOf(7, 10)

    println(solution(n, times))
}