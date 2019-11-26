package Fruitshop_Offline_GUI;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.event.ActionEvent;
import javafx.stage.Stage;
import javafx.scene.layout.AnchorPane;
import javafx.scene.input.MouseEvent;

import java.io.IOException;

public class HomeController {

    @FXML
    private Button SalesmanButton;

    @FXML
    private Button AdminButton;

    @FXML
    private AnchorPane HomeScene;

    @FXML
    private Button ExitButton;

    @FXML
    void LaunchSalesman(ActionEvent event) {
        try {
            Stage stage = (Stage) SalesmanButton.getParent().getScene().getWindow();
            Parent root = FXMLLoader.load(getClass().getResource("Login_Salesman.fxml"));
            stage.setScene(new Scene(root, Main.width, Main.height));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println("LaunchSalesman");

    }

    @FXML
    void LaunchAdmin(ActionEvent event) {
        try {
            Stage stage = (Stage) AdminButton.getParent().getScene().getWindow();
            Parent root = FXMLLoader.load(getClass().getResource("Login_Admin.fxml"));
            stage.setScene(new Scene(root, Main.width, Main.height));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println("LaunchAdmin");

    }

    @FXML
    void exit(ActionEvent event) {
        Main.exit();

    }

}
