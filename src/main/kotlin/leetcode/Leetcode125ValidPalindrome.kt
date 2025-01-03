package main.kotlin.leetcode

import main.kotlin.leetcode.Leetcode125ValidPalindrome.Companion.isPalindrome
import java.util.*

class Leetcode125ValidPalindrome {
    companion object {
        fun isPalindrome(s: String): Boolean {
            val charArr = s.filter { it.isLetterOrDigit() }.uppercase(Locale.getDefault()).toCharArray()
            val lastIdx = charArr.lastIndex
            // 5 -> 0 <= x < 2
            // 6 -> 0 <= x < 3
            for (i in (0 until charArr.size / 2)) {
                if (charArr[i] != charArr[lastIdx - i]) return false
            }

            return true
        }
    }
}

fun main() {
//    val s = "A man, a plan, a canal: Panama"
//    val s = "race a car"
    val s = ".,"
    println(isPalindrome(s))
}