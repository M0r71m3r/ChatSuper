import java.net.InetAddress

import akka.actor.{ActorRef, ActorSystem, Props}
import com.typesafe.config.ConfigFactory
import javafx.application.Platform
import javafx.event.ActionEvent

class ControllerImpl() extends Controller {

  var system: ActorSystem = _
  var sendActor: ActorRef = _
  var boxActor: ActorRef = _

  override def sendMessage(event: ActionEvent): Unit = {
    if (sendField.getText().length() != 0) {
      var token = Array[String]()
      token = sendField.getText.split('/')
      if (token(0) == "all" || sendField.getText() == "help") {
        sendActor ! Message(content = token(1))
        }
      else {
        chatArea.setText(chatArea.getText() + "<" + nickName.getText() + "> say " + sendField.getText() + "\n")
        sendActor ! PrivateMessage(target = sendField.getText(), content = sendField.getText())
      }
       sendField.clear()
    }
  }

  def init(tcpHost: String, tcpPort: String): Unit = {
    chatArea.setEditable(false) //No change, just copy
    nickName.setEditable(false) //No change, just copy


    if (!tcpPort.equals("")) {
      val conf: String = "akka.remote.netty.tcp.port = " + tcpPort + "," +
        "akka.remote.netty.tcp.hostname = " + InetAddress.getLocalHost.getHostAddress + "," +
        "akka.cluster.seed-nodes = [\"akka.tcp://system@" + tcpHost + ":" + "2550" + "\"]," +
        "akka.actor.provider = \"cluster\""
      system = ActorSystem("system", ConfigFactory.parseString(conf))

      sendActor = system.actorOf(Props(classOf[SendActor], this), name = "sendActor")
      boxActor = system.actorOf(Props(classOf[BoxActor], this), name = "boxActor")
    } else {
      Platform.exit()
    }
  }
}