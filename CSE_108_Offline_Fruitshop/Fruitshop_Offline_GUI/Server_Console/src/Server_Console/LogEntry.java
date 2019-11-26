package Server_Console;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;

class LogEntry implements Serializable {
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
        temp = time +"----" + name + "----" + Integer.toString(amount) +"----" + boughtORsold + "----" + Integer.toString(salesmanIndex)+ "\n";

        return  temp;
    }

}
