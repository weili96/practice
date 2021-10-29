package no1

import scala.collection.mutable

object ReverseStackRecursion extends App {

  /**
   *  使用递归和栈本身，完成栈的反转
   *
   */

  // 获取栈低的值并移除
  def getLastAndRemoveElement(stack: mutable.Stack[Int]): Int = {

    val tempNum = stack.pop()

    if (stack.isEmpty)
      tempNum
    else {
      val lastNum = getLastAndRemoveElement(stack)
      stack.push(tempNum)
      lastNum
    }
  }

  // 进行反转插入
  def reverse(stack: mutable.Stack[Int]):Unit = {
    if (stack.nonEmpty) {
      val last = getLastAndRemoveElement(stack)
      reverse(stack)
      stack.push(last)
    }
  }


  private val stack = mutable.Stack(1, 2, 3, 4, 5)

  println(stack)

 reverse(stack)

  println(stack)

}
