package no1

import scala.collection.mutable

object GetNum extends App {

  /**
   * 最大值减去最小值 小于或等于 给定num的 子数组数量
   *
   *  max(arr[i,j]) - min(arr[i,j]) <= num
   *
   * 有两个结论：
   *      如果一段不满足，那么包含它的都不满足
   *       如果一段满足，那么里面的都满足
   *
   * 用到双端队列
   */

  def getNum(arr: Array[Int], num: Int): Int = {

    var i = 0
    var j = 0
    var res = 0

    if (arr.isEmpty || num < 0 )
      0
    else {
      val qmin = mutable.ListBuffer[Int]()
      val qmax = mutable.ListBuffer[Int]()

        while (i < arr.length) {
          var flag = true
          while (j < arr.length && flag) {

            if (qmin.isEmpty || qmin.last != j) {
              while (qmin.nonEmpty && arr(qmin.last) >= arr(j))
                qmin.dropRightInPlace(1)
              qmin.addOne(j)
              while (qmax.nonEmpty && arr(qmax.last) <= arr(j))
                qmax.dropRightInPlace(1)
              qmax.addOne(j)
            }

            if (arr(qmax.head) - arr(qmin.head) > num)
              flag = false

            if (flag)
              j += 1
          }

          res += (j - i)
//          println(j - i)
          if (qmin.head == i)
            qmin.dropInPlace(1)
          if (qmax.head == i)
            qmax.dropInPlace(1)
          i += 1
        }
        res
      }
  }

  val arr = Array(2,4,6,8)

  println(getNum(arr, 3))

  //  主要  i == j  的情况：只有一个元素时


}
