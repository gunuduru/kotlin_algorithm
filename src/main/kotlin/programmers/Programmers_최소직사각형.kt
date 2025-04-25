package main.kotlin.programmers

import java.util.*
import kotlin.math.max

class Programmers_최소직사각형 {
    companion object {
        data class Square(val width: Int, val height: Int) {
            /** 90도 회전시 기존 최대범위 이내에 포함되는가 */
            fun isIncludedWhenRotate(maxWidth: Int, maxHeight: Int): Boolean {
                return height <= maxWidth && width <= maxHeight
            }
        }

        fun solution(sizes: Array<IntArray>): Int {
            val squareList = sizes.map { size -> Square(width = size[0], height = size[1]) }

            // 1. 전체 사각형의 가로/세로 길이 최대값을 구함
            var maxWidth = 0
            var maxHeight = 0

            sizes.forEach { size ->
                maxWidth = max(maxWidth, size[0])
                maxHeight = max(maxHeight, size[1])
            }

            // 2. 현재 (가로 또는 세로가) 최대값을 갖는 명함을 회전했을 때, 기존 최대범위 이내에 존재한다면 해당 (가로 or 세로)값은 축소가능하다고 본다.

            // 2-1. 가로 쳌
            squareList.sortedWith(Comparator.comparing<Square, Int> { it.width }.thenBy { it.height })
            for (square in squareList) {
                if (square.width < maxWidth) maxWidth = square.width // 이전 반복문에서 최대값 사각형 회전시 포함되어서 continue된 상태
                if (square.isIncludedWhenRotate(maxWidth, maxHeight)) {
                    continue // 최대값 사각형 회전시 포함되므로 다음 사각형 확인
                } else {
                    break // 회전시 포함 안된다면 더이상 탐색 안함
                }
            }

            // 2-2. 세로 쳌
            squareList.sortedWith(Comparator.comparing<Square, Int> { it.height }.thenBy { it.width })
            for (square in squareList) {
                if (square.height < maxHeight) maxHeight = square.height // 이전 반복문에서 최대값 사각형 회전시 포함되어서 continue된 상태
                if (square.isIncludedWhenRotate(maxWidth, maxHeight)) {
                    continue // 최대값 사각형 회전시 포함되므로 다음 사각형 확인
                } else {
                    break // 회전시 포함 안된다면 더이상 탐색 안함
                }
            }

            return maxWidth * maxHeight
        }
    }
}

fun main() {
//    val priorities = intArrayOf(2, 1, 3, 2)
    val priorities = intArrayOf(1, 1, 9, 1, 1, 1)
//    val location = 2
    val location = 0

    println(Programmers_프로세스.solution(priorities, location))
}