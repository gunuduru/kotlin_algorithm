package main.kotlin.programmers

import main.kotlin.programmers.Programmers_피로도.Companion.solution

class Programmers_피로도 {
    companion object {
        // 피로도 수치 = Fatigue Point = FP
        fun solution(k: Int, dungeons: Array<IntArray>): Int {
            val dungeonsCnt = dungeons.size
            val visited = BooleanArray(dungeonsCnt)
            var answer: Int = -1

            /**
            @param cnt = 현재까지 다녀온 던전의 개수
            @param leftFP = 남은 피로도 수치
             */
            fun dfs(cnt: Int, leftFP: Int) {
                if (cnt > answer) answer = cnt // 최대 던전 수 최신화

                dungeons.forEachIndexed { idx, dungeon ->
                    // 일단 미방문 던전이면 방문
                    if (!visited[idx]) {
                        visited[idx] = true

                        if (leftFP >= dungeon[0]) {
                            // 만약 탐험 가능한 던전이라면, 탐험하여 피로도 갱신한 뒤 이어서 dfs
                            dfs(cnt + 1, leftFP - dungeon[1])
                        } else {
                            // 탐험 불가능한 던전이라면, 다른 던전 확인
                            dfs(cnt, leftFP)
                        }
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