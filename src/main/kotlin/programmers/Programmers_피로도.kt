package main.kotlin.programmers

import main.kotlin.programmers.Programmers_피로도.Companion.solution

class Programmers_피로도 {
    companion object {
        // 피로도 수치 = Fatigue Point = FP
        fun solution(k: Int, dungeons: Array<IntArray>): Int {
            val dungeonsCnt = dungeons.size
            val visited = BooleanArray(dungeonsCnt)
            var answer = 0

            /**
            @param cnt = 현재까지 다녀온 던전의 개수
            @param leftFP = 남은 피로도 수치
             */
            fun dfs(cnt: Int, leftFP: Int) {
                answer = maxOf(cnt, answer) // 최대 던전 수 최신화

                for (idx in 0 until dungeonsCnt) {
                    if (!visited[idx] && leftFP >= dungeons[idx][0]) {
                        visited[idx] = true
                        dfs(cnt + 1, leftFP - dungeons[idx][1])
                        visited[idx] = false
                    }
                }
            }

            dfs(0, k)
            return answer
        }
    }
}

fun main() {
    val k = 80
    val dungeons = arrayOf(intArrayOf(80, 20), intArrayOf(50, 40), intArrayOf(30, 10))

    println(solution(k, dungeons))
}