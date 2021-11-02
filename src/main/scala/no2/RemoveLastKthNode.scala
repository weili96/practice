package no2

object RemoveLastKthNode extends App {

  /**
   *  移除单链表或双链表的倒数第k个节点
   */

  class Node(val value: Int) {
    var next: Node = _
  }

  def removeLastKthNode(head: Node, lastKth: Int) = {
    if (head == null || lastKth < 1)
      head
    else {
      var cur = head
      var tempHead = head
      var index = lastKth
      while (cur != null) {
        index -= 1
        cur = cur.next
      }

      if (index == 0)
        tempHead = head.next
      if (index < 0) {
        cur = tempHead
        while (index != 0) {
          cur = cur.next
          index += 1
        }
        cur.next = cur.next.next
      }
      tempHead
    }
  }

}
