package app.model

import app.model.TicketStatus.Fixed

import scala.collection.mutable

/**
  * Created by ogiwara on 2017/10/15.
  */
object TicketRepository {
  type TicketId = Long

  private val tickets: mutable.Map[TicketId,Ticket] = mutable.HashMap(
    1L -> Issue(1,"First Issue"),
    2L -> Issue(2,"Second Issue"),
    3L -> Bug(3,"First Bug","sample"),
    4L -> Bug(4,"Closed Issue","fixed",Fixed)
  )

  def findAll: Seq[Ticket] = tickets.values.toVector

  def findById(id: TicketId): Option[Ticket] = tickets.get(id)

  def createIssue(title: String): Issue = {
    val newId = if (tickets.isEmpty) 1 else tickets.keys.max + 1
    val issue = Issue(newId,title)
    tickets.put(newId,issue)
    issue
  }

  def findBugsByStatus(status: TicketStatus): Seq[Bug] ={
    findAll.collect {
      case t@Bug(_,_, `status`,_) => t
    }
  }

  def fix(id: TicketId): Boolean = ???
}
