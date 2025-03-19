package main.kotlin.leetcode

import main.kotlin.leetcode.Leetcode23MergeKSortedLists.Companion.mergeKLists
import java.util.*

/**
 * Example:
 * var li = ListNode(5)
 * var v = li.`val`
 * Definition for singly-linked list.
 * class ListNode(var `val`: Int) {
 *     var next: ListNode? = null
 * }
 */
class Leetcode23MergeKSortedLists {
    class ListNode(var `val`: Int) {
        var next: ListNode? = null

        override fun toString(): String {
            return "$`val`" + if (next != null) ", $next" else ""
        }
    }

    companion object {
        fun mergeKLists(lists: Array<ListNode?>): ListNode? {
            val pq = PriorityQueue<ListNode>{ o1, o2 ->
                when {
                    o1.`val` == o2.`val` -> 0
                    o1.`val` > o2.`val` -> 1
                    else -> -1
                }
            }

            for(node in lists) {
                if(node != null) pq.add(node)
            } // 끝나면 {1, 1, 2} 저장돼있음

            val root = ListNode(0) // 무의미한 초기값
            var tail = root

            while(pq.isNotEmpty()) {
                tail.next = pq.poll() // pq에서 가장 우선순위 높은 객체를 tail의 다음 노드로 설정
                tail = requireNotNull(tail.next) // 다음번 tail의 next를 설정하기 위한 준비

                if(tail.next != null) pq.add(tail.next) // 추출한 노드(다음 노드로 설정한 노드)의 next가 존재한다면 pq에 다시 삽입 -> {1, 1, 2} -> 1 추출 -> {1, 2, 4} (1->4->5에서 1을 삽입하였으니 next는 4)
            }

            return root.next // 무의미한 초기값 이후의 ListNode를 반환
        }
    }
}

fun main() {
    val list1 = Leetcode23MergeKSortedLists.ListNode(1)
    list1.next = Leetcode23MergeKSortedLists.ListNode(4)
    list1.next!!.next = Leetcode23MergeKSortedLists.ListNode(5)
    val list2 = Leetcode23MergeKSortedLists.ListNode(1)
    list2.next = Leetcode23MergeKSortedLists.ListNode(3)
    list2.next!!.next = Leetcode23MergeKSortedLists.ListNode(4)
    val list3 = Leetcode23MergeKSortedLists.ListNode(2)
    list3.next = Leetcode23MergeKSortedLists.ListNode(6)
    val res = mergeKLists(arrayOf(list1, list2, list3))
    println(res)
}