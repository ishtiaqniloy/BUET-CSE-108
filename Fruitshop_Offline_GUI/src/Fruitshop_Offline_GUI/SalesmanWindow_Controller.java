package Fruitshop_Offline_GUI;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ConnectException;
import java.net.Socket;
import java.net.URL;
import java.util.Random;
import java.util.ResourceBundle;

import static Fruitshop_Offline_GUI.Main.numberOfItems;
import static java.lang.Thread.sleep;


public class SalesmanWindow_Controller implements Initializable{

    private int salesmanIndex;
    private String fileName;
    private int sleepTime=0;
    private String address = "127.0.0.1";
    private int port = 3600;
    private Socket socket;
    private static ObjectOutputStream outputStream;
    private static ObjectInputStream inputStream;

    public static Label tempLabel;

    @FXML
    private Button sellButton;

    @FXML
    private Button balanceButton;

    @FXML
    private Label outputLabel;

    @FXML
    private Label typeLabel;

    @FXML
    private Button buyButton;

    @FXML
    private Button homeButton;

    @FXML
    private TextField typeField;

    @FXML
    private TextField amountField;

    @FXML
    private Label welcomeLabel;

    @FXML
    void buy(ActionEvent event) {

        String t, a;

        t = typeField.getText();
        a = amountField.getText();

        typeField.clear();
        amountField.clear();

        if(t.length() == 0 || a.length() == 0){
            typeField.clear();
            amountField.clear();

        }

        else{
            int type = Integer.parseInt(t);
            int amount = Integer.parseInt(a);

            LogEntry tempLog = new LogEntry(type,  false, amount, Main.SalesmanID);
            ShopItem temp = new ShopItem();
            boolean typeMiss = false;

            switch (type){
                case 1 : temp = new Apple(false);
                    break;
                case 2 : temp = new Apple(true);
                    break;
                case 3 : temp = new Orange();
                    break;
                case 4 : temp = new Strawberry(true);
                    break;
                case 5 : temp = new Strawberry(false);
                    break;
                default: typeMiss = true;

            }

            if(typeMiss){
                outputLabel.setText("Wrong type");
            }

            else if(numberOfItems[0] + amount > Main.capacity){
                outputLabel.setText("Not enough space in inventory");
            }

            else if(Main.balance < temp.getBuyingPricePerUnit()*amount){
                outputLabel.setText("Not enough balance");
            }

            else{
                Main.log.add(tempLog);

                for (int i = 0; i < amount; i++) {
                    Main.inventory[ Main.numberOfItems [0] ] = temp;
                    numberOfItems [0]++;
                }
                numberOfItems[type]+=amount;
                Main.balance-=amount*temp.getBuyingPricePerUnit();

                outputLabel.setText("Successfully bought " + amount + " " + temp.getName() );

                senderThread thread = new senderThread(tempLog, inputStream, outputStream, salesmanIndex);

            }


        }



    }


    @FXML
    void sell(ActionEvent event) {
        String t, a;

        t = typeField.getText();
        a = amountField.getText();

        typeField.clear();
        amountField.clear();

        if(t.length() == 0 || a.length() == 0){
            typeField.clear();
            amountField.clear();

        }

        else {
            int type = Integer.parseInt(t);
            int amount = Integer.parseInt(a);

            LogEntry tempLog = new LogEntry(type,  true, amount, Main.SalesmanID);
            ShopItem temp = new ShopItem();

            outputLabel.setText("Wait for a moment please...");

//            try {
//                Thread.sleep(sleepTime);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }

            boolean typeMiss = false;

            switch (type){
                case 1 : temp = new Apple(false);
                    break;
                case 2 : temp = new Apple(true);
                    break;
                case 3 : temp = new Orange();
                    break;
                case 4 : temp = new Strawberry(true);
                    break;
                case 5 : temp = new Strawberry(false);
                    break;
                default: typeMiss = true;

            }

            if(typeMiss){
                outputLabel.setText("Wrong type");
            }

            else if( numberOfItems[type] < amount ){
                outputLabel.setText("Not Enough Amount");
            }

            else {
                ShopItem x;
                int j=0;
                for(int i=0; i< amount; i++){
                    while(Main.inventory[j].type!=type){
                        j++;
                    }

                    for(int k=j; k<numberOfItems[0]-1; k++){
                        Main.inventory[k]=Main.inventory[k+1];
                    }
                    numberOfItems[0]--;

                }
                numberOfItems[type]-=amount;
                Main.balance+=amount*temp.getSellingPricePerUnit();
                Main.log.add(tempLog);

                outputLabel.setText("Successfully sold " + amount + " " + temp.getName() );

                senderThread thread = new senderThread(tempLog, inputStream, outputStream, salesmanIndex);

            }


        }

    }


    @FXML
    void getBalance(ActionEvent event) {

        outputLabel.setText("Balance = " + Main.balance);

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

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        typeLabel.setText("Type 1 : Green Apple\nType 2 : Red Apple\nType 3 : Orange\nType 4 : Canned Strawberries\nType 5 : Packed Strawberries\n");
        welcomeLabel.setText("Welcome Salesman " + Main.SalesmanID + " : " + Main.SalesmanName);

        tempLabel = outputLabel;

        this.salesmanIndex=Main.SalesmanID;
        try {
            socket=new Socket(address , port);

            outputStream = new ObjectOutputStream(socket.getOutputStream());
            outputStream.flush();

            //System.out.println("Connected " + salesmanIndex);
            outputStream.writeObject( salesmanIndex );


            inputStream = new ObjectInputStream(socket.getInputStream());


        }catch (ConnectException e){
            outputLabel.setText("Server Offline");
        }catch (Exception e) {
            System.out.println(e);
        }

        Random rn = new Random();
        sleepTime = rn.nextInt(11) * 1000;

        try {
            Object ob = null;
            ob = inputStream.readObject();

            System.out.println(ob);
        }catch (NullPointerException e){
            outputLabel.setText("Server Offline");
        }
        catch (ConnectException e){
            outputLabel.setText("Server Offline");
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }


    }


}
