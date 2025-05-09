package main.kotlin.programmers

import main.kotlin.programmers.Programmers_인사고과.Companion.solution

class Programmers_인사고과 {
    companion object {
        fun solution(scores: Array<IntArray>): Int {
            // 완호의 점수는 scores[0]
            val wanho = scores[0]
            val wanhoSum = wanho.sum()

            // 첫번째 숫자 기준으로 내림차순, 두번째 숫자 오름차순 정렬
            val sorted = scores.sortedWith(compareByDescending<IntArray> { it[0] }.thenBy { it[1] })

            var maxSecond = 0 // 현재까지의 두번째 숫자 중 최대값
            var isWanhoEliminated = false
            val survivors = mutableListOf<Int>() // 살아남은 사람들의 점수합 저장

            for(score in sorted) {
                val (first, second) = score

                // 지금까지 나온 최대 second보다 작으면 -> 이미 누군가에게 완패당함 -> 탈락
                if(second < maxSecond) {
                    // 만약 그게 완호라면 -> 아웃
                    if(score contentEquals wanho) {
                        isWanhoEliminated = true
                        break
                    }
                    continue // 생존자 리스트에 안넣음
                }

                // maxSecond 갱신
                maxSecond = maxOf(maxSecond, second)

                // 살아남았으니 점수합 저장
                survivors.add(score.sum())

            }

            if(isWanhoEliminated) return -1

            // 생존자 중 완호보다 점수합 높은 사람 수 -> 그 다음이 순위
            return survivors.count {it > wanhoSum} + 1
        }
    }
}

fun main() {
    val scores = arrayOf(intArrayOf(2,2), intArrayOf(1,4), intArrayOf(3,2), intArrayOf(3,2), intArrayOf(2,1))
    println(solution(scores))
}

