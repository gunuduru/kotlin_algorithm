package main.kotlin.programmers

class Programmers_타겟넘버 {
    companion object {
        var numberList = mutableListOf<Int>()
        var cnt = 0
        var targetNum = 0
        var totalDepth = 0

        fun solution(numbers: IntArray, target: Int): Int {
            targetNum = target
            totalDepth = numbers.size
            numberList = numbers.toMutableList()
            dfs(0, 0)

            return cnt
        }

        /**
         * @param depth 현재 누적된 depth
         */
        private fun dfs(curSum: Int, depth: Int) {
            if (depth == totalDepth) {
                if (curSum == targetNum) {
                    cnt++
                }
            } else {
                // +로 한번, -로 한번
                dfs(curSum + numberList[depth], depth + 1)
                dfs(curSum - numberList[depth], depth + 1)
            }
        }
    }
}

fun main() {
    val numbers = intArrayOf(1, 1, 1, 1, 1)
    val target = 3

    println(Programmers_타겟넘버.solution(numbers, target))
}