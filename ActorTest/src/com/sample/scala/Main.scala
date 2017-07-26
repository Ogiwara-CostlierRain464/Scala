package com.sample.scala

import akka.actor.{Actor, ActorSystem, Props}
import akka.routing.RoundRobinRouter

object Main extends App{
  val system = ActorSystem("helloSystem")
  val router = system.actorOf(Props[HelloActor].withRouter(RoundRobinRouter(4)))

  router ! "Hello"

  system.shutdown
}

class HelloActor extends Actor{
  def receive = {
    case "Hello" => println("Hello,World!")
    case _ => println("Bad World!")
  }
}
