package main.kotlin.programmers

import main.kotlin.programmers.Programmers_큰수만들기.Companion.solution
import java.util.*

class Programmers_큰수만들기 {
    companion object {
        fun solution(number: String, k: Int): String {
            // 맨 앞에서부터 큰 숫자를 남기게끔 그리디하게 접근
            var removeCnt = 0
            val stk: Deque<Char> = LinkedList()

            for (c in number) {
                // c보다 작은 숫자가 앞에 있었다면 삭제
                while (stk.isNotEmpty() && stk.last < c && removeCnt < k) {
                    stk.removeLast()
                    removeCnt++
                }
                stk.add(c)
            }

            // k개만큼 삭제하지 않았다면, 맨 뒤의 숫자들을 삭제 (현재 stk은 맨 뒤의 숫자가 가장 작을 것이므로)
            if (removeCnt < k) {
                repeat(k - removeCnt) {
                    stk.removeLast()
                }
            }

            return stk.joinToString("")
        }
    }
}

fun main() {
//    val number = "1924"
//    val number = "1231234"
    val number = "4177252841"
//    val k = 2
//    val k = 3
    val k = 4

    println(solution(number, k))
}