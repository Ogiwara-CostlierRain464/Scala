package app.model

import app.model.TicketStatus.Open

trait Ticket {
  val id: Long
  val title: String
  val status: TicketStatus
}

case class Issue(id: Long,
                 title: String,
                 status: TicketStatus = Open) extends Ticket
case class Bug(id: Long,
               title: String,
               description: String,
               status: TicketStatus = Open) extends Ticket