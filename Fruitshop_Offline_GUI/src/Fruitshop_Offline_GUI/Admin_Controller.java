package Fruitshop_Offline_GUI;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;


public class Admin_Controller {

    @FXML
    private Label currentBalanceLabel;

    @FXML
    private TextField setBalanceTextField;

    @FXML
    private Button setBalanceButton;

    @FXML
    private Button homeButton;

    @FXML
    private TextArea inventoryText;


    @FXML
    private TextArea logText;

    @FXML
    void setBalance(ActionEvent event) {
        String str = setBalanceTextField.getText();
        Main.balance = Float.parseFloat(str);

        currentBalanceLabel.setText(String.valueOf(( Main.balance)));
        setBalanceTextField.clear();

    }

    @FXML
    void home(ActionEvent event) {
        try {
            Stage stage = (Stage) homeButton.getParent().getScene().getWindow();
            Parent root = FXMLLoader.load(getClass().getResource("Home.fxml"));
            stage.setScene(new Scene(root, Main.width, Main.height));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println("Home");

    }



    public void initialize() {
        currentBalanceLabel.setText(String.valueOf(Main.balance));

        logText.setText("Time ---------------------------------- name ---- amount ---- Action ---- Salesman ID\n");
        for (int i=0; i<Main.log.size(); i++){
            logText.appendText(Main.log.get(i).toString());
        }

        inventoryText.setText("Name" + "------" + "Buying Price" + "--" + "Selling Price\n");
        for (int i =0; i< Main.numberOfItems[0]; i++) {
            inventoryText.appendText(Main.inventory[i].toString());

        }



    }
}
