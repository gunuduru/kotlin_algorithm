package main.kotlin.leetcode

import main.kotlin.leetcode.Leetcode234dPalindromeLinkedList.Companion.isPalindrome
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
class Leetcode234dPalindromeLinkedList {
    class ListNode(var `val`: Int) {
        var next: ListNode? = null
    }

    companion object {
        fun isPalindrome(head: ListNode?): Boolean {
            var node = head

            val deque: Deque<Int> = LinkedList()

            while(node != null) {
                deque.add(node.`val`)
                node = node.next
            }

            while(deque.isNotEmpty() && deque.size > 1) {
                if(deque.pollFirst() != deque.pollLast()) return false
            }

            return true
        }
    }
}

fun main() {
    val head = Leetcode234dPalindromeLinkedList.ListNode(1)
    head.next = Leetcode234dPalindromeLinkedList.ListNode(2)
//    head.next = Leetcode234dPalindromeLinkedList.ListNode(2)
//    head.next = Leetcode234dPalindromeLinkedList.ListNode(1)
    println(isPalindrome(head))
}