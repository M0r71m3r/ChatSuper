import akka.actor.{Actor, ActorPath}

class ActorBox extends Actor {
  def receive  = {

    case _ =>
  }
}

class SendActor extends  Actor {
  def receive  = {

    case msg: Message =>
  }
}