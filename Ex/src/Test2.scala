object Test2{
  def exec(): Unit ={
    val list = 1 :: 2 :: 3 :: AbbrevNil
    println(list)
  }
}

sealed abstract class AbbrevList[+A]{

  def isEmpty: Boolean
  def head: A
  def tail: AbbrevList[A]

  def :: [B >: A] (x: B): AbbrevList[B] = new ::(x,this)

  final def foreach(f: A => Unit) = {
    var these = this
    while(!these.isEmpty){
      f(these.head)
      these = these.tail
    }

  }
}

case object AbbrevNil extends AbbrevList[Nothing]{
  override def isEmpty = true

  def head: Nothing =
    throw new NoSuchElementException("head of empty AbbrevList")

  def tail: AbbrevList[Nothing] =
    throw new NoSuchElementException("tail of empty AbbrevList")
}

final case class ::[B](private var hd: B,
                       private var tl: AbbrevList[B]) extends AbbrevList[B]{
  override def isEmpty: Boolean = false
  def head: B = hd
  def tail: AbbrevList[B] = tl
}