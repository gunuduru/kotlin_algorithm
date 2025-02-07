package main.kotlin.programmers

class Programmers_단어변환 {
    companion object {
        fun solution(begin: String, target: String, words: Array<String>): Int {
            if(words.find { word -> word == target } == null) return 0

            val queue = ArrayDeque<String>()
            var cnt = 1
            queue.add(begin)

            while (queue.isNotEmpty()) {
                val qSize = queue.size
                (0 until qSize).forEach {
                    val curWord = queue.removeFirst()
                    words.filter { word -> curWord.isDifferentOnlyOneLetter(word) }
                        .forEach(queue::add)
                }

                if(queue.find { word -> word == target } != null) return cnt
                else cnt++
            }

            return cnt
        }

        fun String.isDifferentOnlyOneLetter(word: String): Boolean {
            var diffCount = 0
            (0 until this.length).forEach { idx ->
                if (this[idx] != word[idx]) diffCount++
                if (diffCount > 1) return false
            }

            return diffCount == 1
        }
    }
}

fun main() {
    val begin = "hit"
    val target = "cog"
//    val words = arrayOf("hot", "dot", "dog", "lot", "log", "cog") // expect 4
    val words = arrayOf("hot", "dot", "dog", "lot", "log") // expect 0
    println(Programmers_단어변환.solution(begin, target, words))
}

