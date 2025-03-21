package main.kotlin.leetcode

import main.kotlin.leetcode.Leetcode77Combinations.Companion.combine

class Leetcode77Combinations {

    companion object {
        // 1~n 까지의 숫자들 중에 k개를 고름
        fun combine(n: Int, k: Int): List<List<Int>> {
            val result = mutableListOf<List<Int>>()

            fun dfs(prevElements: MutableList<Int>, n: Int, k: Int) {
                if (prevElements.size == k) {
                    result.add(prevElements.toList())
                } else {
                    val nextStartNum = if(prevElements.isEmpty()) 1 else prevElements.last() + 1
                    (nextStartNum..n).forEach { nextElement ->
                        prevElements.add(nextElement)
                        dfs(prevElements, n, k)
                        prevElements.removeAt(prevElements.lastIndex)
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