package Fruitshop_Offline_GUI;

import javafx.application.Application;
import javafx.beans.binding.ListExpression;
import javafx.collections.ObservableList.*;
import javafx.collections.transformation.FilteredList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.io.*;
import java.nio.file.Files;
import java.util.ArrayList;

public class Main extends Application {

    final static int height = 450;
    final static int width = 600;

    static double balance = (double) 100;
    final static  int capacity = 20;

    static ArrayList<LogEntry> log = new ArrayList <LogEntry>();
    static ShopItem[] inventory = new ShopItem[capacity];

    static String SalesmanName=null;
    static int SalesmanID = -1;

    static int [] numberOfItems = {0,0,0,0,0,0};

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("Home.fxml"));
        primaryStage.setTitle("Fruit Shop");
        primaryStage.setScene(new Scene(root, width, height));
        primaryStage.show();


    }

    public static void exit(){
        System.out.println("Exiting...");

            System.exit(0);

    }


    public static void main(String[] args) {
        launch(args);
    }
}
