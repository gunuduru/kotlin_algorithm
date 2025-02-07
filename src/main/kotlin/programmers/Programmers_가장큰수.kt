package main.kotlin.programmers

class Programmers_가장큰수 {
    companion object {
        fun solution(numbers: IntArray): String {
            val numStrList = numbers.map(Int::toString).sortedWith { s1, s2 -> "$s2$s1".compareTo("$s1$s2") }
            // "3" vs "30" -> "330" vs "303" -> "330" 승

            val result =  numStrList.joinToString("")

            return if(result.startsWith("0")) "0" else result // "0000" -> "0"으로 반환해야 하므로
        }
    }

}

fun main() {
    val numbers = arrayOf(intArrayOf(6, 10, 2), intArrayOf(3, 30, 34, 5, 9))
    val expects = arrayOf("6210", "9534330")
    numbers.forEachIndexed { idx, number ->
        val result = Programmers_가장큰수.solution(number)
        println("${idx+1}: $result -> 결과: ${result == expects[idx]}")
    }
}

