package Fruitshop_Offline_GUI;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ConnectException;
import java.net.Socket;
import java.net.SocketException;

/**
 * Created by Ishtiaq Niloy on 26-Nov-16.
 */
public class senderThread extends Thread {

    ObjectInputStream inputStream;
    ObjectOutputStream outputStream;
    LogEntry temp;
    int salesmanIndex;

    senderThread(LogEntry temp, ObjectInputStream inputStream,  ObjectOutputStream outputStream, int salesmanIndex){
        this.temp = temp;
        this.inputStream = inputStream;
        this.outputStream = outputStream;
        this.salesmanIndex = salesmanIndex;

        start();

    }
    public void run(){
        try {
            outputStream.writeObject(temp.toString());
            inputStream.readObject();
        }catch (NullPointerException e){

            try {
               Socket socket=new Socket("LocalHost" , 3600);

                outputStream = new ObjectOutputStream(socket.getOutputStream());
                outputStream.flush();

                //System.out.println("Connected " + salesmanIndex);
                outputStream.writeObject( salesmanIndex );


                inputStream = new ObjectInputStream(socket.getInputStream());

                //SalesmanWindow_Controller.tempLabel.setText("Server Online");


            }catch (ConnectException f){
                //SalesmanWindow_Controller.tempLabel.setText("Server Offline");
            }catch (Exception f) {
                System.out.println(e);
            }
        }
        catch(SocketException e){
            System.out.println("Server Offline");
        }
        catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }


    }


}
