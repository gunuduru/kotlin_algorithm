package main.kotlin.programmers

import main.kotlin.programmers.Programmers_모음사전.Companion.solution
import java.util.*

class Programmers_모음사전 {
    companion object {
        // A, AA, AAA, AAAA, (AAAAA~AUUUU), (~AUUUU), (~AUUU), (~AUU), (~AU), ~U
        fun solution(word: String): Int {
            val stk: Deque<Char> = LinkedList()
            val mapCurrToNext: Map<Char, Char> = mapOf(
                'A' to 'E',
                'E' to 'I',
                'I' to 'O',
                'O' to 'U'
            )

            var cnt = 1 // A부터
            stk.add('A') // 첫번째 추가

            while (stk.isNotEmpty()) {
                if (stk.joinToString("") == word) break // 찾는 문자열을 발견했다면 반복문 탈출
                // 삭제작업
                if (stk.size == 5) {
                    if (stk.last == 'U') {
                        while (stk.last == 'U') {
                            stk.removeLast()
                        } // ~UU로 끝나는 부분을 모두 pop -> 해당 prefix 기반으로 사전 맨 마지막이므로
                    } else {
                        val next = mapCurrToNext[stk.last]!!
                        stk.removeLast() // 가장 마지막 문자를
                        stk.add(next) // 다음 모음으로 변경
                        cnt++ // stk.add시점에 개수 추가
                        continue
                    }
                }

                // 추가작업
                stk.add('A') // 현재 stk.size < 5 이므로, 채워준다.
                cnt++
            }

            return cnt
        }
    }
}

fun main() {
    val word = "AAAE"

    println(solution(word))
}