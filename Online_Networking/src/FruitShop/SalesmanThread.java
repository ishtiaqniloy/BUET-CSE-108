package FruitShop;

/**
 * Created by Ishtiaq Niloy on 12-Nov-16.
 */

import LogMonitor.LogMonitor;

import java.io.*;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class SalesmanThread extends Thread{

    FruitShop fruitShop;
    private int salesmanIndex;
    private String fileName;
    private int sleepTime=0;
    private String address = "127.0.0.1";
    private Socket socket;
    private ObjectOutputStream outputStream;
    private ObjectInputStream inputStream;

    static SalesmanThread[] salesmanThread;

    static ArrayList <LogEntry> logOf4 = new ArrayList<LogEntry>();
    static int counter = 0;

    public SalesmanThread(FruitShop fs, int salesmanIndex) {
        //Insert your code here

        fruitShop=fs;
        this.salesmanIndex=salesmanIndex;
        try {
            socket=new Socket(address , LogMonitor.port);

            outputStream = new ObjectOutputStream(socket.getOutputStream());
            outputStream.flush();

            //System.out.println("Connected " + salesmanIndex);
            outputStream.writeObject( salesmanIndex );


            inputStream = new ObjectInputStream(socket.getInputStream());


        } catch (Exception e) {
            System.out.println(e);
        }

        if(salesmanIndex==1){
            fileName="C:\\Users\\Ishti\\Google Drive\\Projects\\IntelliJ_Projects\\Online_Networking\\src\\FruitShop\\input1.txt";
        }

        else if(salesmanIndex==2){
            fileName="C:\\Users\\Ishti\\Google Drive\\Projects\\IntelliJ_Projects\\Offline_Inheritance_Interface\\src\\FruitShop\\input2.txt";
        }

        else if(salesmanIndex==3){
            fileName="C:\\Users\\Ishti\\Google Drive\\Projects\\IntelliJ_Projects\\Offline_Inheritance_Interface\\src\\FruitShop\\input3.txt";
        }

        Random rn = new Random();
        sleepTime = rn.nextInt(11) * 1000;

        try {
            Object ob = null;
            ob = inputStream.readObject();

            System.out.println(ob);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }


    }

    @Override
    public void run() {

        Scanner sc = null;

        try {
            sc = new Scanner(new File(fileName));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        int n = sc.nextInt();

        int choice, type, amount;
        for (int i = 0; i < n; i++) {


            choice = sc.nextInt();

            if (choice == 1) {
                type=sc.nextInt();
                amount=sc.nextInt();
                fruitShop.buy(type, amount, salesmanIndex, outputStream, inputStream);
            }
            else if (choice == 2) {
                type=sc.nextInt();
                amount=sc.nextInt();

                try {
                    sleep(sleepTime);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                fruitShop.sell(type, amount, salesmanIndex, outputStream, inputStream);
            }
            else if (choice == 3) {
                System.out.println(fruitShop.getBalance());
            }

        }

        Sender s = new Sender(inputStream, outputStream, logOf4);

        try {
            s.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
//        try {
//            System.out.println("Waiting");
//            wait();
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
//
//        System.out.println("Notified");

        fruitShop.lastManIndex = salesmanIndex;

    }


    public static void main(String[] args) {
        FruitShop fs=new FruitShop(20, 70);



        salesmanThread=new SalesmanThread[1];

        for (int i = 0; i < salesmanThread.length; i++) {
            salesmanThread[i]= new SalesmanThread(fs,i+1);
            salesmanThread[i].start();
        }


        try {
            for (int i = 0; i < salesmanThread.length; i++) {
                salesmanThread[i].join();
            }
        } catch (InterruptedException e) {
            System.out.println("Interrupted while waiting for other threads to join");
        }

        System.out.println("Final Balance = " + fs.getBalance());

        System.out.println("");
        System.out.println("Generated Logs...");
        System.out.println("Time Stamp" + "\t\t\t\t\t  " + "Name" + "\t" + "Amount" + "\t" + "BoughtOrSold" + "\t" + "Salesman Index");
        for (LogEntry logEntry : fs.getLog()) {
            System.out.println(logEntry.toString());
        }

        System.out.println("");
        System.out.println("Items in inventory...");
        System.out.println("Name" + " " + "Buying Price" + " " + "Selling Price");
        for (ShopItem shopItem : fs.getInventory()) {
            System.out.println(shopItem.toString());
        }

        System.out.println("Salesman " + fs.lastManIndex +" closed the shop.");



    }


}
