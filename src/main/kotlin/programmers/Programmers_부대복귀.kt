package main.kotlin.programmers

import main.kotlin.programmers.Programmers_부대복귀.Companion.solution
import java.util.*

class Programmers_부대복귀 {
    companion object {
        fun solution(n: Int, roads: Array<IntArray>, sources: IntArray, destination: Int): IntArray {
            // 인접 리스트 구성
            val graph = Array(n + 1) { mutableListOf<Int>() }
            for ((u, v) in roads) {
                graph[u].add(v)
                graph[v].add(u)
            }

            // 거리 배열 초기화: -1은 도달 불가 표시
            val dist = IntArray(n + 1) { -1 }

            // destination 기준 BFS 시작
            // dist[x] = dest -> x 까지의 최단거리
            val queue: Queue<Int> = LinkedList()
            queue.add(destination)
            dist[destination] = 0

            while (queue.isNotEmpty()) {
                val cur = queue.poll()
                for (next in graph[cur]) {
                    if (dist[next] == -1) { // destination -> next 까지의 최단거리가 설정되지 않았다면 -> 현재 발견한 값이 최단거리이므로 설정해둠
                        dist[next] =
                            dist[cur] + 1 // destination -> cur까지 오는데 걸린 거리(dist[cur]) 에다가 하나를 더 추가 (cur랑 next는 연결돼있으니까)
                        queue.add(next)
                    }
                    // else는 무시한다. 이유는 이미 dist[next]가 지정된 상태라면 그 값이 최소이므로
                }
            }

            // 각 source에 대한 거리값 정리
            return sources.map { dist[it] }.toIntArray()
        }
    }
}

fun main() {
    val n = 3
    val roads = arrayOf(intArrayOf(1, 2), intArrayOf(2, 3))
    val sources = intArrayOf(2, 3)
    val destination = 1

    println(solution(n, roads, sources, destination).joinToString())
}