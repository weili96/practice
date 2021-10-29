package no1

import scala.::
import scala.collection.mutable
import scala.collection.mutable.ListBuffer

object GetNearLess extends App {

  /**
   *  获取arr数组中，每个i位置左右离i位置最近的，且值比arr[i]小的位置:（x,y)
   *
   *  使用单调栈
   */

  // 数组中没有重复的值              要是重复，栈元素就要用list[Int]，来便于获取处理下标
  def getNearLessNoRepeat(arr: Array[Int]) = {

    val result = new Array[(Int, Int)](arr.length)

    val stack = mutable.Stack[Int]()

    for (i <- arr.indices) {
      while (stack.nonEmpty && arr(stack.top) > arr(i)) {
        val popValue = stack.pop()
        if (stack.isEmpty)
          result(popValue) = (-1, i)
        else
          result(popValue) = (stack.top, i)
      }
      stack.push(i)
    }

    while (stack.nonEmpty) {
      val popValue = stack.pop()
      if (stack.isEmpty)
        result(popValue) = (-1, -1)
      else
        result(popValue) = (stack.top, -1)
    }

    result
  }

  val arr = Array(3,4,1,5,6,2,7)

  private val results: Array[(Int, Int)] = getNearLessNoRepeat(arr)

  println(results.mkString("Array(", ", ", ")"))

}
