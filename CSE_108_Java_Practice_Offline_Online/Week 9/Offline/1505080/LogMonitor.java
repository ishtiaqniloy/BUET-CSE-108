package LogMonitor;

import FruitShop.LogEntry;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.ServerSocket;
import java.net.SocketException;
import java.util.ArrayList;
import java.util.Random;


/**
 * Created by Ishtiaq Niloy on 18-Nov-16.
 */

public class LogMonitor {
    private static int counter = 0;
    public static int port = 3600;

    static ArrayList<LogEntry> serverLog = new ArrayList<LogEntry>();
    static  ArrayList <SalesMan> salesMen = new ArrayList<SalesMan>();

    public static void main(String[] args) {

        ServerSocket serverSocket = null;

        try {
            serverSocket = new ServerSocket(port);
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println("Log Monitor Server online");

        while (true) {
            Integer index;
            Socket clientSocket;
            try {

                clientSocket = serverSocket.accept();

                System.out.print("Connected Salesman " );
                ObjectInputStream tempIn = new ObjectInputStream(clientSocket.getInputStream());
                index = (Integer) tempIn.readObject();
                System.out.println(index);

                salesMen.add(new SalesMan(clientSocket, index, tempIn));

                counter=salesMen.size();

                salesMen.get(counter-1).outputStream.writeObject("Server: Hello Salesman " + index);

                counter++;

            } catch (IOException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }

        }

//        try {
//            for(int i=0; i<salesMen.size(); i++)
//                salesMen.get(i).join();
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
//
//        System.out.println("Generating logs from LogMonitor...");
//        System.out.println("Time Stamp" + "\t\t\t\t\t  " + "Name" + "\t" + "Amount" + "\t" + "BoughtOrSold" + "\t" + "Salesman Index");
//
//        for (int i=0; i<LogMonitor.serverLog.size(); i++) {
//            System.out.println(serverLog.get(i).toString());
//        }
//
//        System.out.println("Log Monitor Exiting");


    }

}

class SalesMan extends Thread {
    private Socket socket;
    ObjectOutputStream outputStream;
    ObjectInputStream inputStream;
    private int index;

    SalesMan(Socket socket, int index, ObjectInputStream inputStream){

        try {
            this.socket = socket;
            outputStream = new ObjectOutputStream(socket.getOutputStream());

            this.inputStream = inputStream;

            this.index = index;
        } catch (Exception e) {
            System.out.println(e);
        }



        start();

    }

    @Override
     public void run() {

        try {
           while(true){

               LogEntry ob;

               ob = (LogEntry) inputStream.readObject();

               Random rn = new Random();
               Boolean reply = rn.nextBoolean();

               if (reply){
                   System.out.println(ob);
                   LogMonitor.serverLog.add(ob);

               }

               outputStream.writeObject(reply);

           }
        } catch (SocketException e){
                //e.printStackTrace();
                System.out.println("Salesman Disconnected " + this.index);  //detects if salesmanThread is finished and so breaks the while loop
                for(int i=0; i<LogMonitor.salesMen.size(); i++){
                    if(LogMonitor.salesMen.get(i) == this){
                        LogMonitor.salesMen.remove(i);
                    }
                }
                if(LogMonitor.salesMen.size() == 0){
                    System.out.println("No Salesman is online at this moment\nWaiting for new connections...");
                }

        } catch (IOException e) {
                e.printStackTrace();
        } catch (ClassNotFoundException e) {
                e.printStackTrace();
        }


    }

}
