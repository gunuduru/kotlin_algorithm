package main.kotlin.programmers

import java.util.*

class Programmers_퍼즐조각채우기 {
    companion object {
        data class Pos(val x: Int, val y: Int)

        fun extractBlocks(board: Array<IntArray>, target: Int): MutableList<List<Pos>> {
            val n = board.size
            val visited = Array(n) { BooleanArray(n) }
            val directions = listOf(Pair(0, 1), Pair(1, 0), Pair(0, -1), Pair(-1, 0))
            val blocks = mutableListOf<List<Pos>>()

            // bfs로 블럭 위치 파악
            for (i in 0 until n) {
                for (j in 0 until n) {
                    if (!visited[i][j] && board[i][j] == target) {
                        // 블럭 발견 -> bfs 시작
                        val queue: Queue<Pos> = LinkedList()
                        val block = mutableListOf<Pos>()
                        queue.add(Pos(i, j))
                        visited[i][j] = true

                        // 최초 발견 좌표 기준으로 bfs 수행
                        while (queue.isNotEmpty()) {
                            val (x, y) = queue.poll()
                            block.add(Pos(x, y))

                            for ((dx, dy) in directions) {
                                val nx = x + dx
                                val ny = y + dy
                                if (nx in 0 until n && ny in 0 until n && !visited[nx][ny] && board[nx][ny] == target) {
                                    visited[nx][ny] = true
                                    queue.add(Pos(nx, ny))
                                }
                            }
                        }

                        blocks.add(normalize(block)) // (0,0) 좌표 기준으로 정규화된 블럭 좌표 삽입 -> n*n 평면 위에 존재하게 됨
                    }
                }
            }
            return blocks
        }

        // 좌표 위치에 상관없이, 모양만으로 일치여부를 판단할 수 있도록 (0, 0) 좌표 기준으로 정규화하였음
        fun normalize(block: List<Pos>): List<Pos> {
            val minX = block.minOf { it.x }
            val minY = block.minOf { it.y }
            return block.map { Pos(it.x - minX, it.y - minY) } // (minX, minY) -> (0, 0)
                .sortedWith(compareBy({ it.x }, { it.y }))
        }

        // n*n 평면 안에서 시계방향으로 90도 회전 ()
        fun rotate(block: List<Pos>, n: Int): List<Pos> {
            // 좌표 (x, y) → (y, n - 1 - x)
            return normalize(block.map { Pos(it.y, n - 1 - it.x) })
        }

        fun checkMatched(emptyBlock: List<Pos>, puzzleBlock: List<Pos>, N: Int): Boolean {
            var targetPuzzle: List<Pos> = puzzleBlock
            var cnt = 0
            while (cnt <= 3) {
                if (emptyBlock == targetPuzzle) return true
                else {
                    targetPuzzle = rotate(targetPuzzle, N)
                    cnt++
                }
            }

            return false
        }

        fun solution(game_board: Array<IntArray>, table: Array<IntArray>): Int {
            val N = game_board.size
            val emptyBlocks = extractBlocks(game_board, 0) // 빈칸 블록 리스트
            val puzzleBlocks = extractBlocks(table, 1) // 퍼즐 블록 리스트

            val usedPuzzleIdxArr = BooleanArray(puzzleBlocks.size)

            var filledBlockCount = 0

            emptyBlocks.forEach { emptyBlock ->
                puzzleBlocks.forEachIndexed { idx, puzzle ->
                    if (!usedPuzzleIdxArr[idx] && checkMatched(emptyBlock, puzzle, N)) {
                        filledBlockCount += puzzle.size
                        usedPuzzleIdxArr[idx] = true
                        return@forEach // 매칭되는 퍼즐을 찾았으므로, 더이상의 퍼즐 매칭은 하지 않고 종료한다.
                    }
                }
            }

            return filledBlockCount
        }
    }
}

fun main() {
//    val game_board = arrayOf(
//        intArrayOf(1, 1, 0, 0, 1, 0),
//        intArrayOf(0, 0, 1, 0, 1, 0),
//        intArrayOf(0, 1, 1, 0, 0, 1),
//        intArrayOf(1, 1, 0, 1, 1, 1),
//        intArrayOf(1, 0, 0, 0, 1, 0),
//        intArrayOf(0, 1, 1, 1, 0, 0)
//    )
//
//    val table = arrayOf(
//        intArrayOf(1, 0, 0, 1, 1, 0),
//        intArrayOf(1, 0, 1, 0, 1, 0),
//        intArrayOf(0, 1, 1, 0, 1, 1),
//        intArrayOf(0, 0, 1, 0, 0, 0),
//        intArrayOf(1, 1, 0, 1, 1, 0),
//        intArrayOf(0, 1, 0, 0, 0, 0)
//    )

    val game_board = arrayOf(
        intArrayOf(0,0,0),
        intArrayOf(1,1,0),
        intArrayOf(1,1,1)
    )

    val table = arrayOf(
        intArrayOf(1,1,1),
        intArrayOf(1,0,0),
        intArrayOf(0,0,0)
    )

    println(Programmers_퍼즐조각채우기.solution(game_board, table))
}