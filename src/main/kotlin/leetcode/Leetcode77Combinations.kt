package main.kotlin.leetcode

import main.kotlin.leetcode.Leetcode77Combinations.Companion.combine

class Leetcode77Combinations {

    companion object {
        // 1~n 까지의 숫자들 중에 k개를 고름
        fun combine(n: Int, k: Int): List<List<Int>> {
            val result = mutableListOf<List<Int>>()

            fun dfs(prevElements: MutableList<Int>, n: Int, k: Int) {
                if (prevElements.size == k) { // prevElements가 k개 쌓였다 -> 조합 한개 완성
                    result.add(prevElements.toList()) // result에 반영
                } else {
                    /**
                     * 조합 결과를 찾기 위한 dfs 범위의 최소값.
                     * 선택한 숫자가 없다면 1, 있다면 이전에 선택한 숫자의 다음 숫자
                     */
                    val nextStartNum = if(prevElements.isEmpty()) 1 else prevElements.last() + 1
                    (nextStartNum..n).forEach { nextElement -> // 다음 숫자 최소값 ~ n 사이에서 dfs 수행
                        prevElements.add(nextElement) // nextElement를 추가하여
                        dfs(prevElements, n, k) // dfs 수행
                        prevElements.removeAt(prevElements.lastIndex) // 다음 nextElement에서의 dfs를 위해, 이번 블록에서 추가한 값은 제거한다. (visited 처리하듯)
                    }
                }
            }

            dfs(mutableListOf(), n, k)

            return result
        }
    }
}

fun main() {
    val n = 1
    val k = 1

    combine(n, k).forEach { list -> println(list.joinToString()) }
}