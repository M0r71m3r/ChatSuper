import javafx.application.Application
import javafx.fxml.FXMLLoader
import javafx.scene.{Parent, Scene}
import javafx.stage.Stage

class Launcher() extends Application {

  def start(stage: Stage): Unit = {
      stage.setTitle("Login")
      val parent: Parent = FXMLLoader.load(getClass.getResource("Login.fxml"))
      val scene = new Scene(parent)
      stage.setScene(scene)
      stage.show()
  }
}
