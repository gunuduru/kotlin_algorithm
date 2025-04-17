package main.kotlin.programmers

import main.kotlin.programmers.Programmers_이중우선순위큐.Companion.solution
import java.util.*

class Programmers_이중우선순위큐 {
    companion object {
        fun solution(operations: Array<String>): IntArray {
            val reversePq: PriorityQueue<Int> = PriorityQueue(kotlin.Comparator.reverseOrder()) // front = 최대값
            val pq: PriorityQueue<Int> = PriorityQueue() // front = 최소값

            operations.forEach { operation ->
                require(reversePq.size == pq.size) { "큐 크기 불일치" }

                val (op, num) = operation.split(" ")
                val number = num.toInt()
                when (op) {
                    "I" -> {
                        reversePq.add(number)
                        pq.add(number)
                    }

                    "D" -> {
                        if (pq.isEmpty()) return@forEach // 큐가 비어있으면 삭제 연산 무시

                        if (number == -1) {
                            // 최소값 삭제 -> pq의 front 삭제, 해당 값을 reversePq에서도 삭제
                            val min = pq.poll()
                            reversePq.remove(min)
                        } else {
                            // 최대값 삭제 -> reversePq의 front 삭제, 해당 값을 pq에서도 삭제
                            val max = reversePq.poll()
                            pq.remove(max)
                        }
                    }
                }
            }

            require(reversePq.size == pq.size) { "큐 크기 불일치" }
            return if (pq.isEmpty()) intArrayOf(0, 0)
            else intArrayOf(reversePq.peek(), pq.peek())
        }
    }
}

fun main() {
//    val operators = arrayOf("I 16", "I -5643", "D -1", "D 1", "D 1", "I 123", "D -1")
    val operators = arrayOf("I -45", "I 653", "D 1", "I -642", "I 45", "I 97", "D 1", "D -1", "I 333")

    solution(operators).forEach { println(it) }
}