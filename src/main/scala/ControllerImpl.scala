import java.net.URL
import java.util.ResourceBundle
import akka.actor.{ActorRef, ActorSystem, Props, RootActorPath}
import javafx.event.ActionEvent
import javafx.fxml.FXML
import javafx.scene.control.TextArea

class ControllerImpl extends Controller {

  //ActorSystem
  val actorSystem: ActorSystem = ActorSystem.create("SystemOfActor")

  //Actors
  var firstActor: ActorRef = _
  var sendActor: ActorRef = _

  override def initialize(location: URL, resourceBundle: ResourceBundle): Unit = {
    chatArea.setEditable(false)
    nickName.setEditable(false)
    startCluster()
  }

  override def sendMessage(event: ActionEvent): Unit = {
    send(sendField.getText())
//    secondActor ! "hi"
    sendActor ! Message
    //println("Click")
  }

  override def sendNick(event: ActionEvent): Unit = {
    nickName.setText(nickField.getText())
  }

  def send(msg: String): Unit = {
    chatArea.setText(chatArea.getText() + "<" + nickName.getText() + "> say " + msg + "\n")
    sendActor ! sendField.getText()
//    sendField.clear()
  }

  def startCluster(): Unit = {
    firstActor = actorSystem.actorOf(Props(classOf[ClusterListener], this), name = "firstActor")
    sendActor = actorSystem.actorOf(Props(classOf[ClusterListener], this), name = "secondActor")
    firstActor ! println("//////////////[ Wake Up ]//////////////")
    sendActor ! println("//////////////[ Wake Down ]//////////////")
  }
}
