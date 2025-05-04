package main.kotlin.programmers

import main.kotlin.programmers.Programmers_기능개발.Companion.solution
import java.util.*

class Programmers_기능개발 {
    companion object {
        data class Task(val progress: Int, val speed: Int) {
            fun isFinished(day: Int): Boolean {
                return progress + speed * day >= 100
            }
        }

        fun solution(progresses: IntArray, speeds: IntArray): IntArray {
            val queue: Deque<Task> = LinkedList()

            for (i in 0 until progresses.size) {
                queue.add(Task(progresses[i], speeds[i]))
            }

            var day = 1
            val result = mutableListOf<Int>()

            while (queue.isNotEmpty()) {
                var cnt = 0

                while (queue.firstOrNull()?.isFinished(day) == true) {
                    queue.removeFirst()
                    cnt++
                }

                if (cnt > 0) result.add(cnt)

                day++
            }

            return result.toIntArray()
        }
    }
}

fun main() {
    val progresses = intArrayOf(93, 30, 55)
//    val progresses = intArrayOf(95, 90, 99, 99, 80, 99)
    val speeds = intArrayOf(1, 30, 5)
//    val speeds = intArrayOf(1, 1, 1, 1, 1, 1)

    println(solution(progresses, speeds).joinToString())
}