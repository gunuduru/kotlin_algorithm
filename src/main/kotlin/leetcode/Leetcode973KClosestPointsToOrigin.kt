package main.kotlin.leetcode

import main.kotlin.leetcode.Leetcode973KClosestPointsToOrigin.Companion.kClosest
import java.util.*
import kotlin.math.sqrt

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
            constructor(array: IntArray) : this(array[0], array[1])

            val distance: Double = sqrt((x * x + y * y).toDouble())
            fun toIntArray() = intArrayOf(x, y)
        }

        fun kClosest(points: Array<IntArray>, k: Int): Array<IntArray> {
            val pq = PriorityQueue<Position> { o1, o2 ->
                when {
                    o1.distance > o2.distance -> 1
                    o1.distance == o2.distance -> 0
                    else -> -1
                }
            }

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