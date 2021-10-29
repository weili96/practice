package no1

import scala.collection.mutable

object SortStackByStack extends App {

  // 用一个栈实现另一个栈的排序

  /**
   *  stack弹出一个放到temp, 与helpStack第一个比较，
   *  要是小于放入，
   *  要是大于把helpStack往stack回放到小于temp
   */

  def sortByStack(stack: mutable.Stack[Int]) = {

    val helpStack = new mutable.Stack[Int]()

    while (stack.nonEmpty) {
      val temp = stack.pop()
      while (helpStack.nonEmpty && helpStack.top < temp)
        stack.push(helpStack.pop())
      helpStack.push(temp)
    }

    while (helpStack.nonEmpty)
      stack.push(helpStack.pop())

  }


  val stack = mutable.Stack(1,3,2,5,4,6,7)
  println(stack)

  sortByStack(stack)
  println(stack)



}
