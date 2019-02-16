package LogMonitor;

import com.sun.org.apache.xpath.internal.operations.Bool;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class Home_Logmonitor_Controller implements Initializable {

    private static boolean status_on=false;

    static LogMonitor logMonitor;

    @FXML
    private Button exitButton;

    public static TextArea logArea;

    @FXML
    public TextArea logTextArea;

    @FXML
    private Button statusChangeButton;

    @FXML
    private Label statusLabel;

    @FXML
    void changeStatus(ActionEvent event) {
        if(status_on){      //currently server on, turning it off

            logMonitor.close();

            statusLabel.setText("Server Is Offline");
            statusChangeButton.setText("Turn Server On");
            status_on = false;

            System.out.println("Log Monitor Server offline");


        }

        else{           //currently server off, turning it on

            logMonitor = new LogMonitor();
            statusLabel.setText("Server Is Online");
            statusChangeButton.setText("Turn Server Off");
            status_on = true;


        }

    }

    @FXML
    void exit(ActionEvent event) {

        if(status_on){      //currently server on, turning it off

           logMonitor.close();

            statusLabel.setText("Server Is Offline");
            statusChangeButton.setText("Turn Server On");
            status_on = false;

            System.out.println("Log Monitor Server offline");


        }

        System.out.println("Exiting...");
        System.exit(0);

    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        status_on = false;
        logArea = logTextArea;

        logTextArea.setText("Time ---------------------------------- name ---- amount ---- Action ---- Salesman ID\n");

    }
}


