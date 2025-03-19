package main.kotlin.leetcode

import main.kotlin.leetcode.Leetcode316RemoveDuplicateLetters.Companion.removeDuplicateLetters

class Leetcode316RemoveDuplicateLetters {
    companion object {
        fun removeDuplicateLetters(s: String): String {
            val counter: MutableMap<Char, Int> = HashMap()

            // 처리한(이전에 봤던) 문자들을 true로 저장
            val seen: MutableMap<Char, Boolean> = HashMap()

            val stack = ArrayDeque<Char>()

            // 문자별 개수 계산
            for (c in s) {
                counter[c] = counter.getOrDefault(c, 0) + 1
            }

            for (c in s) {
                // 현재 처리하는 문자는 카운터에서 -1 처리
                counter[c] =
                    requireNotNull(counter[c]) - 1 // 위의 문자별 개수 계산 에서 s의 각 문자(c)들을 counter의 key로 넣어놨기 때문에, requireNotNull처리하였음

                if (seen[c] == true) continue // 이미 다룬 문자는 스킵

                // 스택이 비어있지 않고, 스택의 최상단 문자가 c보다 사전순으로 뒤에 있고, 스택의 최상단 문자가 나머지 s의 문자들에 존재할 경우
                while (stack.isNotEmpty() && stack.last() > c && requireNotNull(counter[stack.last()]) > 0) {
                    seen[stack.removeLast()] = false // 해당 문자를 못 본 것으로 (seen[c] = false) 처리결과 변경
                    // -> 해당 문자는 어짜피 남아있으니(counter[c] > 0) 이후의 반복문에서 추가될 것임
                }

                stack.addLast(c) // 스택에 넣고

                seen[c] = true // 해당 문자를 처리한 것으로 수정
            }

            println(KotlinVersion.CURRENT.toString())
            return stack.joinToString("")
        }
    }
}

fun main() {
//    val s = "bcabc"
    val s = "cbacdcbc"

    println(removeDuplicateLetters(s))
}