package Fruitshop_Offline_GUI;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.event.ActionEvent;
import javafx.stage.Stage;

import java.io.IOException;
import java.io.InterruptedIOException;

public class Login_Salesman_Controller {

    @FXML
    private Button submitButton;

    @FXML
    private TextField IDField;

    @FXML
    private AnchorPane LoginSalesmanScene;

    @FXML
    private Label loginSalesmanLabel;

    @FXML
    private Button backButton;

    @FXML
    private TextField nameField;

    @FXML
    void submit(ActionEvent event) {
        Main.SalesmanName =  nameField.getText();

        String ID = IDField.getText();

        if(Main.SalesmanName.length() == 0 || ID.length() == 0 ){
            loginSalesmanLabel.setText("Cannot continue without Name or ID");
            nameField.clear();
            IDField.clear();
        }

        else{
            Main.SalesmanID = Integer.parseInt(ID);

            try {
                Stage stage = (Stage) backButton.getParent().getScene().getWindow();
                Parent root = FXMLLoader.load(getClass().getResource("SalesmanWindow.fxml"));
                stage.setScene(new Scene(root, Main.width, Main.height));
                stage.show();
            } catch (IOException e) {
                e.printStackTrace();
            }

            System.out.println("SalesmanWindow");



        }


    }

    @FXML
    void back(ActionEvent event) {
        try {
            Stage stage = (Stage) backButton.getParent().getScene().getWindow();
            Parent root = FXMLLoader.load(getClass().getResource("Home.fxml"));
            stage.setScene(new Scene(root, Main.width, Main.height));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println("Home");
    }


}
