package main.kotlin.programmers

class Programmers_K번째수 {
    companion object {
        // array가 [1, 5, 2, 6, 3, 7, 4], i = 2, j = 5, k = 3
        // -> 2번째~5번째 자르면 [5,2,6,3] -> 정렬하면 [2,3,5,6] -> k(3)번째 숫자는 5
        fun solution(array: IntArray, commands: Array<IntArray>): IntArray {
            val result = mutableListOf<Int>()
            commands.forEach { command ->
                val sliced = array.sliceArray(command[0] - 1 until command[1]).sorted()
                result.add(sliced[command[2] - 1])
            }

            return result.toIntArray()
        }
    }

}

fun main() {
    val array: IntArray = intArrayOf(1, 5, 2, 6, 3, 7, 4)
    val commands: Array<IntArray> = arrayOf(intArrayOf(2, 5, 3), intArrayOf(4, 4, 1), intArrayOf(1, 7, 3))
    println(Programmers_K번째수.solution(array, commands).contentToString())
}

