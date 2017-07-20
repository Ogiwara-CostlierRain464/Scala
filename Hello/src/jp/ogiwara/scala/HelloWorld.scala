package jp.ogiwara.scala

import scala.util.control.Breaks.{breakable, break}

object HelloWorld extends App{

  implicit def AofT[T](x: T) = new { def |>[S] (f: T => S): S = f(x) }

  Factorial.exec(10) |> println
  NumPlay.exec(3257) |> println
  FizzBuzz.exec(21) |> println
  Leet.exec
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