package bounds

class Test4 {

}

abstract trait Node[+A]{
  def payload: A
  def next: Node[A]
}

case class ::[+A](val payload: A,val next: Node[A]) extends Node[A]{
  override def toString = s"(${payload.toString} :: ${next.toString})"
}

object NilNode extends Node[Nothing]{
  def payload = throw new NoSuchElementException("No payload in NilNode")
  def next = throw new NoSuchElementException("No next in NilNode")

  override def toString = "*"
}

class C1{
  var x = "1"
  def setX1(x: String): Unit ={
    this.x = x
  }
}

class C2 extends C1
class C3 extends C2{
  def setX3(x: String) = super[C1].x
}

class C9{ self =>
  def talk(message: String) = println(s"C9.talk : ${message}")
  class C10{
    class C11{
      def talk(message: String) = self.talk(s"C11.talk: ${message}")
    }
    val c11 = new C11()
  }
  val c10 = new C10()
}

trait Subject{
  type Aha = { //ダックタイピング  Rubyにはmethod-missingなんてものも -> インターフェースじゃだめ？
    def c()
  }

  private var ahas = List[Aha]()
}