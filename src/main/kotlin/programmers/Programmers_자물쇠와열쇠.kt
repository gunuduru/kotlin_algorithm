package main.kotlin.programmers

import main.kotlin.programmers.Programmers_자물쇠와열쇠.Companion.solution

class Programmers_자물쇠와열쇠 {
    companion object {
        // 열쇠는 회전과 이동이 가능
        // 열쇠를 꼈을 때 자물쇠의 모든 홈이 채워져야
        fun solution(key: Array<IntArray>, lock: Array<IntArray>): Boolean {
            val keySize = key.size
            val lockSize = lock.size
            val pad = lockSize - 1
            val bigSize = keySize + 2 * pad

            // 1. 네 번 회전한 keyTemplates 생성(패딩 포함)
            val keyTemplates = mutableListOf<Array<IntArray>>()
            var curKey = key
            repeat(4) {
                keyTemplates.add(addPadding(curKey, pad))
                curKey = rotate90(curKey)
            }

            // 2. 네 번의 회전, 모든 이동 케이스를 순회하며 lock을 덮어봄
            for (rotatedIdx in 0 until 4) {
                val paddedKey = keyTemplates[rotatedIdx]
                for (dx in 0..bigSize - lockSize) {
                    for (dy in 0..bigSize - lockSize) {
                        var match = true
                        loop@ for (i in 0 until lockSize) {
                            for (j in 0 until lockSize) {
                                val keyVal = paddedKey[dx + i][dy + j]
                                val lockVal = lock[i][j]
                                if (keyVal + lockVal != 1) {
                                    match = false
                                    break@loop
                                }
                            }
                        }
                        if (match) return true
                    }
                }
            }
            return false
        }

        fun rotate90(arr: Array<IntArray>): Array<IntArray> {
            val n = arr.size
            val result = Array(n) { IntArray(n) }
            for (i in 0 until n) {
                for (j in 0 until n) {
                    result[j][n - 1 - i] = arr[i][j]
                }
            }
            return result
        }

        fun addPadding(arr: Array<IntArray>, k: Int): Array<IntArray> {
            val n = arr.size
            val paddedSize = n + 2 * k
            val result = Array(paddedSize) { IntArray(paddedSize) { 0 } }

            for (i in 0 until n) {
                for (j in 0 until n) {
                    result[i + k][j + k] = arr[i][j]
                }
            }
            return result
        }
    }
}

fun main() {
    val key = arrayOf(intArrayOf(0, 0, 0), intArrayOf(1, 0, 0), intArrayOf(0, 1, 1))
    val lock = arrayOf(intArrayOf(1, 1, 1), intArrayOf(1, 1, 0), intArrayOf(1, 0, 1))

    println(solution(key, lock))
}