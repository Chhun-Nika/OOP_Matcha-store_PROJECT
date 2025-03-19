package ui.manager;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.io.IOException;


import javafx.event.ActionEvent;
import javafx.scene.Node;


public class Route {
    /**
     *
     * @param route is the name of the fxml file
     * @param event is the action event
     *
     */
    public static void get (String route, ActionEvent event) throws IOException  {
        FXMLLoader fxmlLoader = new FXMLLoader(Route.class.getResource("/ui/view/"+route+".fxml"));
        Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        Scene scene = new Scene(fxmlLoader.load());
        stage.setScene(scene);
        stage.show();
    }
}
