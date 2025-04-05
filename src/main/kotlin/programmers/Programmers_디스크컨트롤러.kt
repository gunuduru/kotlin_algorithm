package main.kotlin.programmers

import main.kotlin.programmers.Programmers_디스크컨트롤러.Companion.solution
import java.util.*

class Programmers_디스크컨트롤러 {
    companion object {
        // 우선순위: 작업의 소요시간이 짧은 것 > 작업의 요청 시각이 빠른 것 > 작업의 번호가 작은 것
        /**
         * @param remaining 완료까지 남은 시간
         */
        data class Task(val duration: Int, val requestTime: Int, val num: Int, var remaining: Int) {
            constructor(requestTime: Int, duration: Int, num: Int) : this(
                requestTime = requestTime,
                duration = duration,
                num = num,
                remaining = duration // 초기 생성시 남은 시간 = 소요시간
            )
        }

        // 평균 반환시간의 정수부분을 반환해야 한다.
        // jobs[i] = [requestTime, duration]
        fun solution(jobs: Array<IntArray>): Int {
            val readyQueue: PriorityQueue<Task> = PriorityQueue<Task>(
                compareBy<Task> { it.duration }
                    .thenBy { it.requestTime }
                    .thenBy { it.num }
            )

            /**
             * 작업들이 모여있는 우선순위큐. 요청시간이 가장 빠른 순으로 정렬되어있다.
             * 매 시간마다 taskQueue의 최상단 작업을 확인하고, 요청시간에 해당하는 작업일 경우 readyQueue에 넣는다.
             * */
            val taskQueue: PriorityQueue<Task> = PriorityQueue<Task>(
                compareBy<Task> { it.requestTime }
            )

            val finishTimeList = MutableList(jobs.size) { 0 } // 이게 왜 2, 15, 4가 될까 확인해보자

            // 작업큐에 우선 넣는다.
            jobs.forEachIndexed { idx, job ->
                taskQueue.add(
                    Task(
                        duration = job[1],
                        requestTime = job[0],
                        num = idx
                    )
                )
            }

            var curTime = 0 // 반복문 종료시 1씩 증가하는 현재시간값

            // 작업큐와 대기큐가 모두 비어있을 경우에 반복문을 종료한다.
            while (taskQueue.isNotEmpty() || readyQueue.isNotEmpty()) {
                // 1. 현재시간 기준, 작업큐의 최상단 작업이 대기큐에 들어갈 상태인지 확인 -> 대기큐에 삽입
                while (taskQueue.isNotEmpty() && taskQueue.peek()!!.requestTime <= curTime) {
                    readyQueue.add(taskQueue.poll())
                }
                // 2. 대기큐에 작업이 있다면 -> curTime += 해당 작업의 소요시간 && finishTimeList[num].add(curTime - 요청시간)
                if (readyQueue.isNotEmpty()) {
                    val finishedTask = readyQueue.poll()
                    curTime += finishedTask.duration // 현재 작업의 소요시간만큼 현재시간 증가
                    finishTimeList[finishedTask.num] =
                        curTime - finishedTask.requestTime // 현재시간 - 요청시간 = 요청 후 완료까지 걸린 시간
                } else if (taskQueue.isNotEmpty()) {
                    curTime = taskQueue.peek()!!.requestTime // 대기큐엔 작업이 없으나 작업큐에는 존재할 경우, 요청시간이 제일 빠른 작업의 요청시간으로 순간이동
                    // (이게 없으면 작업 종료 후 대기큐가 비어있을 때 무한루프)
                }
            }

            return finishTimeList.average().toInt()
        }
    }
}

fun main() {
    val jobs: Array<IntArray> = arrayOf(
        intArrayOf(0, 3),
        intArrayOf(1, 9),
        intArrayOf(3, 5),
    )

    println(solution(jobs))
}