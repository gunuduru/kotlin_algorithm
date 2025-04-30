package main.kotlin.programmers

import main.kotlin.programmers.Programmers_조이스틱.Companion.solution
import kotlin.math.abs
import kotlin.math.min

class Programmers_조이스틱 {
    companion object {
        // AAA -> JAZ 만드는 최소 조작 횟수
        fun solution(name: String): Int {
            val gapList: List<Int> = name.map { char ->
                min(
                    char - 'A',
                    'Z' - char + 1
                )
            } // gapList[i]: i번째 문자를 완성문자로 바꾸기 위한 조작횟수. 위로 가거나, 아래로 가거나
            val nameLength = name.length
            val visited = BooleanArray(nameLength + 1)
            val ctrlCnt = gapList.count { it > 0 } // 조작해야하는 (A가 아닌) 문자의 숫자
            var minMove = Int.MAX_VALUE

            /**
            - 커서를 좌우로 움직이는 조작 횟수만을 반환한다. 상하로 움직이는 것은 gapList.sum()으로 구할 수 있기 때문
            @param curIdx 현재 인덱스
            @param curMove 현재까지의 조작횟수
            @param charCnt 조작한 문자 수. ctrlCnt가 되면 종료해야 한다.
             */
            fun dfs(curIdx: Int, curMove: Int, charCnt: Int) {
                if (charCnt == ctrlCnt) {
                    if (minMove > curMove) minMove = curMove
                    return
                }

                for (nextIdx in 0 until nameLength) {
                    if (!visited[nextIdx] && gapList[nextIdx] != 0) {
                        val idxGap = abs(nextIdx - curIdx) // 현재 문자와 다음 문자와의 인덱스 차이
                        visited[nextIdx] = true
                        dfs(
                            nextIdx,
                            curMove + min(nameLength - idxGap, idxGap), // 좌우 중 가장 덜 움직여도 되는 방향으로 curMove갱신
                            charCnt + 1
                        )
                        visited[nextIdx] = false // 다음 문자도 확인해봐야
                    }
                }
            }

            visited[0] = true
            dfs(
                0,
                0,
                if (gapList[0] > 0) 1 else 0 // 0번 문자가 ctrlCnt에 포함된다면 1을 담는다
            )

            return gapList.sum() + minMove
        }
    }
}

fun main() {
//    val name = "JEROEN"
//    val name = "JAN"
    val name = "JAZ"

    println(solution(name))
}