package no1

import java.util.Date
import javax.print.attribute.DateTimeSyntax
import scala.collection.mutable

object DogCatQueue extends App {

  // 继承Pet的Dog和Cat排入队列，可以按Dog或Cat
  trait Pet

  case class Dog(typeName: String = "dog") extends Pet

  case class Cat(typeName: String = "cat") extends Pet

  class PetQueue {
    private val dogQueue = new mutable.Queue[(Pet, Long)]()
    private val catQueue = new mutable.Queue[(Pet, Long)]()

    // 新增Pet
    def enqueue(pet: Pet) = {
      //按照Dog和Cat分别放入队列
      pet match {
        case dog: Dog => dogQueue.enqueue((dog, System.currentTimeMillis()))
        case cat: Cat => catQueue.enqueue((cat, System.currentTimeMillis()))
        case _ => throw new RuntimeException("err,not dog or cat")
      }
    }

    // 仅出Dog
    def dequeueDog() = {
      if (dogQueue.nonEmpty)
        dogQueue.dequeue()._1.asInstanceOf[Dog]
      else
        throw new RuntimeException("err,Dog queue is empty.")
    }


    // 仅出Cat
    def dequeueCat() = {
      if (catQueue.nonEmpty)
        catQueue.dequeue()._1.asInstanceOf[Cat]
      else
        throw new RuntimeException("err,Cat queue is empty.")
    }


    // 出Pet
    def dequeueAll() = {
      if (dogQueue.nonEmpty && catQueue.nonEmpty) {
        if (dogQueue.front._2 < catQueue.front._2)
          dogQueue.dequeue()._1
        else
          catQueue.dequeue()._1
      }else if (dogQueue.nonEmpty) {
        dogQueue.dequeue()._1
      }else if (catQueue.nonEmpty) {
        catQueue.dequeue()._1
      }else {
        throw new RuntimeException("err, Queue is empty")
      }

    }

    // 判断是否为空
    def isEmpty() = {
      dogQueue.isEmpty && catQueue.isEmpty
    }

    def isDogEmpty() = {
      dogQueue.isEmpty
    }

    def isCatEmpty() = {
      catQueue.isEmpty
    }

  }


  private val petQueue = new PetQueue()
  petQueue.enqueue(Dog("1"))
  petQueue.enqueue(Cat("2"))
  petQueue.enqueue(Dog("3"))

  while (!petQueue.isEmpty()) {
    println(petQueue.dequeueAll())
  }

}
