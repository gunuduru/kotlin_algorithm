package main.kotlin.programmers

import main.kotlin.programmers.Programmers_큰수만들기.Companion.solution
import java.util.*

class Programmers_큰수만들기 {
    companion object {
        class NumIdx(val num: Char, val idx: Int)

        fun solution(number: String, k: Int): String {
            // 가장 작은 숫자이고, 가장 앞에 있는 숫자들을 삭제하면 되겠다.
            val pq = PriorityQueue<NumIdx> { o1, o2 ->
                if (o1.num == o2.num) o1.idx - o2.idx
                else o1.num - o2.num
            }

            number.forEachIndexed { idx, num ->
                pq.add(NumIdx(num, idx))
            }

            repeat(k) {
                pq.remove() // poll?
            }

            val pqWithIdx = PriorityQueue<NumIdx> { o1, o2 ->
                o1.idx - o2.idx
            }

            while (pq.isNotEmpty()) {
                pqWithIdx.add(pq.poll())
            }

            return pqWithIdx.map { it.num }.joinToString("")
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