package main.kotlin.leetcode

import main.kotlin.leetcode.Leetcode973KClosestPointsToOrigin.Companion.kClosest
import java.util.*

/**
 * Example:
 * var li = ListNode(5)
 * var v = li.`val`
 * Definition for singly-linked list.
 * class ListNode(var `val`: Int) {
 *     var next: ListNode? = null
 * }
 */
class Leetcode973KClosestPointsToOrigin {

    companion object {
        class Position(val x: Int, val y: Int) {
            constructor(array: IntArray) : this(
                array[0],
                array[1]
            ) // array는 size 2인 Int 배열이 반드시 들어온다는 가정 하에 생성자 메소드를 구현하였다. 실제 사용시에는 예외처리 필수

            val distance = x * x + y * y
            fun toIntArray() = intArrayOf(x, y)
        }

        fun kClosest(points: Array<IntArray>, k: Int): Array<IntArray> {
            val pq = PriorityQueue<Position>(compareBy { position -> position.distance })

            points.map { point -> Position(point) }
                .forEach { position ->
                    pq.add(position)
                }

            return (1..k).map { pq.poll().toIntArray() }.toTypedArray()
        }
    }
}

fun main() {
    val points = arrayOf(intArrayOf(1, 3), intArrayOf(-2, 2))
//    val points = arrayOf(intArrayOf(3, 3), intArrayOf(5, -1), intArrayOf(-2, 4))
    val k = 1
//    val k = 2

    kClosest(points, k).forEach { array -> println(array.contentToString()) }
}