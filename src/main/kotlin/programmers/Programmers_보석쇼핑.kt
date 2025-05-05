package main.kotlin.programmers

import main.kotlin.programmers.Programmers_보석쇼핑.Companion.solution

class Programmers_보석쇼핑 {
    companion object {
        fun solution(gems: Array<String>): IntArray {
            val gemTypes = gems.toSet().size // 전체 보석 종류 수
            val gemCount = mutableMapOf<String, Int>() // 현재 구간 내 보석별 개수 추적
            var answer = intArrayOf(0, gems.size - 1) // 초기값: 전체 구간

            var left = 0
            for (right in gems.indices) {
                // 오른쪽 포인터 확장: 보석 추가
                gemCount[gems[right]] = gemCount.getOrDefault(gems[right], 0) + 1

                // 모든 종류의 보석이 포함된 경우, 구간 최소화 시도
                while (gemCount.size == gemTypes) {
                    // 현재 구간이 더 짧으면 정답 갱신
                    if (right - left < answer[1] - answer[0]) {
                        answer = intArrayOf(left, right)
                    }

                    // 왼쪽 보석 제거 시도
                    gemCount[gems[left]] = gemCount[gems[left]]!! - 1
                    if (gemCount[gems[left]] == 0) {
                        gemCount.remove(gems[left]) // 해당 종류 보석이 0개면 map에서 제거
                    }
                    left++ // 왼쪽 포인터 이동
                }
            }

            // 문제는 1-based indexing 요구
            return intArrayOf(answer[0] + 1, answer[1] + 1)
        }
    }
}

fun main() {
    val gems = arrayOf("DIA", "RUBY", "RUBY", "DIA", "DIA", "EMERALD", "SAPPHIRE", "DIA")

    println(solution(gems).joinToString())
}