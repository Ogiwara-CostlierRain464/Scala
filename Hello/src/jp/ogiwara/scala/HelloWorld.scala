package jp.ogiwara.scala

import scala.util.control.Breaks.{break, breakable}
import org.junit.Test

object HelloWorld extends App{

  implicit def AofT[T](x: T) = new { def |>[S] (f: T => S): S = f(x) }

  //Factorial.exec(10) |> println
  //NumPlay.exec(3257) |> println
  //FizzBuzz.exec(21) |> println
  //Leet.exec
  //OptionalTest.exec("test2")
  //Functional.exec
  //Function.double(2) |> println
  //Function.double2(2) |> println
  //val tuple = 1 -> 2 -> 3 -> "Str" -> 'symbol
  //OptionTest.exec()
  //val f = (x: Int) ⇒ x*2
  //右側束縛.exec()
  //MatchTest.exec()
  /*val class1 = new jp.ogiwara.scala.Class1
  class1 match {
    case Class1(label) => println(label)
  }*/
  //println(class1.field)
  println("Hello, world")
}


object Factorial{
  def exec(end: Int) = 1 to end reduce {(e,i) => e*i}
}

object NumPlay{
  def exec(in: Int): Int = {
    var result = in
    breakable {
      while(true){
        var a = result / 10
        var b = result % 10
        result = a + b
        if(result < 10){
          break
        }
      }
    }
    result
  }
}

object FizzBuzz{
  def exec(until: Int): Seq[String] = {
    for(i <- 1 to until) yield{
      if(i % 15 == 0){
        "FizzBuzz"
      }else if(i % 5 == 0){
        "Buzz"
      }else if(i % 3 == 0){
        "Fizz"
      }else{
        i.toString
      }
    }
  }
}

object Leet{

  def exec(){
    "EMACSISBETTER" toCharArray() map leet foreach print
  }

  def leet(char: Char) =
    char match {
      case 'A' => 'E'
      case 'E' => '3'
      case 'G' => '6'
      case 'I' => '1'
      case 'O' => '0'
      case 'S' => '5'
      case 'Z' => '2'
      case _ => char
    }
}

object OptionalTest{
  def exec(key: String): Unit ={
    val map = Map("test" -> "hoge","test2" -> null)
    map.get(key) match {
      case Some(v) if v == null => print(1)
      case Some(v) => print(v)
      case None => print("NOTHING")
    }
  }
}

object Functional{
  def exec(): Unit ={
    List("a","b","c").map(_.toUpperCase).foreach(println)

    List("a","b","c").collect {
      case "a" => "A"
      case s => s
    }.foreach(println)

    print(List(1,2,3,4,5).find(_ > 4))
  }
}

object Function{
  // = がないと手続き的な関数
  def double(i: Int){
    2 * i
  }

  // = があると関数型的な関数(関数型プログラミングにおいて、関数は常に値を返す)
  def double2(i: Int) = {
    2 * i
  }

}

object OptionTest{
  def exec(){
    val map = Map(
      "Taro" → 12,
      "Jiro" -> 10,
      "Sub" -> 9
    )

    println(map.get("Taro"))
    println(map.get("UNKOWN"))

    println(map("Sub"))
    println(map.getOrElse("?", "Oops!"))
  }
}

abstract class BulkReader[In]{
  val source: In
}

object 右側束縛{

  def exec(): Unit ={
    val list = List('b','c','d')
    println('a' :: list ++ List('e','f'))
  }
}

object MatchTest{
  def exec(): Unit ={
    class Role
    case object Manager extends Role
    case object Developer extends Role

    case class Person(name: String,age: Int,role: Role)

    val alice = new Person("Alice",25,Developer)
    val bob = new Person("Bob",32,Manager)
    val charlie = new Person("Charlie",32,Developer)

    for(item ← Map(1 -> alice,2 -> bob,3 -> charlie)){
      item match {
        case (id,p @ Person(_,_,Manager)) => println(s"$p is overpaid")
        case (id,p @ Person(_,_,_)) => println(s"$p is underpaid.")
      }
    }
  }
}

object Try{
  def exec(): Unit ={
    val then = null
    import java.util.Calendar
    val now = Calendar.getInstance()

    try{
      now.compareTo(then)
    } catch {
      case e: NullPointerException => println("ぬルポ!"); System.exit(-1);
      case unknown => println(s"Unknown exception: $unknown"); System.exit(-1)
    }finally {

    }
  }
}