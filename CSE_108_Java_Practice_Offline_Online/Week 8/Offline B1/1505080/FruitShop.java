package FruitShop;

/**
 * Created by Ishtiaq Niloy on 27-Oct-16.
 */

import java.util.ArrayList;
import java.util.Date;
import java.text.SimpleDateFormat;
import java.util.List;

class LogEntry{
    String time;
    String name;
    String boughtORsold;
    int amount;
    int salesmanIndex;

    LogEntry(int type, boolean isSold, int amount, int salesmanIndex){
        this.amount=amount;
        this.salesmanIndex = salesmanIndex;

        if(isSold){
            boughtORsold = "Sold" ;
        }
        else{
            boughtORsold = "Bought";
        }

        SimpleDateFormat timeFormat = new SimpleDateFormat("EEE MMM dd HH:mm:ss zzz yyyy");
        time = timeFormat.format( new Date() );

        //System.out.println(time);

        ShopItem temp = new ShopItem();

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

        name = temp.getTypeName() + temp.getName();

        //System.out.println("Entry created\n" + this);

    }

    public String toString(){
        String temp;
        temp = time +"  " + name + "  " + Integer.toString(amount) +"  " + boughtORsold + "      " + Integer.toString(salesmanIndex);

        return  temp;
    }

}


interface Shop{
    void buy(int type,int amount, int salesmanIndex);
    void sell(int type, int amount, int salesmanIndex);
    LogEntry[] getLog();
    ShopItem[] getInventory();
    double getBalance();
}


class FruitShop implements Shop{
    private List<LogEntry> log = new ArrayList<LogEntry>();

    private int[] numberOfItems = new int[6];
    private ShopItem[] inventory;


    private double balance=0;
    private int capacity=0;

    private ShopItem temp;

    FruitShop(int cap, double initialBalance){
        balance=initialBalance;
        capacity=cap;

        for(int i=0; i<6 ; i++){
            numberOfItems[i] = 0;
        }
        inventory = new ShopItem[capacity];

    }


    synchronized public void buy(int type, int amount, int salesmanIndex) {
        LogEntry tempLog = new LogEntry(type,  false, amount, salesmanIndex);

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

        }

    }

    synchronized public void sell(int type, int amount, int salesmanIndex) {
        LogEntry tempLog = new LogEntry(type,  true, amount, salesmanIndex);

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
