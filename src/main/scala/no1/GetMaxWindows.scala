package no1

import scala.collection.mutable

/**
 * 生成窗口最大值数组。
 *  在数组中移动窗口，取出每个窗口中的最大值
 */

object GetMaxWindows extends App {


  def getMaxValueInWindow(arr: Array[Int], w: Int) = {

    val currMax = new mutable.ListBuffer[Int]()

    val result = new Array[Int](arr.length - w +1)
    var index = 0
    for (i <- arr.indices) {

      while (currMax.nonEmpty && arr(currMax.last) <= arr(i)) {
        currMax.dropRightInPlace(1)
      }

      currMax.addOne(i)

      if (currMax.head == i - w)
        currMax.dropInPlace(1)

      if (i >= w - 1) {
        result(index) = arr(currMax.head)
        index += 1
      }
    }
    result
  }

  private val arr: Array[Int] = Array(4, 3, 5, 4, 3, 3, 6, 7)
  private val resultArr: Array[Int] = getMaxValueInWindow(arr, 3)
  println(resultArr.mkString("Array(", ", ", ")"))


}



