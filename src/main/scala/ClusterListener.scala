import akka.cluster.Cluster
import akka.cluster.ClusterEvent._
import akka.actor.{Actor, ActorLogging, ActorRef, ActorSystem, Address, AddressFromURIString, RootActorPath}
import akka.actor.Props
import akka.cluster.ClusterEvent.ClusterDomainEvent
import akka.cluster.client.ClusterClientUp
import akka.cluster.typed.JoinSeedNodes
import akka.protobufv3.internal.compiler.PluginProtos.CodeGeneratorResponse.File

class ClusterListener(controllerImpl: ControllerImpl) extends Actor with ActorLogging {

  val cluster: Cluster = Cluster(context.system)
  cluster.join(cluster.selfAddress)

//  val seedNodes: List[Address] =
//   List("akka://SystemOfActor@192.168.56.1:25520", "akka://SystemOfActor@192.168.56.1:25521").map(AddressFromURIString.parse)
//  cluster.joinSeedNodes(seedNodes)

  override def preStart(): Unit = {
//    println("[ Check ]")
      cluster.subscribe(self, classOf[MemberEvent], classOf[UnreachableMember])
//    println("[ Check_2 ]")
  }

  override def postStop(): Unit = cluster.unsubscribe(self)

  def receive: Receive = {
//    case MemberUp(member) =>
//    log.info("Member is Up: {}", member.address)
    case Message => {
      println(cluster.selfAddress + "\n")
      println(controllerImpl.sendActor)
      println(controllerImpl.sendField.getText())
      controllerImpl.sendField.clear()
    }
//    case "hi" => println("hola")
//    case msg: Message =>
//    cluster.state.members.foreach(x => {
//      println(x)
//      val remoteNode = context.actorSelection(RootActorPath(x.address) / "user")
//      remoteNode ! Package(msg.payload)
//    })
//    case payload: Package => controllerImpl.chatArea.setText(payload.payload)
    case _: MemberEvent =>
  }
}
//controllerImpl.secondActor ! println("//////////////[ Rise Up ]//////////////")
//  object SimpleClusterApp {
//    def main(args: Array[String]): Unit = {
//
//      // Override the configuration of the port
//      // when specified as program argument
//      if (args.nonEmpty) System.setProperty("akka.remote.netty.tcp.port", args(0))
//
//      // Create an Akka system
//      val system = ActorSystem("ClusterSystem")
//      val clusterListener = system.actorOf(Props[SimpleClusterListener],
//        name = "clusterListener")
//
//      Cluster(system).subscribe(clusterListener, classOf[ClusterDomainEvent])
//    }
//  }
//
//  class SimpleClusterListener(controllerImpl: ControllerImpl) extends Actor with ActorLogging {
//    def receive = {
//      case state: CurrentClusterState ⇒
//        log.info("Current members: {}", state.members.mkString(", "))
//      case MemberUp(member) ⇒
//        log.info("Member is Up: {}", member.address)
//      case UnreachableMember(member) ⇒
//        log.info("Member detected as unreachable: {}", member)
//      case MemberRemoved(member, previousStatus) ⇒
//        log.info("Member is Removed: {} after {}",
//          member.address, previousStatus)
//      case _: ClusterDomainEvent ⇒ // ignore
//    }
//  }
