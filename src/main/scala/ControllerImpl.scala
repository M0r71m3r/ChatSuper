import java.net.URL
import java.util.ResourceBundle
import akka.actor.{ActorRef, ActorSystem, Props}
import com.typesafe.config.ConfigFactory
import javafx.event.ActionEvent

class ControllerImpl extends Controller {

  override def initialize(location: URL, resourceBundle: ResourceBundle): Unit = {
    chatArea.setEditable(false) //No change, just copy
    nickName.setEditable(false) //No change, just copy
  }

  val tcpPort: String = "2550"

  val conf: String = "akka.remote.netty.tcp.port = " + tcpPort + "," +
    "akka.remote.netty.tcp.hostname = 127.0.0.1," +
    "akka.cluster.seed-nodes = [\"akka.tcp://system@127.0.0.1:2550\", \"akka.tcp://system@127.0.0.1:2551\"]," +
    "akka.actor.provider = \"cluster\""
  //ActorSystem
  val system: ActorSystem = ActorSystem("system", ConfigFactory.parseString(conf))
  //Actors
  val sendActor: ActorRef = system.actorOf(Props(classOf[SendActor], this), name = "sendActor")
  val boxActor: ActorRef = system.actorOf(Props(classOf[BoxActor], this), name = "boxActor")

  override def sendMessage(event: ActionEvent): Unit = {
    if (sendField.getText().length() != 0) {
      var token = Array[String]()
      token = sendField.getText.split('/')
      if (token(0) == "all" || sendField.getText() == "help") {
        sendActor ! Message(content = sendField.getText())
        }
      else {
        chatArea.setText(chatArea.getText() + "<" + nickName.getText() + "> say " + sendField.getText() + "\n")
        sendActor ! PrivateMessage(target = sendField.getText(), content = sendField.getText())
      }
       sendField.clear()
    }
  }

  override def sendNick(event: ActionEvent): Unit = {
    if (nickField.getText().length() != 0 ) {
      nickName.setText(nickField.getText())
      nickField.clear()
    }
  }
}