import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import ui.manager.Route;

public class RegisterPageController {

    @FXML
    private Label SignUpLabel;

    @FXML
    private Button backUserBtn;

    @FXML
    private Label confirmPwdLabel;

    @FXML
    private PasswordField confirmPwdTextField;

    @FXML
    private Button createAccBtn;

    @FXML
    private DatePicker dobInput;

    @FXML
    private Label dobLabel;

    @FXML
    private Label emailLabel;

    @FXML
    private TextField emailTextField;

    @FXML
    private Label nameLabel;

    @FXML
    private TextField nameTextField;

    @FXML
    private Label phoneNumLabel;

    @FXML
    private TextField phoneNumTextField;

    @FXML
    private Label pwdLabel;

    @FXML
    private PasswordField pwdTextField;

    @FXML
    void backBtnListener(ActionEvent event) throws IOException {
        Route.get("UserPage", event);
    }

}
