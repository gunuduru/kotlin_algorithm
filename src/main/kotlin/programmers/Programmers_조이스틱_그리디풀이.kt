package main.kotlin.programmers

import main.kotlin.programmers.Programmers_조이스틱_그리디풀이.Companion.solution
import kotlin.math.min

class Programmers_조이스틱_그리디풀이 {
    companion object {
        // AAA -> JAZ 만드는 최소 조작 횟수
        fun solution(name: String): Int {
            var answer = 0
            val n = name.length

            // 1. 상하 조작 횟수: 각 문자마다 위/아래 중 더 적은 쪽으로 바꾸는 횟수
            answer += name.map { ch -> min(ch - 'A', 'Z' - ch + 1) }.sum()

            // 2. 좌우 커서 이동 최소 계산
            var minMove = n - 1 // 기본값: 오른쪽으로만 끝까지 가는 경우

            for (i in 0 until n) {
                var next = i + 1 // A가 아닌 다음 문자의 인덱스 찾기
                while (next < n && name[next] == 'A') next++

                // i까지 오른쪽으로 이동 후 → A 구간 스킵 → 끝 문자 처리
                val move = i * 2 + (n - next)

                // 끝에서 먼저 이동 후 ← 왼쪽으로 되돌아와서 앞쪽 문자 처리
                val reverseMove = i + (n - next) * 2

                minMove = min(minMove, min(move, reverseMove))
            }

            return answer + minMove
        }
    }
}

fun main() {
//    val name = "JEROEN"
//    val name = "JAN"
    val name = "JAZ"

    println(solution(name))
}