package main.kotlin.leetcode

import main.kotlin.leetcode.Leetcode21MergeTwoSortedLists.Companion.mergeTwoLists

/**
 * Example:
 * var li = ListNode(5)
 * var v = li.`val`
 * Definition for singly-linked list.
 * class ListNode(var `val`: Int) {
 *     var next: ListNode? = null
 * }
 */
class Leetcode21MergeTwoSortedLists {
    class ListNode(var `val`: Int) {
        var next: ListNode? = null

        override fun toString(): String {
            return "$`val`" + if(next != null) ", $next" else ""
        }
    }

    companion object {
        fun mergeTwoLists(list1: ListNode?, list2: ListNode?): ListNode? {
            // 두 노드 중 null이 아닌 노드를 return
            if(list1 == null) return list2
            if(list2 == null) return list1

            // value가 더 작은쪽의 값은
            return if(list1.`val` < list2.`val`) {
                // l1의 value가 더 작으면 l1.next와 l2를 병합하여 반환한다.
                list1.next = mergeTwoLists(list1.next, list2)
                list1
            } else {
                // l2의 value가 더 작거나 같으면 l1과 l2.next를 병합하여 반환한다.
                list2.next = mergeTwoLists(list1, list2.next)
                list2
            }
        }
    }
}

fun main() {
    val list1 = Leetcode21MergeTwoSortedLists.ListNode(1)
    list1.next = Leetcode21MergeTwoSortedLists.ListNode(2)
    list1.next!!.next = Leetcode21MergeTwoSortedLists.ListNode(4)
    val list2 = Leetcode21MergeTwoSortedLists.ListNode(1)
    list2.next = Leetcode21MergeTwoSortedLists.ListNode(3)
    list2.next!!.next = Leetcode21MergeTwoSortedLists.ListNode(4)
    val res = mergeTwoLists(list1, list2)
    println(res)
}