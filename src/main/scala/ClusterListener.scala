import akka.actor.{Actor, ActorLogging, ActorRef, ActorSystem, Address, AddressFromURIString, RootActorPath}
import akka.actor.Props
import akka.cluster.Cluster
import akka.cluster.ClusterEvent._
import akka.cluster.ClusterEvent.ClusterDomainEvent
import akka.cluster.client.ClusterClientUp
import akka.cluster.typed.JoinSeedNodes
import akka.event.Logging
import com.typesafe.config.ConfigFactory

class ClusterListener(controllerImpl: ControllerImpl) extends Actor with ActorLogging {

  val ActorLost = controllerImpl.system.actorOf(Props[ClusterListener], "ActorLost")

  def receive: Receive = {
    case MemberWeaklyUp => println("TEST")
    case _ =>
  }
}

//  val cluster: Cluster = Cluster(context.system)
//  cluster.join(cluster.selfAddress)
////  val seedNodes: List[Address] =
////   List("akka:///system@127.0.0.1:2550", "akka:///system@127.0.0.1:2550").map(AddressFromURIString.parse)
////  cluster.joinSeedNodes(seedNodes)
//
//  override def preStart(): Unit = {
//      cluster.subscribe(self, classOf[MemberEvent], classOf[UnreachableMember])
//  }
//
//  override def postStop(): Unit = cluster.unsubscribe(self)
//
//  def receive: Receive = {
//    case "Hi" => boxActor ! "Hola"
//    case msg: Message =>
//    cluster.state.members.foreach(x => {
//      println(x)
//      val remoteNode = context.actorSelection(RootActorPath(x.address) / "user")
//      remoteNode ! Package(msg.payload)
//    })
//    case payload: Package => controllerImpl.chatArea.setText(payload.payload)
//    case _: MemberEvent =>
//  }
