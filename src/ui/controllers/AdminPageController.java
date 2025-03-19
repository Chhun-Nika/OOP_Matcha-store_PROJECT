

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import ui.manager.Route;

public class AdminPageController {

    @FXML
    private Label adminEmailLabel;

    @FXML
    private TextField adminEmailTextInput;

    @FXML
    private Button adminLoginBtn;

    @FXML
    private Label adminPasswordLabel;

    @FXML
    private TextField adminPwdInputText;

    @FXML
    private Label loginAdminLabel;

    @FXML
    private Button backBtn;

    public void backBtnListener(ActionEvent event) throws IOException {
        Route.get("WelcomePage", event);
    }

}
