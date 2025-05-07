package main.kotlin.programmers

import main.kotlin.programmers.Programmers_경주로건설.Companion.solution
import java.util.*

class Programmers_경주로건설 {
    companion object {
        // 자동차의 bfs 과정에서의 위치
        data class Pos(val x: Int, val y: Int, val dir: Pair<Int, Int>, val cost: Int)

        /**
        위쪽: (-1, 0)
        왼쪽: (0, -1)
        오른쪽: (0, 1)
        아래쪽: (1, 0)
         */
        val dirIdx = arrayOf(-1 to 0, 1 to 0, 0 to -1, 0 to 1) // 상 하 좌 우

        // 직선도로 100원 코너 500원(방향 바꿀때)
        fun solution(board: Array<IntArray>): Int {
            val blocks = mutableSetOf<Pair<Int, Int>>() // 블록의 위치

            val n = board.size // 배열의 크기

            for (i in 0 until n) {
                for (j in 0 until n) {
                    if (board[i][j] == 1) blocks.add(i to j)
                }
            }

            /** 해당 좌표가 유효한지 확인 */
            fun isValidPos(pos: Pair<Int, Int>): Boolean {
                return pos.first >= 0 && pos.first < n && pos.second >= 0 && pos.second < n
            }

            val visited =
                Array(n) { Array(n) { IntArray(4) { Int.MAX_VALUE } } } // visited[i][j][k] = i, j 좌표를 k 방향으로 방문했을때의 최소 cost

            val queue: Deque<Pos> = LinkedList()
            // 우측으로
            if (!blocks.contains(1 to 0)) {
                visited[1][0][dirIdx.indexOf(1 to 0)] = 100
                queue.add(Pos(1, 0, 1 to 0, 100))
            }

            // 하단으로
            if (!blocks.contains(0 to 1)) {
                visited[0][1][dirIdx.indexOf(0 to 1)] = 100
                queue.add(Pos(0, 1, 0 to 1, 100))
            }

            while (queue.isNotEmpty()) {
                val (x, y, dir, cost) = queue.poll()
                // 선택 가능한 방향 확인 (직진, 좌회전, 우회전 - 직진 아니라면 500원 추가 필요)
                val nextDirList = listOf(dir, dir.turn(true), dir.turn(false))
                for (nextDir in nextDirList) {
                    val nx = x + nextDir.first
                    val ny = y + nextDir.second
                    if (isValidPos(nx to ny) && !blocks.contains(nx to ny)) { // 다음 좌표가 유효하면서, 막혀있지 않고, 미방문 상태라면
                        val newCost = cost + if (dir == nextDir) 100 else 600
                        if (visited[nx][ny][dirIdx.indexOf(nextDir)] > newCost) {
                            visited[nx][ny][dirIdx.indexOf(nextDir)] = newCost
                            queue.add(Pos(nx, ny, nextDir, newCost))
                        }
                    }
                }
            }

            return visited[n - 1][n - 1].minOf { it }
        }

        /**
        위쪽: (-1, 0)
        왼쪽: (0, -1)
        오른쪽: (0, 1)
        아래쪽: (1, 0)
         */
        fun Pair<Int, Int>.turn(isLeft: Boolean): Pair<Int, Int> {
            return if (isLeft) {
                if (this == -1 to 0) {
                    // 위쪽은 다른 방향과 반대.
                    this.second to this.first
                } else {
                    -this.second to -this.first
                }
            } else {
                if (this == -1 to 0) {
                    // 위쪽은 다른 방향과 반대.
                    -this.second to -this.first
                } else {
                    this.second to this.first
                }
            }
        }
    }
}

fun main() {
    val board = arrayOf(intArrayOf(0, 0, 1, 0), intArrayOf(0, 0, 0, 0), intArrayOf(0, 1, 0, 1), intArrayOf(1, 0, 0, 0))
    println(solution(board))
}