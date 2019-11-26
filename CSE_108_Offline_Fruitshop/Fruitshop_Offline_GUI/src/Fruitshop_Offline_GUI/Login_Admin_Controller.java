package Fruitshop_Offline_GUI;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.event.ActionEvent;
import javafx.stage.Stage;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;

public class Login_Admin_Controller {

    @FXML
    private PasswordField passField;

    @FXML
    private Label LoginPageLabel;

    @FXML
    private Button loginButton;

    @FXML
    private TextField userField;

    @FXML
    private Button BackButton;

    @FXML
    private AnchorPane LoginAdminScene;

    @FXML
    void LoginValidator(ActionEvent event) {
        String username = userField.getText();
        String password = passField.getText();



        if(username.contentEquals("Admin") && password.contentEquals("1234")){
            try {
                Stage stage = (Stage) loginButton.getParent().getScene().getWindow();
                Parent root = FXMLLoader.load(getClass().getResource("Admin.fxml"));
                stage.setScene(new Scene(root, Main.width, Main.height));
                stage.show();
            } catch (IOException e) {
                e.printStackTrace();
            }

            System.out.println("AdminChoices");
        }

        else{
            LoginPageLabel.setText("Wrong Username or Password. Try again...");
            userField.clear();
            passField.clear();
        }


    }

    @FXML
    void Back(ActionEvent event) {
        try {
            Stage stage = (Stage) loginButton.getParent().getScene().getWindow();
            Parent root = FXMLLoader.load(getClass().getResource("Home.fxml"));
            stage.setScene(new Scene(root, Main.width, Main.height));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println("Home");
    }



}
