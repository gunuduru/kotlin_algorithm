package main.kotlin.leetcode

import main.kotlin.leetcode.Leetcode46Permuitations.Companion.permute

class Leetcode46Permuitations {

    companion object {
        fun permute(nums: IntArray): List<List<Int>> {
            val results: MutableList<List<Int>> = mutableListOf()

            /**
             * @param prevElements 이전 depth에서 선택한 원소들
             * @param elements 이번 depth에서 선택 가능한(=남아있는) 원소들
             */
            fun dfs(prevElements: MutableList<Int>, elements: List<Int>) {
                // 선택 가능한 원소들이 없으므로, 리프 노드에 도달한 것 -> 결과에 추가
                if (elements.isEmpty()) {
                    results.add(prevElements.map { it }) // 하드카피 해야한다 (prevElements는 객체이므로)
                }

                // 전달받은 엘리먼트를 모두 탐색
                for (e in elements) {
                    val nextElement: MutableList<Int> = ArrayList(elements)
                    nextElement.remove(e) // (이번 반복문의 경우) 현재 depth에선 e를 택할 것 -> 다음 원소에선 제외되어야 함

                    prevElements.add(e) // 현재 depth에서 선택한 원소는 이후의 prevElements에 추가한다.

                    dfs(prevElements, nextElement) // e를 선택한 이후의 prev/next로 dfs를 다시 수행한다.

                    prevElements.remove(e) // e를 선택한 경우는 모두 순회 완료했으므로, e를 선택하지 않았던 시점으로 prev를 원복시킨다. -> 다음 e에서 이어서!
                }
            }

            val list = nums.toMutableList()
            dfs(mutableListOf(), list)

            return results
        }
    }
}

fun main() {
    val nums = intArrayOf(1, 2, 3)

    permute(nums).forEach { list -> println(list.joinToString()) }
}