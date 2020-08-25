import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

abstract class ControllerLogin implements Initializable {

    @FXML
    protected TextArea chatArea;

    @FXML
    protected TextField loginNickField;

    @FXML
    protected Button loginBtn;

    @FXML
    protected TextField loginPort;

    @FXML
    protected TextField loginHost;

    @FXML
    public void login(ActionEvent event) {
    }

    @Override
    public void initialize(URL location , ResourceBundle resourceBundle) {
    }
}