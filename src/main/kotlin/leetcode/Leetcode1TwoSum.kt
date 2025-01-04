package main.kotlin.leetcode

import main.kotlin.leetcode.Leetcode1TwoSum.Companion.twoSum

class Leetcode1TwoSum {
    companion object {
        fun twoSum(nums: IntArray, target: Int): IntArray {
            val mapNumToIdx = mutableMapOf<Int, Int>()

            nums.forEachIndexed { idx, num ->
                if(mapNumToIdx.containsKey(target - num)) {
                    return intArrayOf(
                        requireNotNull(mapNumToIdx[target - num]), idx
                    )
                }

                mapNumToIdx[num] = idx //
            }

            // nums[stt] + nums[end] == target인 케이스가 반드시 있으므로, 컴파일에러를 막기 위한 무의미한 반환부
            return intArrayOf(-1, -1)
        }
    }
}

fun main() {
    val nums = intArrayOf(2, 7, 11, 15)
//    val nums = intArrayOf(3, 2, 4)
//    val nums = intArrayOf(3, 3)
    val target = 9
//    val target = 6
//    val target = 6

    println(twoSum(nums, target).contentToString())
}