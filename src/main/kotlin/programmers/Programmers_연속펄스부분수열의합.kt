package main.kotlin.programmers

import main.kotlin.programmers.Programmers_연속펄스부분수열의합.Companion.solution
import kotlin.math.max

class Programmers_연속펄스부분수열의합 {
    companion object {
        // 연속부분수열에 펄스수열 (-1, 1, -1, ... 또는 1, -1, 1, ... )을 곱했을 때의 가장 큰 합
        fun solution(sequence: IntArray): Long {
            // a[0] - a[1] + a[2] - a[3] + a[4] - ...

            val n = sequence.size
            for (i in 0 until n step 2) {
                sequence[i] *= -1 // 짝수번째 값을 모두 음수로 -> 펄스수열 곱한것과 같은 효과
            }

            var maxSum1 = Long.MIN_VALUE
            var maxSum2 = Long.MIN_VALUE
            var curSum1 = 0L // sequence 그대로
            var curSum2 = 0L // sequence[i] * -1 (반대쪽 펄스수열)


            for (i in sequence.indices) {
                // 카데인 알고리즘 -> max(이전까지 누적한 합 + 현재값, 현재값) 을 선택해서 나아가기
                curSum1 = max(curSum1 + sequence[i], sequence[i].toLong())
                curSum2 = max(curSum2 - sequence[i], -sequence[i].toLong())

                maxSum1 = max(maxSum1, curSum1)
                maxSum2 = max(maxSum2, curSum2)
            }

            return max(maxSum1, maxSum2)

//         틀린 코드 -> 브루트포스로 풀었으니..
//         val dp = LongArray(n) // dp[i] = i부터 시작하는 부분수열들 중 가장 펄스부분수열 합이 큰 것

//         for(i in 0 until n) {
//             var curr: Long = sequence[i].toLong()
//             dp[i] = curr
//             for(j in i+1 until n) {
//                 curr += sequence[j]
//                 if(abs(dp[i]) < abs(curr)) dp[i] = curr
//             }
//         }

//         return dp.maxOf{abs(it)}
        }
    }
}

fun main() {
    val sequence = intArrayOf(2, 3, -6, 1, 3, -1, 2, 4)

    println(solution(sequence))
}