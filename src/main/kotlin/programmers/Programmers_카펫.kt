package main.kotlin.programmers

import main.kotlin.programmers.Programmers_카펫.Companion.solution

class Programmers_카펫 {
    companion object {
        /**
         * 중앙에는 노란색, 테두리 한줄은 갈색
         * 가로 x 세로 y, 모든 격자의 사이즈는 1*1이라고 가정.
        [1] 2x + 2y - 4 = brown (brown = 카펫의 둘레)
        [2] (x-2) * (y-2) = yellow (테두리 제외 너비 = yellow)
        [3] x * y = brown+yellow (카펫 전체너비 = 모든 격자의 개수)
        ex) (10, 2) -> [1] 2x + 2y = 14 / [3] xy = 12 -> x+y = 7, xy = 12
         */
        fun solution(brown: Int, yellow: Int): IntArray {
            val area = brown + yellow // [3] x * y = brown+yellow (카펫 전체너비 = 모든 격자의 개수)

            // xy = area를 만족하는 (x, y) 중에서 2x + 2y = brown+4 를 만족하는 값을 찾는다.
            for (i in 1..area / 2) {
                if (area % i == 0) {
                    val height = i
                    val width = area / i // 가로 >= 세로 이므로
                    if (2 * width + 2 * height == brown + 4) return intArrayOf(width, height)
                }
            }

            return intArrayOf() // 위의 for문에서 답이 나오지만, 컴파일에러를 막기 위해 빈 배열 반환문 추가
        }
    }
}

fun main() {
    val brown = 10
    val yellow = 2

    println(solution(brown, yellow).contentToString())
}