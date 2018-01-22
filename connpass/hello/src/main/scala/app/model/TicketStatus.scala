package app.model

/**
  * sealedにすると、同じファイル内でしか継承できないようにする
  */
sealed trait TicketStatus

object TicketStatus {
  case object Open extends TicketStatus
  case object Fixed extends TicketStatus
}