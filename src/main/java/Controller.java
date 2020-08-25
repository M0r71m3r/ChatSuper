import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import java.net.URL;
import java.util.ResourceBundle;

abstract class Controller implements Initializable {

    @FXML
    protected TextArea chatArea;

    @FXML
    protected TextField sendField;

    @FXML
    protected Button sendBtn;

    @FXML
    protected TextArea nickName;

    @FXML
    protected TextField nickField;

    @FXML
    abstract void sendMessage(ActionEvent event);

    @Override
    public void initialize(URL location , ResourceBundle resourceBundle) {
    }
}