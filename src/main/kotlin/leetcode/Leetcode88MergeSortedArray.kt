package main.kotlin.leetcode

import main.kotlin.leetcode.Leetcode88MergeSortedArray.Companion.merge

/**
 * Example:
 * var li = ListNode(5)
 * var v = li.`val`
 * Definition for singly-linked list.
 * class ListNode(var `val`: Int) {
 *     var next: ListNode? = null
 * }
 */
class Leetcode88MergeSortedArray {

    companion object {
        // num1에 넣어야 하는데, num1의 앞에서부터 비교해서 수정하면 기존 값이 사라지므로, 큰 숫자부터 뒤에서 비교하여 채운다.
        fun merge(nums1: IntArray, m: Int, nums2: IntArray, n: Int): Unit {
            var idx1 = m - 1 // 맨 뒤 인덱스
            var idx2 = n - 1 // 맨 뒤 인덱스
            var idx = m + n - 1 // nums1에 채워나갈거라서, nums1의 맨 뒤 인덱스부터 시작한다.

            while (idx1 >= 0 && idx2 >= 0) {
                if (nums1[idx1] > nums2[idx2]) {
                    nums1[idx] = nums1[idx1]
                    idx1--
                } else {
                    nums1[idx] = nums2[idx2]
                    idx2--
                }
                idx--
            }

            // 만약 nums2의 숫자가 남아있다면, 이를 복사해넣는다.
            // 혹시 nums1이 남아있는 경우가 생기더라도, 이미 잘 정렬된 상태이므로 패스한다.
            while (idx2 >= 0) {
                nums1[idx] = nums2[idx2]
                idx2--
                idx--
            }
        }
    }
}

fun main() {
    val nums1: IntArray = intArrayOf(1, 2, 3, 0, 0, 0)
    val m = 3
    val nums2: IntArray = intArrayOf(2, 5, 6)
    val n = 3

    merge(nums1, m, nums2, n)
    print("[")
    nums1.forEach { num -> print("$num  ") }
    print("]")
}