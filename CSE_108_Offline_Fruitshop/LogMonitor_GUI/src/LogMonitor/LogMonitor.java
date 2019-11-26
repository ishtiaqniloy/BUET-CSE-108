package LogMonitor;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;
import java.util.ArrayList;


/**
 * Created by Ishtiaq Niloy on 18-Nov-16.
 */

public class LogMonitor extends Thread {
    private static int counter = 0;
    public static int port = 3600;

    static ServerSocket serverSocket = null;

    static ArrayList<LogEntry> serverLog = new ArrayList<LogEntry>();
    static  ArrayList <SalesMan> salesMen = new ArrayList<SalesMan>();

    LogMonitor(){

        start();
    }

    public void close(){
        System.out.println("Trying to close socket");
        try {
            serverSocket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }


    }

    public void run(){

        try {
            serverSocket = new ServerSocket(port);
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println("Log Monitor Server online");


            try {
                while (true) {
                    Integer index;
                    Socket clientSocket;
                    clientSocket = serverSocket.accept();

                    System.out.print("Connected Salesman " );
                    ObjectInputStream tempIn = new ObjectInputStream(clientSocket.getInputStream());
                    index = (Integer) tempIn.readObject();
                    System.out.println(index);

                    salesMen.add(new SalesMan(clientSocket, index, tempIn));

                    counter=salesMen.size();

                    salesMen.get(counter-1).outputStream.writeObject("Server: Hello Salesman " + index);

                    counter++;
                }

            }catch (SocketException e){
                System.out.println("Server Offline");
            }catch (IOException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }




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

               //LogEntry ob;
               //ob = (LogEntry) inputStream.readObject();
               //System.out.println(ob);
               //LogMonitor.serverLog.add(ob);
               Home_Logmonitor_Controller.logArea.appendText(String.valueOf(inputStream.readObject()));

               outputStream.writeObject(true);

           }
        } catch (SocketException e){
                //e.printStackTrace();
                System.out.println("Salesman Disconnected " + this.index);  //detects if salesmanThread is finished and so breaks the while loop
                for(int i = 0; i< LogMonitor.salesMen.size(); i++){
                    if(LogMonitor.salesMen.get(i) == this){
                        LogMonitor.salesMen.remove(i);
                    }
                }
                if(LogMonitor.salesMen.size() == 0){
                    Home_Logmonitor_Controller.logArea.appendText("No Salesman is online at this moment\nWaiting for new connections...\n");
                }

        } catch (IOException e) {
                e.printStackTrace();
        } catch (ClassNotFoundException e) {
                e.printStackTrace();
        }


    }

}
