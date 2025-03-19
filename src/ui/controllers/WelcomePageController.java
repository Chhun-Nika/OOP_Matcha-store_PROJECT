

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import ui.manager.Route;

public class WelcomePageController {

    @FXML
    private Button adminBtn;

    @FXML
    private Label loginAsLabel;

    @FXML
    private Button userBtn;

    @FXML
    private Label welcomeLabel;

    public void adminBtnListener(ActionEvent event) throws IOException{
        Route.get("AdminPage", event);
    }

    public void userBtnListener(ActionEvent event) throws IOException{
        Route.get("UserPage", event);
    }
}
