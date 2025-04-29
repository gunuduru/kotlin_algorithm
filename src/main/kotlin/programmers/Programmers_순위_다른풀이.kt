package main.kotlin.programmers

import main.kotlin.programmers.Programmers_순위_다른풀이.Companion.solution

class Programmers_순위_다른풀이 {
    companion object {
        fun solution(n: Int, results: Array<IntArray>): Int {
            val win = Array(n + 1) { mutableSetOf<Int>() } // win[a] = {a가 이기는 선수들}
            val lose = Array(n + 1) { mutableSetOf<Int>() } // lose[a] = {a가 지는 선수들}

            for ((a, b) in results) {
                win[a].add(b)
                lose[b].add(a)
            }

            // A에게 진 인원수 + A에게 이긴 인원수 = n-1 이라면, A는 결과값에 카운팅되어야 한다.
            // A에게 진 인원수 -> 각 선수별로 DFS해서 A가 나오는 인원수
            // A에게 이긴 인원수 -> A에서 DFS해서 나오는 모든 인원수

            fun dfs(cur: Int, arr: Array<MutableSet<Int>>, visited: BooleanArray) {
                for (next in arr[cur].toList()) {
                    if (!visited[next]) {
                        visited[next] = true
                        dfs(next, arr, visited)
                        arr[cur].addAll(arr[next])
                    }
                }
            }

            for (idx in (1..n)) {
                dfs(idx, win, BooleanArray(n + 1))
                dfs(idx, lose, BooleanArray(n + 1))
            }

            var result = 0 // 최종 반환값

            for (idx in (1..n)) {
                if (win[idx].size + lose[idx].size == n - 1) result++
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