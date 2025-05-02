package main.kotlin.programmers

import main.kotlin.programmers.Programmers_섬연결하기.Companion.solution

class Programmers_섬연결하기 {
    companion object {
        fun solution(n: Int, costs: Array<IntArray>): Int {
            costs.sortBy { it[2] } // 비용 기준 오름차순 정렬

            val parent = IntArray(n) { it } // Union-Find 초기화 -> {0, 1, 2, ...} (it = index)

            /**
             * 경로 압축(Path Compression)
             * - x의 루트를 재귀적으로 찾고, 그 루트를 x의 부모로 설정함으로써 트리 깊이를 줄이는 최적화 기법
             * - 예: parent[3] = 2, parent[2] = 1, parent[1] = 0 → find(3) 호출 시 parent[3] = 0으로 경로 압축됨
             * - 이렇게 하면 이후 find 호출 시 O(1)에 가깝게 탐색 가능
             */
            fun find(x: Int): Int {
                if (parent[x] != x) parent[x] = find(parent[x])
                return parent[x]
            }


            fun union(a: Int, b: Int) {
                val pa = find(a)
                val pb = find(b)
                if (pa != pb) parent[pa] = pb // 두 집합을 하나로 합침
                // pa와 pb 중 어떤 루트를 부모로 삼든 상관없지만, 구현 내에서 일관된 방식으로 처리해야 함
            }

            var answer = 0

            // 크루스칼 알고리즘의 핵심 루프
            // - 비용이 작은 간선부터 순회
            // - 두 섬의 루트가 다르면 union으로 연결 (싸이클 방지)
            // - union이 일어날 때마다 해당 비용을 정답에 누적
            // - MST는 n-1개의 간선이 연결되면 완성되므로, 전체 연결이 완료되면 더 이상 union이 발생하지 않음
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