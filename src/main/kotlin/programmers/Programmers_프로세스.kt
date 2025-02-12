package main.kotlin.programmers

import java.util.*

class Programmers_프로세스 {
    companion object {
        class Process(val location: Int, val priority: Int)

        fun solution(priorities: IntArray, location: Int): Int {
            val queue: Queue<Process> = LinkedList()
            priorities.forEachIndexed { index, priority -> queue.add(Process(index, priority)) }

            // 현재 수행중인 프로세스의 순서. 초기값은 1이다
            var processOrder = 1

            while(queue.isNotEmpty()) {
                val curProcess = queue.poll()
                // 현재 프로세스보다 우선순위가 더 높은 프로세스가 있다면
                if(queue.find { it.priority > curProcess.priority } != null) {
                    queue.add(curProcess)
                } else {
                    if(location == curProcess.location) {
                        return processOrder
                    } else {
                        processOrder++
                    }
                }
            }

            return processOrder // while문 안에서 끝날것인데, 컴파일오류를 막기 위해 추가
        }
    }
}

fun main() {
//    val priorities = intArrayOf(2, 1, 3, 2)
    val priorities = intArrayOf(1, 1, 9,1,1,1)
//    val location = 2
    val location = 0

    println(Programmers_프로세스.solution(priorities, location))
}