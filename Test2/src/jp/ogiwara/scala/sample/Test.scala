package jp.ogiwara.scala.sample

import scala.annotation.tailrec

object Test {
  def exec() = {
    /*for(i <- 0 to 3){
      exceptionToLeft(throwsOnOddInt(i)) match {
        case Left(ex) => println(s"Oops, got exception: ${ex.toString}")
        case Right(x) => println(x)
      }
    }*/
    //optionalIsGood
    def concatUpper(s1: String,s2: String) = s"${s1} ${s2}".toUpperCase

    val c = concatUpper _
    println(c("short","pants"))

    val c2 = concatUpper("short", _: String)
    println(c2("pants"))
  }

  def exceptionToLeft[T](f: => T): Either[Throwable,T] = try{
    Right(f)
  } catch {
    case ex: Throwable => Left(ex)
  }

  def throwsOnOddInt(i: Int) = i % 2 match {
    case 0 => i
    case 1 => throw new RuntimeException(s"$i is odd!")
  }

  //stack over flow able
  def factorial(i: BigInt): BigInt = i match {
    case _ if i == 1 => i
    case _ => i * factorial(i - 1)
  }

  //non-stack over flow
  def factorial2(i: BigInt): BigInt = {
    def fact(i: BigInt,accumulator: BigInt): BigInt = i match {
      case _ if i == 1 => accumulator
      case _ => fact(i - 1, i * accumulator)
    }
    fact(i,1)
  }

  def 畳み込み: Unit ={
    List(1,2,3,4,5).foldLeft(List[String]()) {
      (list,x) => ("<" + x + ">") :: list
    }.reverse.foreach(println)
  }

  def optionalIsGood = {
    val someNumber = Some(5)
    val noneNumber = None
    for(option <- List(someNumber,noneNumber)){
      option.map(n => println(n * 5))
    }
  }
}

object Person{
  def apply(name: String,age: Int) = new Person(name,age)
  def unapply(p: Person) = Some(p)
}

class Person(val name: String,val age: Int){
  def say() = println(s"$name is $age years old.")

  override def toString: String = s"Person[$name $age]"
}

abstract class Shape(val id: String){
  def draw() = Unit
}

case class Circle(override val id: String) extends Shape(id){
  //case classの継承は禁止されている
  //Kotlinではdata は sealedが自動で付与される
}
