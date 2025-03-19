package main.kotlin.programmers

import main.kotlin.programmers.Programmers_여행경로.Companion.solution
import java.util.*
import kotlin.collections.ArrayDeque

class Programmers_여행경로 {
    companion object {
        /**
         * 항상 ICN에서 출발
         */
        fun solution(tickets: Array<Array<String>>): Array<String> {
            val fromToMap: MutableMap<String, PriorityQueue<String>> = mutableMapOf()

            tickets.forEach { ticket ->
                fromToMap.putIfAbsent(ticket[0], PriorityQueue()) // 비어있으면 빈 queue 넣음
                requireNotNull(fromToMap[ticket[0]]).add(ticket[1]) // 위에 넣었기때문에 null safe
            }

            val answer: MutableList<String> = LinkedList()
            val stack = ArrayDeque<String>()

            // 출발지 삽입
            stack.add("ICN")
            while (stack.isNotEmpty()) {
                // 스택에서 추출될 값을 출발지로 한 도착지 처리
                while (fromToMap.containsKey(stack.last()) && fromToMap[stack.last()]!!.isNotEmpty()) { // stack의 최상단 공항에서 갈 수 있는 "다른 공항(PriorityQueue)"이 존재하고, 최상단 공항에서 이어서 갈 수 있는 다른 공항이 남아있는 경우 (왜냐면 이미 방문한 공항은 PQ에서 pop되니까)
                    stack.addLast(fromToMap[stack.last()]!!.poll())
                }

//                answer.add(stack.removeFirst()) -> 이렇게 해도 되지만, 스택으로 사용하고 있으니 스택처럼 사용해보자
                answer.add(0, stack.removeLast())
            }

            return answer.toTypedArray()
        }
    }
}

fun main() {
//    val tickets = arrayOf(arrayOf("ICN", "JFK"), arrayOf("HND", "IAD"), arrayOf("JFK", "HND"))
    val tickets = arrayOf(
        arrayOf("ICN", "SFO"),
        arrayOf("ICN", "ATL"),
        arrayOf("SFO", "ATL"),
        arrayOf("ATL", "ICN"),
        arrayOf("ATL", "SFO")
    )

    solution(tickets).forEach { ticket -> println(ticket) }
}