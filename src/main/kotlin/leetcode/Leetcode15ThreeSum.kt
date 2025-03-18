package main.kotlin.leetcode

import main.kotlin.leetcode.Leetcode15ThreeSum.Companion.threeSum

class Leetcode15ThreeSum {
    companion object {
        // 3 <= nums.size <= 3000
        fun threeSum(nums: IntArray): List<List<Int>> {
            // { i .. left .. right }
            var left: Int
            var right: Int
            var sum: Int
            val results = mutableListOf<List<Int>>()
            nums.sort()

            for (i in 0 until nums.size - 2) { // i 기준으로 우측 2칸은 left, right의 몫이므로
                // 중복 건너뛰기.
                if(i > 0 && nums[i] == nums[i-1]) continue

                // 이후의 작업들은 left와 right의 비교 -> i는 건들지도, 비교하지도 않는다
                left = i + 1
                right = nums.size - 1
                while (left < right) {
                    sum = nums[i] + nums[left] + nums[right]
                    if (sum < 0) left++
                    else if (sum > 0) right--
                    else {
                        results.add(listOf(nums[i], nums[left], nums[right])) // 합이 0인 숫자들 저장
                        // 중복 제거
                        while (left < right && nums[left] == nums[left + 1]) left++ // left 1 증가한 위치가 중복위치라면 스킵하기 위함
                        while (left < right && nums[right] == nums[right - 1]) right-- // right 1 감소한 위치가 중복위치라면 스킵하기 위함

                        left++
                        right--
                    }
                }
            }

            return results
        }
    }
}

fun main() {
//    val nums = intArrayOf(-1, 0, 1, 2, -1, -4)
//    val nums = intArrayOf(0, 1, 1)
    val nums = intArrayOf(0,0,0)

    println(threeSum(nums))
}