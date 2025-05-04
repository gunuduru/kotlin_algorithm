package main.kotlin.programmers

import main.kotlin.programmers.Programmers_베스트앨범.Companion.solution
import java.util.*

class Programmers_베스트앨범 {
    companion object {
        data class PlayIndex(val play: Int, val idx: Int)

        fun solution(genres: Array<String>, plays: IntArray): IntArray {
            val genreMap = mutableMapOf<String, PriorityQueue<PlayIndex>>()

            // 1. 장르별 우선순위 큐 구성
            for (i in genres.indices) {
                val queue = genreMap.getOrPut(genres[i]) {
                    PriorityQueue(compareByDescending<PlayIndex> { it.play }.thenBy { it.idx })
                }
                queue.add(PlayIndex(plays[i], i))
            }

            // 2. 장르별 총 재생수 기준 정렬
            val sortedGenres = genreMap.entries
                .map { it.key to it.value.sumOf { pi -> pi.play } }
                .sortedByDescending { it.second }

            // 3. 각 장르별 상위 2개 곡 추출
            val result = mutableListOf<Int>()
            for ((genre, _) in sortedGenres) {
                val queue = genreMap[genre]!!
                repeat(minOf(2, queue.size)) {
                    result.add(queue.poll().idx)
                }
            }

            return result.toIntArray()
        }

        fun solution2(genres: Array<String>, plays: IntArray): IntArray {
            return genres.indices                       // [0, 1, 2, ...]
                .groupBy { genres[it] }                // Map<String, List<Int>> → 장르별 인덱스 그룹화
                .toList()                              // List<Pair<String, List<Int>>> → 정렬을 위해 List로 변환
                .sortedByDescending { it.second.sumOf { plays[it] } } // 재생 수 총합 기준 정렬
                .map { it.second.sortedByDescending { plays[it] }.take(2) } // 장르별 상위 2개 추출
                .flatten()                             // 2차원 리스트 → 1차원으로 평탄화
                .toIntArray()                          // 배열로 변환
        }
    }
}

fun main() {
    val genre = arrayOf("classic", "pop", "classic", "classic", "pop")
    val plays = intArrayOf(500, 600, 150, 800, 2500)

    println(solution(genre, plays).joinToString())
}