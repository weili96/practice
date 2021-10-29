package no1

import scala.collection.mutable

/**
 * 求最大子矩阵的大小：
 *  一个整型矩阵，只有0和1，求全为1的矩形区域中，最大矩形区域中1的数量。
 *
 *  用到单调栈
 */
object MaxRecSize extends App {

  // 以行来分割，每次处理一行计算上面行中对应的连续的1

  def maxRecSize(map: Array[Array[Int]]) = {

    if (map.isEmpty || map(0).isEmpty) {
      0
    } else {
      var maxArea   = 0
      val height    = new Array[Int](map(0).length)
      for (i <- map.indices) {
        for (j <- map(0).indices) {
          height(j) = if (map(i)(j) == 0) 0 else height(j) + 1
        }
        maxArea = Math.max(maxRecFromBottom(height), maxArea)
      }
      maxArea
    }
  }

  def maxRecFromBottom(height: Array[Int]) = {
    if (height == null || height.length == 0) {
      0
    } else {
      var maxArea = 0
      val stack = new mutable.Stack[Int]()

      for (i <- height.indices) {
        while (stack.nonEmpty && height(i) <= height(stack.top)) {
          val j       = stack.pop()
          val k       = if (stack.isEmpty) -1 else stack.top
          val curArea = (i - k - 1) * height(j)
          maxArea     = Math.max(maxArea, curArea)
        }
        stack.push(i)
      }

      while (stack.nonEmpty) {
        val j       = stack.pop()
        val k       = if (stack.isEmpty) -1 else stack.top
        val curArea = (height.length - k -1) * height(j)
        maxArea     = Math.max(maxArea, curArea)
      }
      maxArea
    }
  }


  val arr = Array(
    Array(1,0,1,1),
    Array(1,1,1,1),
    Array(1,1,1,0)
  )

  println(maxRecSize(arr))

}
