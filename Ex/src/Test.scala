import bounds._

implicit def any2Node[A](x: A): Node[A] = bounds.::[A](x,NilNode)

//クライアントはNodeの正しい生成方法について少しも心配しなくてよい
case class LinkedList[A <% Node[A]](val head: Node[A]){

  def ::[B >: A <% Node[B]](x: Node[B]): LinkedList[B] =
    LinkedList(bounds.::(x.payload,head))

  override def toString = head.toString
}

object Test extends App{
  val list = LinkedList(1)
  val list2 = 2 :: list
  val list3 = 3 :: list2
  val list4 = "FOUR!" :: list3

  println(list)
  println(list3)
  println(list4)
}