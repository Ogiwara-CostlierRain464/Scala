package example

import app.model.{Bug, Issue, Ticket}
import app.model.TicketStatus.Open

import scala.annotation.tailrec

object Hello extends App {
  implicit def any2Issue[A](x: A): Issue = new Issue(0,x.toString)

  println(Scalable.factorial(10))
}

trait Greeting {
  lazy val greeting: String = "Hello, Scala"
}

object Scalable{

  def factorial(i: BigInt): BigInt = {

    @tailrec
    def fact(i: BigInt, accumulator: BigInt): BigInt = {
      if(i <= 1) accumulator
      else fact(i - 1, i * accumulator)
    }

    fact(i,1)
  }
}

