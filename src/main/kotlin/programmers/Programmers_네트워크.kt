package main.kotlin.programmers

import java.util.*

class Programmers_네트워크 {
    companion object {
        fun solution(n: Int, computers: Array<IntArray>): Int {
            val adjMap = mutableMapOf<Int, MutableSet<Int>>()
            (0 until n).forEach { num -> adjMap[num] = mutableSetOf() }

            computers.forEachIndexed { cptIdx, adj ->
                adj.forEachIndexed { adjIdx, isConnected ->
                    if (isConnected == 1 && cptIdx != adjIdx) {
                        adjMap[cptIdx]!!.add(adjIdx)
                    }
                }
            }

            return bfs(adjMap)
        }

        /** adjMap 기반으로 네트워크의 개수를 반환한다. */
        fun bfs(adjMap: MutableMap<Int, MutableSet<Int>>): Int {
            val visited = (0 until adjMap.size).map { false }.toMutableList()
            var cnt = 0

            val queue = ArrayDeque<Int>()

            adjMap.keys.forEach { idx ->
                // 방문하지 않은 인덱스는 새로운 네트워크의 시작
                if (visited[idx].not()) {
                    cnt++ // 네트워크 개수 추가
                    queue.add(idx)
                    while (queue.isNotEmpty()) {
                        val curIdx = queue.poll()

                        // 미방문 인덱스인 경우, 인접 인덱스 리스트를 모두 queue에 넣는다.
                        if (visited[curIdx].not()) {
                            visited[curIdx] = true
                            queue.addAll(adjMap[curIdx]!!.toList())
                        }
                    }
                    // while문이 종료된 시점은, idx와 연결된 네트워크들이 모두 방문상태로 바뀌어있음.
                }
            }

            return cnt
        }
    }
}

fun main() {
    val n = 3
    val computers: Array<IntArray> = arrayOf(intArrayOf(1, 1, 0), intArrayOf(1, 1, 0), intArrayOf(0, 0, 1))
//    val computers: Array<IntArray> = arrayOf(intArrayOf(1, 1, 0), intArrayOf(1, 1, 1), intArrayOf(0, 1, 1))

    println(Programmers_네트워크.solution(n, computers))
}