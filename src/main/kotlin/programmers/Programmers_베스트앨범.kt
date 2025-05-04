package main.kotlin.programmers

import main.kotlin.programmers.Programmers_베스트앨범.Companion.solution
import java.util.*

class Programmers_베스트앨범 {
    companion object {
        data class PlayIndex(val play: Int, val idx: Int)
        data class GenrePlayCnt(val genre: String, val playCnt: Int)

        fun solution(genres: Array<String>, plays: IntArray): IntArray {
            val genreToPlayIndexPQ = mutableMapOf<String, PriorityQueue<PlayIndex>>()

            val result = mutableListOf<Int>()

            for (i in 0 until genres.size) {
                genreToPlayIndexPQ.computeIfAbsent(genres[i]) {
                    PriorityQueue<PlayIndex>(
                        compareByDescending<PlayIndex> { it.play }.thenBy { it.idx }
                    )
                }

                genreToPlayIndexPQ[genres[i]]!!.add(PlayIndex(plays[i], i))
            }

            val pq = PriorityQueue<GenrePlayCnt>(compareByDescending { it.playCnt })

            genreToPlayIndexPQ.entries.map { pq.add(GenrePlayCnt(it.key, it.value.sumOf { it.play })) }

            while (pq.isNotEmpty()) {
                val genre = pq.poll().genre

                // 2 이하의 숫자로 반환
                repeat(genreToPlayIndexPQ[genre]!!.size.coerceAtMost(2)) {
                    val cur = genreToPlayIndexPQ[genre]!!.poll()
                    result.add(cur.idx)
                }

            }

            return result.toIntArray()
        }
    }
}

fun main() {
    val genre = arrayOf("classic", "pop", "classic", "classic", "pop")
    val plays = intArrayOf(500, 600, 150, 800, 2500)

    println(solution(genre, plays).joinToString())
}