import java.net.{InetAddress, URL}
import java.util.ResourceBundle

import javafx.event.ActionEvent
import javafx.fxml.FXMLLoader
import javafx.scene.{Parent, Scene}
import javafx.stage.{Modality, Stage}

class ControllerImplLogin() extends ControllerLogin {

  override def initialize(location: URL, resourceBundle: ResourceBundle): Unit = {
    chatArea.setEditable(false) //No change, just copy
  }

  override def login(actionEvent: ActionEvent): Unit = {
    val stage = loginBtn.getScene.getWindow.asInstanceOf[Stage]

    if (loginNickField.getText().length() != 0 && loginPort.getText().length() != 0) {

      stage.close()

      val fxmlLoader = new FXMLLoader(getClass.getResource("Controller.fxml"))
      val parent = fxmlLoader.load.asInstanceOf[Parent]
      val primaryStage = new Stage()
      val controller = fxmlLoader.getController[ControllerImpl]

      controller.nickName.setText(loginNickField.getText)
      controller.init(if (loginHost.getText.isEmpty) InetAddress.getLocalHost.getHostAddress else loginHost.getText, loginPort.getText)

      primaryStage.initModality(Modality.WINDOW_MODAL)
      primaryStage.setTitle("Chat")
      primaryStage.setScene(new Scene(parent))
      primaryStage.show()
    }
  }
}

