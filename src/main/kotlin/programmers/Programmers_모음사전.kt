package main.kotlin.programmers

import main.kotlin.programmers.Programmers_모음사전.Companion.solution
import java.util.*

class Programmers_모음사전 {
    companion object {
        fun solution(word: String): Int {
            val vowels = charArrayOf('A', 'E', 'I', 'O', 'U')
            val stk: Deque<Char> = LinkedList()
            val sb = StringBuilder()
            var count = 1

            stk.add('A')
            sb.append('A')

            while (true) {
                if (sb.toString() == word) break

                // 길이가 5일 때 처리
                if (stk.size == 5) {
                    // 'U'로 끝나는 경우 → 뒤에서부터 'U' 제거
                    while (stk.isNotEmpty() && stk.last == 'U') {
                        stk.removeLast()
                        sb.deleteCharAt(sb.length - 1)
                    }

                    // 그 다음 모음으로 교체
                    if (stk.isNotEmpty()) {
                        val lastChar = stk.removeLast()
                        sb.deleteCharAt(sb.length - 1)
                        val nextIndex = vowels.indexOf(lastChar) + 1
                        stk.add(vowels[nextIndex])
                        sb.append(vowels[nextIndex])
                    }
                } else {
                    // 길이 < 5 → 뒤에 'A' 추가
                    stk.add('A')
                    sb.append('A')
                }

                count++
            }

            return count
        }
    }
}

fun main() {
    val word = "AAAE"

    println(solution(word))
}