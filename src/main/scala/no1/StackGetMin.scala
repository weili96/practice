package no1

import sun.util.calendar.BaseCalendar.Date

import scala.collection.mutable
import scala.runtime.RichInt

object StackGetMin extends App {

  // 栈的push和pop,getMin 时间复杂的都为 O(1)

  // 用两个栈来实现，一个存数据，一个存最小值

  // 方法1：插入快，弹出稍耗时
  class MyStack1 {
    private val stackData: mutable.Stack[Int] = mutable.Stack[Int]()
    private val stackMin: mutable.Stack[Int] = mutable.Stack[Int]()

    def push(num: Int) = {
      // 判断是否添加进stackMin
      //      if (stackMin.isEmpty)
      //        stackMin.push(num)
      //      else if (num <=  stackMin.top)
      //        stackMin.push(num)
      if (stackMin.isEmpty || num <=  stackMin.top)
        stackMin.push(num)

      stackData.push(num)
    }

    def pop() = {
      if (stackData.isEmpty)
        throw new RuntimeException("Stack is empty")

      val value = stackData.pop()
      if (value == getMin())
        stackMin.pop()

      value
    }

    def getMin() = {
      if (stackMin.isEmpty)
        throw new RuntimeException("Stack is empty")

      stackMin.top
    }
  }


  // 方法2：插入稍耗时，弹出快   ,  额外练习了一下比较和协变
  class MyStack2[T <: Ordered[T]] {

    private val stackData = new mutable.Stack[T]()
    private val stackMin = new mutable.Stack[T]()

    def push(value: T) = {
      if (stackMin.isEmpty || value <= getMin())
        stackMin.push(value)
      else
        stackMin.push(stackMin.top)

      stackMin.reverse

      stackData.push(value)
    }

    def pop() = {
      if (stackData.isEmpty)
        throw new RuntimeException("Stack is empty.")

      stackMin.pop()

      stackData.pop()
    }

    def getMin(): T = {
      if (stackMin.isEmpty)
        throw new RuntimeException("Stack is empty.")

      stackMin.top
    }

  }

  class Person(val name: String,val age: Int) extends Ordered[Person] {
    override def compare(that: Person): Int = this.age - that.age

    override def toString: String = s"Person($name, $age)"
  }

  private val stack = new MyStack2[Person]()

  stack.push(new Person("w",1))
  stack.push(new Person("jwl",3))
  stack.push(new Person("wl",2))
  stack.push(new Person("wwll",5))

  println(s"min: ${stack.getMin()}")
  println(s"pop: ${stack.pop()}")

}
