package FruitShop;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

/**
 * Created by Ishtiaq Niloy on 27-Nov-16.
 */
public class Sender extends Thread {
    static int  acknowledge = -1;
    static ObjectInputStream inputStream;
    static ObjectOutputStream outputStream;
    static ArrayList<LogEntry> tempLog;

    Sender(ObjectInputStream inputStream, ObjectOutputStream outputStream, ArrayList<LogEntry> t){
        this.inputStream = inputStream;
        this.outputStream = outputStream;
        this.tempLog = t;

        start();

    }

    synchronized public void run(){

        SalesmanThread.counter = 0;
        int acknowlegde=-1;
        boolean done = false;
        boolean[] ack = {false, false, false, false};

        int size = tempLog.size();

        while(!done){
            for(int i=0; i<size && i<4; i++){
                System.out.println("Sending data : " + tempLog.get(0).toString() );

                try {
                    outputStream.writeObject(tempLog.get(0));
                    SalesmanThread.logOf4.remove(0);
                } catch (IOException e) {
                    e.printStackTrace();
                }


            }

            done = true;
            //SalesmanThread.salesmanThread[0].notifyAll();

        }


    }



}
