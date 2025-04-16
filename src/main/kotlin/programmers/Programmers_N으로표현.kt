package main.kotlin.programmers

import main.kotlin.programmers.Programmers_N으로표현.Companion.solution
import java.util.*

class Programmers_N으로표현 {
    companion object {
        /**
         * N 사용횟수의 최솟값을 리턴해야 함
         * dp[i] = N을 i번 사용해서 만들 수 있는 모든 수
         * 괄호가 가능하므로, dp[i]는 dp[j] 와 dp[i-j]의 사칙연산이라고 볼 수 있음 (1 <= j < i)
         * -> dp[1]은 N 하나뿐
         * -> dp[2]는 dp[1]과 dp[1]의 사칙연산 + NN(repeated)
         * -> dp[3]은 dp[1]과 dp[2]의 사칙연산, dp[2]와 dp[1]의 사칙연산(순서가 달라지므로 다른 결과임) + NNN(repeated)
         * (...) -> 매 for-i문 종료 직전에 (number in dp[i])가 true라면 해당 i를 반환
         */
        fun solution(N: Int, number: Int): Int {
            val dp = Array(9) { mutableSetOf<Int>() } // 1 ~ 8까지만 확인 (8보다 크면 -1을 반환하므로, 관심사는 8 이하의 index)

            // i: N의 사용횟수
            for (i in 1..8) {
                val repeated = N.toString().repeat(i).toInt() // N이 i번 붙어있는 경우.
                dp[i].add(repeated) // N이 i번 붙어있는 경우도 가능한 숫자에 추가

                // j: dp[i]의 부분인덱스. dp[j]와 dp[i-j]로 dp[i]를 만든다.
                for (j in 1 until i) {
                    for (left in dp[j]) {
                        for (right in dp[i - j]) {
                            dp[i].add(left + right)
                            dp[i].add(left - right)
                            dp[i].add(left * right)
                            if (right != 0) dp[i].add(left / right)
                        }
                    }
                }

                if (number in dp[i]) return i
            }

            return -1 // for-i문을 완료했는데도 적당한 i를 찾지 못했다면, i>8이므로 -1을 반환
        }
    }
}

fun main() {
//    val N = 5
     val N = 2
//    val number = 12
     val number = 11

    println(solution(N, number))
}