package main.kotlin.programmers

import main.kotlin.programmers.Programmers_가장먼노드.Companion.solution
import java.util.*

class Programmers_가장먼노드 {
    companion object {
        data class Node(val num: Int) {
            val adj: LinkedList<Node> = LinkedList()
        }

        fun solution(n: Int, edge: Array<IntArray>): Int {
            val nodes = (0..n).map { num -> Node(num) } // nodes[0]은 사용하지 않는다.

            // 인접노드 설정
            for (arr in edge) {
                nodes[arr[0]].adj.add(nodes[arr[1]])
                nodes[arr[1]].adj.add(nodes[arr[0]])
            }

            val visited = BooleanArray(n + 1)
            val queue: Deque<Node> = LinkedList()
            var curDistance = 0
            var qCnt = 0

            queue.add(nodes[1])
            visited[1] = true
            while (queue.isNotEmpty()) {
                qCnt = queue.size
                for (idx in 0 until qCnt) {
                    val front = queue.poll()
                    visited[front.num] = true
                    for (node in front.adj) {
                        if (!visited[node.num]) queue.add(node) // 인접한 노드들 중에서 아직 방문하지 않은 노드들만 queue에 추가한다.
                    }
                }
                curDistance++
            }

            return qCnt
        }
    }
}

fun main() {
    val n = 6
    val edge = arrayOf(
        intArrayOf(3, 6),
        intArrayOf(4, 3),
        intArrayOf(3, 2),
        intArrayOf(1, 3),
        intArrayOf(1, 2),
        intArrayOf(2, 4),
        intArrayOf(5, 2)
    )

    println(solution(n, edge))
}