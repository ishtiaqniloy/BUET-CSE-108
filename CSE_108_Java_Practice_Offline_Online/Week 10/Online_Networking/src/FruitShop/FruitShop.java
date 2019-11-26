package FruitShop;

/**
 * Created by Ishtiaq Niloy on 27-Oct-16.
 */

import com.sun.org.apache.xpath.internal.SourceTree;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;

import static FruitShop.SalesmanThread.counter;


interface Shop{
    void buy(int type, int amount, int salesmanIndex, ObjectOutputStream oos, ObjectInputStream ois);
    void sell(int type, int amount, int salesmanIndex, ObjectOutputStream oos, ObjectInputStream ois);
    LogEntry[] getLog();
    ShopItem[] getInventory();
    double getBalance();
}


class FruitShop implements Shop {
    private List<LogEntry> log = new ArrayList<LogEntry>();

    private int[] numberOfItems = new int[6];
    private ShopItem[] inventory;


    private double balance=0;
    private int capacity=0;

    private ShopItem temp;

    public int lastManIndex = 0;

    FruitShop(int cap, double initialBalance){
        balance=initialBalance;
        capacity=cap;

        for(int i=0; i<6 ; i++){
            numberOfItems[i] = 0;
        }
        inventory = new ShopItem[capacity];

    }


    synchronized public void buy(int type, int amount, int salesmanIndex, ObjectOutputStream oos, ObjectInputStream ois) {
        LogEntry tempLog = new LogEntry(type,  false, amount, salesmanIndex);
        Boolean logEntrySent=false;

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
        }

        if(numberOfItems[0] + amount > capacity){
            System.out.println("Not enough space in inventory");
        }

        else if(balance<temp.getBuyingPricePerUnit()*amount){
                System.out.println("Not enough balance");
        }

        else{
            log.add(tempLog);



            for (int i = 0; i < amount; i++) {
                inventory[ numberOfItems [0] ] = temp;
                numberOfItems [0]++;
            }
            numberOfItems[type]+=amount;
            balance-=amount*temp.getBuyingPricePerUnit();


            System.out.println("Bought");
            SalesmanThread.logOf4.add(tempLog);
            SalesmanThread.counter++;


            if(SalesmanThread.counter == 4){
                Sender s = new Sender(ois, oos, SalesmanThread.logOf4);
//                try {
//                    System.out.println("Waiting");
//                    wait();
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }
//
//                System.out.println("Notified");
            }


        }

//        while(!logEntrySent) {
//
//
//
//            try {
//                oos.writeObject(tempLog);
//
//                logEntrySent = (Boolean) ois.readObject();
//                if(!logEntrySent)
//                    System.out.println("Sending data failure. Trying to send again...");
//
//            } catch (IOException e) {
//                e.printStackTrace();
//            } catch (ClassNotFoundException e) {
//                e.printStackTrace();
//            }
//
//
//        }

    }

    synchronized public void sell(int type, int amount, int salesmanIndex, ObjectOutputStream oos , ObjectInputStream ois) {
        LogEntry tempLog = new LogEntry(type,  true, amount, salesmanIndex);
        Boolean logEntrySent=false;

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
        }

        if( numberOfItems[type] < amount ){
            System.out.println("Not Enough Amount");
        }

        else {
            ShopItem x;
            int j=0;
            for(int i=0; i< amount; i++){
                while(inventory[j].type!=type){
                    j++;
                }

                for(int k=j; k<numberOfItems[0]-1; k++){
                    inventory[k]=inventory[k+1];
                }
                numberOfItems[0]--;

            }
            numberOfItems[type]-=amount;
            balance+=amount*temp.getSellingPricePerUnit();
            log.add(tempLog);

            System.out.println("Sold");
            SalesmanThread.logOf4.add(tempLog);
            SalesmanThread.counter++;


            if(SalesmanThread.counter == 4){
                Sender s = new Sender(ois, oos, SalesmanThread.logOf4);
//                try {
//                    System.out.println("Waiting");
//                    wait();
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }
//
//                System.out.println("Notified");
            }

//            while(!logEntrySent) {
//                try {
//                    oos.writeObject(tempLog);
//
//                    logEntrySent = (Boolean) ois.readObject();
//
//                    if(!logEntrySent)
//                        System.out.println("Sending data failure. Trying to send again...");
//
//                } catch (IOException e) {
//                    e.printStackTrace();
//                } catch (ClassNotFoundException e) {
//                    e.printStackTrace();
//                }
//
//            }

        }

    }

    public LogEntry[] getLog() {
        int size = log.size();
        LogEntry[] logEntries = new LogEntry[size];
        for(int i=0; i<size; i++){
            logEntries[i] = log.get(i);         //transforms list into array
        }


        return  logEntries;
    }

    public ShopItem[] getInventory() {
        ShopItem[] items = new ShopItem[numberOfItems[0]];
        for(int i=0; i<numberOfItems[0]; i++){
            items[i] = inventory[i];      //transforms list into array
        }

        return items;
    }

    synchronized public double getBalance() {
        return balance;
    }


}
