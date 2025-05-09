package main.kotlin.programmers

import main.kotlin.programmers.Programmers_풍선터트리기.Companion.solution

class Programmers_풍선터트리기 {
    companion object {
        fun solution(a: IntArray): Int {
            val n = a.size
            val leftMin = IntArray(n)
            val rightMin = IntArray(n)

            leftMin[0] = a[0]
            for (i in 1 until n) {
                leftMin[i] = minOf(leftMin[i - 1], a[i]) // 좌측에서 누적된 최솟값
            }

            rightMin[n - 1] = a[n - 1]
            for (i in n - 2 downTo 0) {
                rightMin[i] = minOf(rightMin[i + 1], a[i]) // 우측에서 누적된 최솟값
            }

            var count = 0
            for (i in 0 until n) {
                // 양끝값이거나 || 좌측까지의 최솟값보다 작거나 || 우측까지의 최솟값보다 작을 경우
                // 한쪽에 큰 풍선이 있더라도, 다른 쪽으로 진행되는 세계선에서는 내가 안 터질 수 있기 때문에 괜찮다.
                // -> 풍선 i가 살아남을 수 있으려면, { 왼쪽 방향으로 진행되는 경로 || 오른쪽 방향으로 진행되는 경로 } 둘 중 하나에서라도 끝까지 살아남을 수 있으면 정답이다.
                // 즉, 풍선 게임은 마치 **"두 갈래의 우주선"**처럼 두 개의 시뮬레이션이 진행되는 것과 같다 → "한쪽에서는 내가 터지더라도, 다른 쪽에서는 안 터지면 난 살 수 있음"
                // 양 끝값이 가능한 이유 -> if (끝값 > 반대편 남은놈) 찬스 사용 else 반대편 남은놈 터트림
                if (i == 0 || i == n - 1 || a[i] < leftMin[i - 1] || a[i] < rightMin[i + 1]) {
                    count++
                }
            }

            return count
        }
    }
}

fun main() {
    val a = intArrayOf(9, -1, -5)
    println(solution(a))
}

