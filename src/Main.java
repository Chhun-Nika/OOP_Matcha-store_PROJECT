
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;


public class Main extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/ui/view/ProductList.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 1200, 800);
        primaryStage.setTitle("Matcha Store");
        primaryStage.setScene(scene);
        primaryStage.show();
    }
     public static void main(String[] args) {
        launch(args);
    }
}
