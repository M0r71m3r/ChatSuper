import akka.actor.{Actor, ActorLogging}
import akka.cluster.Cluster
import javafx.fxml.FXMLLoader
import javafx.scene.{Parent, Scene}
import javafx.stage.{Modality, Stage}

case class Message(content: String)
case class PrivateMessage(content: String, target: String)

class SendActor(controllerImpl: ControllerImpl) extends Actor with ActorLogging {

  val cluster: Cluster = Cluster(context.system)

  def receive: Receive = {
    case payload: Message =>
      if (payload.content == "help") {
        controllerImpl.chatArea.setText(controllerImpl.chatArea.getText() + "all/ - send message all" + "\n" + "nick/ - send message for nick" + "\n")
      }
      else {
        cluster.state.members.foreach(p => {
          val remoteNode = cluster.system.actorSelection(path = p.address + "/user/boxActor")
          val n: String = "<" + controllerImpl.nickName.getText() + "> say "
          remoteNode ! Message(n + payload.content)
        })
      }
    case payload: PrivateMessage =>
      cluster.state.members.foreach(p => {
        val remoteNode = cluster.system.actorSelection(path = p.address + "/user/boxActor")
        val n : String = "<" + controllerImpl.nickName.getText() + "> say "
        remoteNode ! PrivateMessage(n + payload.content, payload.target)
      })
  }
}

class BoxActor(controllerImpl: ControllerImpl) extends Actor with ActorLogging {

  def receive: Receive = {
    case payload: Message =>
      controllerImpl.chatArea.setText(controllerImpl.chatArea.getText() + payload.content + "\n")
    case payload: PrivateMessage =>
      var token = Array[String]()
      token = payload.target.split('/')
      if (token(0) == controllerImpl.nickName.getText()) {
        controllerImpl.chatArea.setText(controllerImpl.chatArea.getText() + payload.content + "\n")
      }
  }
}
