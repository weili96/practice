package no2

object PrintCommonPart extends App {

  /**
   *  打印两个有序链表的公共部分
   */

  // 创建链表节点
  class Node(val value: Int) {
    var next:Node = _
  }

  def printCommonPart(head1:Node, head2: Node) = {

    println("Common Part:")

    var h1 = head1
    var h2 = head2

    while (h1.next != null && h2.next != null) {
      if (h1.value < h2.value)
        h1 = h1.next
      else if (h1.value > h2.value)
        h2 = h2.next
      else {
        print(s"${h1.value} ")
        h1 = h1.next
        h2 = h2.next
      }
    }
  }

  val node1 = new Node(1)
  val node2 = new Node(2)
  val node3 = new Node(3)
  val node4 = new Node(4)
  val node5 = new Node(5)

  val node22 = new Node(2)
  val node33 = new Node(3)
  val node44 = new Node(4)
  val node55 = new Node(1)

  val head1 = node1
  node1.next = node2
  node2.next = node3
  node3.next = node4
  node4.next = node5

  val head2 = node22
  node22.next = node33
  node33.next = node44
  node44.next = node55

  printCommonPart(head1, head2)


}
