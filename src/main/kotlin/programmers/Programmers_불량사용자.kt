package main.kotlin.programmers

import main.kotlin.programmers.Programmers_불량사용자.Companion.solution

class Programmers_불량사용자 {
    companion object {
        // bannedList[i] = i번째 banned_id 패턴에 매칭되는 user_id 리스트
        lateinit var bannedList: List<List<String>>

        // 중복되지 않는 정답 조합들을 저장 (각 조합은 Set<String> 형태)
        val result = mutableSetOf<Set<String>>()

        fun solution(user_id: Array<String>, banned_id: Array<String>): Int {
            // 각 banned_id에 대해 매칭 가능한 user_id 리스트를 생성
            bannedList = banned_id.map { banned ->
                user_id.filter { user -> isMatch(user, banned) }
            }

            // DFS로 가능한 모든 조합 탐색 시작
            dfs(0, mutableSetOf())

            return result.size
        }

        /**
         * depth: 현재 탐색 중인 bannedList 인덱스
         * path: 현재까지 선택된 user_id 집합 (중복 없는 방문자 역할)
         */
        fun dfs(depth: Int, path: MutableSet<String>) {
            if (depth == bannedList.size) {
                // 모든 banned_id 패턴에 대해 유효한 매칭을 완료했으면 결과에 추가
                result.add(path.toSet()) // 순서를 고려하지 않으므로 Set으로 저장
                return
            }

            // 현재 depth의 banned_id 패턴에 매칭되는 user_id 후보들에 대해 탐색
            for (user in bannedList[depth]) {
                if (user in path) continue // 이미 사용한 user는 중복 매칭 불가
                path.add(user)
                dfs(depth + 1, path)
                path.remove(user) // 백트래킹
            }
        }

        /**
         * 주어진 user_id가 banned_id 패턴과 일치하는지 확인
         * '*' 문자는 모든 문자와 매칭 가능
         */
        fun isMatch(user: String, banned: String): Boolean {
            if (user.length != banned.length) return false
            return user.indices.all { banned[it] == '*' || banned[it] == user[it] }
        }
    }
}

fun main() {
    val user_id = arrayOf("frodo", "fradi", "crodo", "abc123", "frodoc")
    val banned_id = arrayOf("fr*d*", "abc1**")

    println(solution(user_id, banned_id))
}