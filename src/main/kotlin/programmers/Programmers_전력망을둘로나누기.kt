package main.kotlin.programmers

import main.kotlin.programmers.Programmers_전력망을둘로나누기.Companion.solution
import java.util.*
import kotlin.math.abs
import kotlin.math.min

class Programmers_전력망을둘로나누기 {
    companion object {
        // 전선 하나를 끊어서 송전탑 개수가 가능한 비슷하도록 두 전력망으로 나눔
        // 전선을 하나씩 false로 바꿔서, bfs로 인접 송전탑 개수 확인
        fun solution(n: Int, wires: Array<IntArray>): Int {
            val adj = Array(n + 1) { BooleanArray(n + 1) } // adj[i][j] = true -> i와 j 송전탑 사이에 전선이 있다

            // 전선 세팅
            for ((v1, v2) in wires) {
                adj[v1][v2] = true
                adj[v2][v1] = true
            }

            /**
            bfs 후 두 전력망의 송전탑 개수의 차를 반환한다.
             */
            fun bfs(): Int {
                val cntList = mutableListOf<Int>()
                val visited = BooleanArray(n + 1)

                val queue: Deque<Int> = LinkedList()

                for (num in 1..n) {
                    if (visited[num]) continue

                    var cnt = 1 // 현재 bfs에서 방문한 노드의 수 (현재 노드부터 카운트 시작)
                    visited[num] = true
                    queue.add(num)
                    while (queue.isNotEmpty()) {
                        // cnt += queue.size // [수정1] 이번 bfs에서 방문한 노드들의 수를 cnt에 누적
                        // repeat(queue.size) { // [수정2] level-order BFS 방식을 굳이 사용하지 않아도 되므로 생략해보자. -> 큐에서 하나 꺼낼때마다 카운트하기만 하면 되므로
                        val front = queue.poll()
                        cnt++ // [수정1] cnt는 queue에서 빼낼때 증가시키는게 안전하다.
                        for (next in 1..n) {
                            if (adj[front][next] && !visited[next]) {
                                queue.add(next) // 인접 & 미방문 노드라면 queue에 추가
                                visited[next] = true
                            }
                        }
                        // }
                    }
                    cntList.add(cnt)
                }

                return abs(cntList[0] - cntList[1])
            }

            // 전선을 하나씩 끊어본 후 bfs 수행 -> 가장 절대값이 적은 경우를 찾는다.
            var result = Int.MAX_VALUE // 가장 큰 값을 초기값으로

            for ((v1, v2) in wires) {
                adj[v1][v2] = false
                adj[v2][v1] = false

                val curDiff = bfs()
                result = min(result, curDiff)

                // 원상복구
                adj[v1][v2] = true
                adj[v2][v1] = true
            }

            return result
        }
    }
}

fun main() {
    val n = 5
    val wires = arrayOf(intArrayOf(1, 2), intArrayOf(2, 3), intArrayOf(3, 4))

    println(solution(n, wires))
}