package main.kotlin.leetcode

import main.kotlin.leetcode.Leetcode20ValidParentheses.Companion.isValid

class Leetcode20ValidParentheses {
    companion object {
        fun isValid(s: String): Boolean {
            val mapParentheses = hashMapOf(
                '}' to '{',
                ']' to '[',
                ')' to '('
            )

            val stack = ArrayDeque<Char>()

            for (c in s) {
                if (!mapParentheses.containsKey(c)) stack.addLast(c) // 여는 괄호면 모두 push
                else if (stack.isEmpty() || mapParentheses[c] != stack.removeLastOrNull()) return false // 스택이 비어있거나, 스택에 담긴 괄호(여는 괄호)가 c와 매치되지 않는 괄호라면 false 반환
            }

            return stack.isEmpty()
        }
    }
}

fun main() {
//    val s = "()"
//    val s = "()[]{}"
//    val s = "(]"
//    val s = "([])"
    val s = "({]("

    println(isValid(s))
}