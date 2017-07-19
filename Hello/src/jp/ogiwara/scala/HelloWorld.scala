package jp.ogiwara.scala

object HelloWorld extends App{
  Numbers.searchData(12) match {
    case Some(x) => println(x)
    case _ => println(None)
  }
}

object Numbers{

  private val dataList = List(1,2,3,4,5)

  def searchData(number: Int): Option[String] = if(dataList.contains(number)) Some(number.toString) else None
}
