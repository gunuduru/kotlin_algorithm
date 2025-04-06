package main.kotlin.programmers

class Programmers_징검다리 {
    companion object {
        fun solution(distance: Int, rocks: IntArray, n: Int): Int {
            // 바위를 n개 제거했을 때, 각 지점 사이의 거리의 최솟값중에 "가장 큰 값"

            // -> "거리"를 기준으로 이분탐색을 해야함 (0 ~ distance)
            // -> 다음 바위 사이의 거리 < mid인 바위를 발견하면, 해당 바위를 제거 --> n개 이상으로 제거하는 경우엔 실패처리

            // 제거 전 -> (0), 2, 11, 14, 17, 21, (25)

            val sortedRocks = rocks.sorted() + distance // 마지막 지점까지 바위로 추가

            var left = 0
            var right = distance
            var answer = 0

            while (left <= right) {
                val mid = (left + right) / 2
                var prev = 0 // 현재 바위 이전의 돌. 초기값은 출발지점(0)
                var removeCnt = 0 // 현재 반복문에서 제거한 바위의 수

                for (rock in sortedRocks) {
                    if (rock - prev < mid) {
                        // 현재 바위는 거리가 너무 가까우므로 제거
                        removeCnt++
                        // prev = prev -> 현재 rock을 제거한 상태로 간주하므로, 다음 바위의 이전 위치는 기존의 prev로 고정시켜야 한다.
                    } else {
                        // 유지해도 되는 바위 -> 다음 바위의 prev를 현재의 rock으로 갱신
                        prev = rock
                    }
                }

                if (removeCnt > n) {
                    right = mid - 1 // 너무 많이 제거해야 하는 상태이므로, mid가 너무 크다고 판단.
                    // -> 더 작은거리범위로 늘려야 함 (n개보다 더 제거해야 하므로, 문제에서 요구하는 답을 낼 수 없음)
                    // 거리의 최솟값을 mid로 하려니 너무 많은(=n개보다 많은) 바위를 제거해야 하니, 우리의 관심사 밖이다. 그러므로, mid를 더 작게 해봐야 하니 좌측으로 이분탐색을 한다
                } else {
                    answer = mid // 현재까지 최선의 답은 mid
                    left = mid + 1
                    // 현재까지 "바위 n개 삭제시 거리의 최솟값 = mid"이고, 우리가 찾는 값은 "최솟값 중에 가장 큰 값"이니까, 우측으로 이분탐색을 한다
                }
            }

            return answer

        }
    }
}

fun main() {
    val distance = 25
    val rocks: IntArray = intArrayOf(2, 14, 11, 21, 17)
    val n = 2
    println(Programmers_징검다리.solution(distance, rocks, n))
}