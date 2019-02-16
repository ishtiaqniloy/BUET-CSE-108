package Server_Console;

import java.io.Serializable;

/**
 * Created by Ishtiaq Niloy on 27-Oct-16.
 */
class ShopItem implements Serializable {
    private String name;
    private double sellingPricePerUnit;
    private double buyingPricePerUnit;

    String typeName;
    int type;

    ShopItem(String name, double sellingPricePerUnit, double buyingPricePerUnit) {
        this.name = name;
        this.sellingPricePerUnit = sellingPricePerUnit;
        this.buyingPricePerUnit = buyingPricePerUnit;
        this.typeName="";
        this.type=0;
    }

    ShopItem(){
        name="";
        sellingPricePerUnit=0;
        buyingPricePerUnit=0;
        typeName="";
        type=0;
    }

    void setSellingPricePerUnit(double s) {
        sellingPricePerUnit = s;
    }

    void setBuyingPricePerUnit(double b) {
        buyingPricePerUnit = b;
    }

    void setName(String s) {
        name = s;
    }


    double getSellingPricePerUnit() {
        return sellingPricePerUnit;
    }

    double getBuyingPricePerUnit() {
        return buyingPricePerUnit;
    }

    String getName(){
        return name;
    }

    String getTypeName(){
        return typeName;
    }

    public String toString(){
        String temp;
        temp= typeName + name + "---" + Double.toString(buyingPricePerUnit)+ "-----------" + Double.toString(sellingPricePerUnit) + "\n";

        return  temp;
    }

}

class Apple extends ShopItem{
    boolean isRed;

    Apple(boolean red) {
        super("Apples", 5, 3);
        isRed=red;
        if(isRed){
            type=2;
            typeName="Red ";
        }

        else{
            type=1;
            typeName = "Green ";
        }

    }

}

class Orange extends ShopItem{

    Orange() {
        super("Oranges", 6, 3);
        type=3;
    }
}

class Strawberry extends ShopItem{
    boolean isCanned;

    Strawberry(boolean canned) {
        super("Strawberries", 8, 5);
        type=5;
        typeName = "Packed ";

        if(canned){
            typeName = "Canned ";
            setSellingPricePerUnit(10);
            setBuyingPricePerUnit(8);
            type = 4;
        }

        isCanned=canned;
    }

}