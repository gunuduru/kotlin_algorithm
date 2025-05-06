package main.kotlin.programmers

import main.kotlin.programmers.Programmers_징검다리건너기.Companion.solution

class Programmers_징검다리건너기 {
    companion object {
        /**
         * 최대 몇 명까지 징검다리를 건널 수 있는지 이진 탐색으로 계산
         * mid명이 건널 수 있으면 → 더 많은 인원 시도 (right 이동)
         * mid명이 못 건너면 → 더 적은 인원 시도 (left 이동)
         */
        fun solution(stones: IntArray, k: Int): Int {
            var left = 1 // 최소 인원: 1명 (0명은 항상 가능하므로 탐색 의미 없음)
            var right = stones.maxOrNull() ?: 0 // 최대 인원: 가장 튼튼한 돌이 버틸 수 있는 수
            var answer = 0

            while (left <= right) {
                val mid = (left + right) / 2
                if (canCross(stones, k, mid)) {
                    answer = mid // mid명은 건널 수 있음 → 더 많은 인원 가능성 확인
                    left = mid + 1
                } else {
                    right = mid - 1 // mid명이 못 건너면 → 인원 줄여서 시도
                }
            }

            return answer
        }

        /**
         * mid명이 건널 수 있는지 판단
         * → k개 이상 연속으로 밟을 수 없는 돌이 있으면 불가능
         */
        fun canCross(stones: IntArray, k: Int, mid: Int): Boolean {
            var skip = 0 // 연속으로 밟을 수 없는 돌의 개수

            for (stone in stones) {
                if (stone < mid) {
                    skip++
                    if (skip >= k) return false // k개 이상 연속으로 못 밟으면 건너지 못함
                } else {
                    skip = 0 // 연속 끊김
                }
            }

            return true
        }
    }
}

fun main() {
    val stones = intArrayOf(2, 4, 5, 3, 2, 1, 4, 2, 5, 1)
    val k = 3

    println(solution(stones, k))
}