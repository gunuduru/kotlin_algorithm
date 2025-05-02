package main.kotlin.programmers

import main.kotlin.programmers.Programmers_방의개수.Companion.solution

class Programmers_방의개수 {
    companion object {
        fun solution(arrows: IntArray): Int {
            // arrow = i -> x + dx[i], y + dy[i]
            val dx = intArrayOf(0, 1, 1, 1, 0, -1, -1, -1)
            val dy = intArrayOf(1, 1, 0, -1, -1, -1, 0, 1)

            val visitedNodes = mutableSetOf<Pair<Int, Int>>() // 방문한 정점
            val visitedEdges = mutableSetOf<Pair<Pair<Int, Int>, Pair<Int, Int>>>() // 방문한 간선

            var x = 0
            var y = 0
            visitedNodes.add(Pair(x, y)) // 최초 시작 위치 (원점) 저장

            var answer = 0

            for (dir in arrows) {
                // ↗, ↘ 등 대각선 방향에서는 두 선이 중간에서 교차할 수 있다.
                // 예: (1,1) → (0,0), (1,0) → (0,1)는 (0.5,0.5)에서 교차함
                // 하지만 좌표는 정수이므로, 0.5 위치를 감지할 수 없다
                // → 이를 감지하기 위해 한 번의 이동을 2번(0.5칸씩) 나눠서 처리한다
                repeat(2) {
                    val nx = x + dx[dir]
                    val ny = y + dy[dir]
                    val current = Pair(x, y)
                    val next = Pair(nx, ny)

                    val edge = Pair(current, next)
                    val reverseEdge = Pair(next, current)

                    // 새로운 간선을 통해 이미 방문한 정점에 도달한 경우 → 방이 생김
                    if (next in visitedNodes && edge !in visitedEdges) {
                        answer++
                    }

                    visitedNodes.add(next)        // 다음 정점 방문 처리
                    visitedEdges.add(edge)        // 현재 방향 간선 방문 처리
                    visitedEdges.add(reverseEdge) // 반대 방향 간선도 함께 처리 (양방향 처리)

                    x = nx
                    y = ny
                }
            }

            return answer
        }
    }
}

fun main() {
    val arrows = intArrayOf(6, 6, 6, 4, 4, 4, 2, 2, 2, 0, 0, 0, 1, 6, 5, 5, 3, 6, 0)

    println(solution(arrows))
}