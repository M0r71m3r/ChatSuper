import java.net.URL
import java.util.ResourceBundle
import akka.actor.{ActorRef, ActorSystem, Props, RootActorPath, Actor, ActorLogging}
import com.typesafe.config.ConfigFactory
import javafx.event.ActionEvent

class ControllerImpl extends Controller {

  override def initialize(location: URL, resourceBundle: ResourceBundle): Unit = {
    chatArea.setEditable(false)
    nickName.setEditable(false)
    startCluster()
  }

  val conf: String = "akka.remote.netty.tcp.port = 2551," +
    "akka.remote.netty.tcp.hostname = 127.0.0.1," +
    "akka.cluster.seed-nodes = [\"akka.tcp://system@127.0.0.1:2550\", \"akka.tcp://system@127.0.0.1:2551\"]," +
    "akka.actor.provider = \"cluster\""
  val system = ActorSystem("system", ConfigFactory.parseString(conf))

  val sendActor = system.actorOf(Props[SendActor], "sendActor")
  val boxActor = system.actorOf(Props[ActorBox], "boxActor")

  val ss = "akka://system@127.0.0.1:2551/user/sendActor"


  override def sendMessage(event: ActionEvent): Unit = {
    send(sendField.getText())
    sendActor ! sendField.getText()
    sendField.clear()
  }

  override def sendNick(event: ActionEvent): Unit = {
    nickName.setText(nickField.getText())
  }

  def send(msg: String): Unit = {
    chatArea.setText(chatArea.getText() + "<" + nickName.getText() + "> say " + msg + "\n")
  }

  def startCluster(): Unit = {
  }
}
