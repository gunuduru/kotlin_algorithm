package main.kotlin.programmers

import main.kotlin.programmers.Programmers_순위.Companion.solution

class Programmers_순위 {
    companion object {
        fun solution(n: Int, results: Array<IntArray>): Int {
            val visited = BooleanArray(n + 1)
            val adj = Array(n + 1) { BooleanArray(n + 1) } // adj[a][b] = true -> a는 b에게 이겼다.
            val graph = Array(n + 1) { HashSet<Int>() } // graph[a] = {a에게 지는 선수들 (a 승리-> b 승리-> c 라면 b, c)}

            for ((a, b) in results) {
                adj[a][b] = true
            }

            // A에게 진 인원수 + A에게 이긴 인원수 = n-1 이라면, A는 결과값에 카운팅되어야 한다.
            // A에게 진 인원수 -> 각 선수별로 DFS해서 A가 나오는 인원수
            // A에게 이긴 인원수 -> A에서 DFS해서 나오는 모든 인원수

            /**
            @param stt dfs의 시작점 -> graph[stt]를 구하는 것이 목적
            @param cur 현재 dfs가 탐색중인 선수번호
             */
            fun dfs(stt: Int, cur: Int) {
                for (idx in (1..n)) {
                    if (!visited[idx] && adj[cur][idx]) {
                        // 미확인 선수이고, cur선수가 idx선수에게 이긴 상황이라면
                        graph[stt].add(idx)
                        visited[idx] = true
                        dfs(stt, idx) // idx 기준으로 dfs 진행
                    }
                }

            }

            for (idx in (1..n)) {
                visited.fill(false)
                dfs(idx, idx)
            }

            var result = 0 // 최종 반환값

            for (idx in (1..n)) {
                val idxAppearCnt = graph.count { hashset -> hashset.contains(idx) } // idx에게 진 인원수
                if (graph[idx].size + idxAppearCnt == n - 1) result++ /// graph[idx] = idx가 이긴 인원수
            }

            return result
        }
    }
}

fun main() {
    val n = 5
    val results = arrayOf(
        intArrayOf(4, 3),
        intArrayOf(4, 2),
        intArrayOf(3, 2),
        intArrayOf(1, 2),
        intArrayOf(2, 5)
    )

    println(solution(n, results))
}