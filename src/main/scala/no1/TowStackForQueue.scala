package no1

import scala.collection.mutable

object TowStackForQueue extends App {

  // 用两个栈实现队列

  private val stackPush = new mutable.Stack[Int]()
  private val stackPop = new mutable.Stack[Int]()

  // 把stackPush导入stackPop： stackPop为空时才可以导，并且一次要把stackPush里的都导入
  def pushToPop() = {

    if (stackPop.isEmpty) {
      while (stackPush.nonEmpty) {
        stackPop.push(stackPush.pop())
      }
    }

  }


  def enqueue(value: Int) = {
    stackPush.push(value)
  }

  def dequeue() = {
    if (stackPop.isEmpty && stackPush.isEmpty)
      throw new RuntimeException("Queue is empty.")

    pushToPop()

    stackPop.pop()
  }

  def front = {
    stackPop.top
  }

}
