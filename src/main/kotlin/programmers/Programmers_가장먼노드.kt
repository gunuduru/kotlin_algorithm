package main.kotlin.programmers

import main.kotlin.programmers.Programmers_가장먼노드.Companion.solution
import java.util.*

class Programmers_가장먼노드 {
    companion object {
        fun solution(n: Int, edge: Array<IntArray>): Int {
            val graph = Array(n + 1) { mutableListOf<Int>() }
            val visited = BooleanArray(n + 1)
            val distance = IntArray(n + 1)

            for ((u, v) in edge) {
                graph[u].add(v)
                graph[v].add(u)
            }

            val queue: Deque<Int> = LinkedList()
            queue.add(1)
            visited[1] = true

            while (queue.isNotEmpty()) {
                val curr = queue.poll()
                for (next in graph[curr]) {
                    if (!visited[next]) {
                        visited[next] = true
                        distance[next] = distance[curr] + 1
                        queue.add(next)
                    }
                }
            }

            val max = distance.maxOrNull() ?: 0
            return distance.count { it == max }
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