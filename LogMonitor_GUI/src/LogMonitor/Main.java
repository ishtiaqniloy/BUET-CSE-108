package LogMonitor;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    final static int height = 600;
    final static int width = 600;

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("Home_Logmonitor.fxml"));
        primaryStage.setTitle("Log Monitor");
        primaryStage.setScene(new Scene(root, width, height));
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
