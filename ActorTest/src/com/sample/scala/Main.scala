package com.sample.scala

import akka.actor.{Actor, ActorRef, ActorSystem, Props}

import scala.collection.{immutable, mutable}
import scala.util.Random

object Main extends App{
  //ActorSystem: ここからactorを生成していく
  val system = ActorSystem("system")

  val ref = system.actorOf(Props[HelloActor])

  ref ! "Hello"
  ref ! "Hoge!"

  system.shutdown
}

class HelloActor extends Actor{
  override def receive = {
    case "count" => {
      println(s"size: ")
    }
  }
}


object BarbershopSimulator{

  private val system = ActorSystem("system")
  private val random = new Random
  private val customers = new mutable.ArrayBuffer[ActorRef]()
  private val shop = system.actorOf(Props[Shop])

  def generateCustomers(): Unit = {
    for(i <- 1 to 20){
      val customer = system.actorOf(Props[Customer])
      customers += customer
    }

    println(s"[!] generated ${customers.size} customers")
  }

  def trickleCustomers(): Unit = {
    for(customer <- customers){
      shop ! customer
      Thread.sleep(random.nextInt(450))
    }
  }

  def tallyCuts(): Unit ={
    Thread.sleep(2000)

    val shornCut = customers.filter()
  }
}

//内部データを持たないメッセージ
case object Haircut

class Customer(val id: Int) extends Actor{

  var shorn = false

  override def receive = {
    case Haircut => {
      shorn = true
      println(s"[c] customer $id got a haircut!")
    }
  }
}

class Barber extends Actor{

  private val random = new Random

  def helpCustomer(customer: Customer){
    if(context.dispatcher.prerequisites.deadLetterMailbox.messageQueue.numberOfMessages >= 3){
        println(s"[b] not enough seats, turning customer ${customer.id} away")
    }else{
      println(s"[b] cutting hair of customer ${customer.id}")

      Thread.sleep(100 + random.nextInt(400))
      customer.self ! Haircut
    }
  }

  override def receive = {
    case customer: Customer => helpCustomer(customer)
  }
}

class Shop extends Actor{

  val barber = new Barber

  override def receive = {
    case customer: Customer => barber.self ! customer
  }
}
