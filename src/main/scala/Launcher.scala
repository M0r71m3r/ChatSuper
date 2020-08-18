import javafx.application.Application
import javafx.fxml.FXMLLoader
import javafx.scene.{Parent, Scene}
import javafx.stage.Stage

class Launcher() extends Application {

  def start(primaryStage: Stage): Unit = {
    primaryStage.setTitle("Chat")
    val parent: Parent = FXMLLoader.load(getClass.getResource("Controller.fxml"))
    val scene = new Scene(parent)
    primaryStage.setScene(scene)
    primaryStage.show()
  }
}
