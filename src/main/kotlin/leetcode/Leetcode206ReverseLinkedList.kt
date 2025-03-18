package main.kotlin.leetcode

import main.kotlin.leetcode.Leetcode206ReverseLinkedList.Companion.reverseList

/**
 * Example:
 * var li = ListNode(5)
 * var v = li.`val`
 * Definition for singly-linked list.
 * class ListNode(var `val`: Int) {
 *     var next: ListNode? = null
 * }
 */
class Leetcode206ReverseLinkedList {
    class ListNode(var `val`: Int) {
        var next: ListNode? = null

        override fun toString(): String {
            return "$`val`" + if (next != null) ", $next" else ""
        }
    }

    companion object {
        fun reverseList(head: ListNode?): ListNode? {
            var prev: ListNode? = null // 현재 노드의 이전 노드
            var node = head // 현재 노드

            // 노드 끝으로 이동할 때까지 순회
            // 1,2,3,4,5
            while (node != null) {              // 1    2
                // 현재 노드의 다음 노드 미리 지정
                val next = node.next            // 2    3
                // 현재 노드의 다음으로 이전 노드 지정
                node.next = prev                // null 1
                // 이전 노드는 현재 노드로 지정
                prev = node                     // 1    2
                // 미리 지정했던 다음 노드를 현재 노드로 변경
                node = next                     // 2    3
            } // node.next가 node가 되고, 이는 prev에 저장된다. -> node == null이면 node의 순회가 끝난 것이고, prev는 node.next들이 역순으로 저장된다.

            return prev
        }
    }
}

fun main() {
    val head: Leetcode206ReverseLinkedList.ListNode
    val arr = intArrayOf(1, 2, 3, 4, 5)
    head = Leetcode206ReverseLinkedList.ListNode(arr[0])
    var node = head
    for (i in 1 until 5) {
        node.next = Leetcode206ReverseLinkedList.ListNode(arr[i])
        node = node.next!!
    }
    val res = reverseList(head)
    println(res)
}