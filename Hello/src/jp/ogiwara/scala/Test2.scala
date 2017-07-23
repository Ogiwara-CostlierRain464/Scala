package jp.ogiwara.scala

object Test2 {

  type Pair[+A,+B] = Tuple2[A,B]
  object Pair{
    def apply[A,B](x: A,y: B) = Tuple2(x,y)
    def unapply[A,B](x: Tuple2[A,B]): Option[Tuple2[A,B]] = Some(x)
  }

}

class Class1{
  private val field = 1

    //def equalFields(other: Class1) = field == other.field
}

object Class1{
  def unapply(c: Class1) = Some(c.field)
}

class ClassB{

}
