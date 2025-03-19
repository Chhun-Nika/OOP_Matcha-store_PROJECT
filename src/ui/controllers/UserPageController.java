import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import ui.manager.Route;

public class UserPageController {

    @FXML
    private Label loginUserLabel;

    @FXML
    private Label userEmailLabel;

    @FXML
    private TextField userEmailTextInput;

    @FXML
    private Button userLoginBtn;

    @FXML
    private Label userPasswordLabel;

    @FXML
    private TextField userPwdInputText;

    @FXML
    private Button userRegisterBtn;

    @FXML
    private Button backUserBtn;

    public void backBtnListener(ActionEvent event) throws IOException {
        Route.get("WelcomePage", event);
    }

    public void registerBtnListener(ActionEvent event) throws IOException {
        Route.get("RegisterPage", event);
    }

}
