package main.kotlin.programmers

import main.kotlin.programmers.Programmers_가장긴팰린드롬.Companion.solution

class Programmers_가장긴팰린드롬 {
    companion object {
        fun solution(s: String): Int {
            val n = s.length
            var longest = 1 // 가장 긴 팰린드롬의 길이

            fun expandAroundCenter(leftStart: Int, rightStart: Int): Int {
                var left = leftStart
                var right = rightStart
                while (left >= 0 && right < n && s[left] == s[right]) {
                    left--
                    right++
                }
                return right - left - 1 // 팰린드롬 길이
            }

            for (i in 0 until n) {
                val len1 = expandAroundCenter(i, i)     // 홀수
                val len2 = expandAroundCenter(i, i + 1) // 짝수
                longest = maxOf(longest, len1, len2)
            }

            return longest
        }
    }
}

fun main() {
    val s = "abcdcba"

    println(solution(s))
}