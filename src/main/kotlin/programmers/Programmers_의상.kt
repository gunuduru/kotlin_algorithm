package main.kotlin.programmers

import main.kotlin.programmers.Programmers_의상.Companion.solution

class Programmers_의상 {
    companion object {
        fun solution(clothes: Array<Array<String>>): Int {
            val categoryToItems = mutableMapOf<String, MutableSet<String>>()

            for ((item, category) in clothes) {
                categoryToItems.computeIfAbsent(category) { mutableSetOf() }.add(item)
            }

            return categoryToItems.values
                .map { it.size + 1 }  // 각 카테고리: 아이템 수 + 안 입는 경우
                .reduce(Int::times) - 1  // 모든 카테고리에서 아무것도 안 입은 경우 제외
        }
    }
}

fun main() {
    val clothes = arrayOf(
        arrayOf("yellow_hat", "headgear"), arrayOf("blue_sunglasses", "eyewear"), arrayOf(
            "green_turban", "headgear"
        )
    )

    println(solution(clothes))
}