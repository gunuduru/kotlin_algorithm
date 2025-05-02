package main.kotlin.programmers

import main.kotlin.programmers.Programmers_섬연결하기.Companion.solution

class Programmers_섬연결하기 {
    companion object {
        fun solution(n: Int, costs: Array<IntArray>): Int {
            costs.sortBy { it[2] } // 비용 기준 정렬

            val parent = IntArray(n) { it } // Union-Find 초기화

            fun find(x: Int): Int {
                if (parent[x] != x) parent[x] = find(parent[x])
                return parent[x]
            }

            fun union(a: Int, b: Int) {
                val pa = find(a)
                val pb = find(b)
                if (pa != pb) parent[pa] = pb
            }

            var answer = 0
            for ((from, to, cost) in costs) {
                if (find(from) != find(to)) {
                    union(from, to)
                    answer += cost
                }
            }

            return answer
        }
    }
}

fun main() {
    val n = 4
    val costs =
        arrayOf(intArrayOf(0, 1, 1), intArrayOf(0, 2, 2), intArrayOf(1, 2, 5), intArrayOf(1, 3, 1), intArrayOf(2, 3, 8))

    println(solution(n, costs))
}