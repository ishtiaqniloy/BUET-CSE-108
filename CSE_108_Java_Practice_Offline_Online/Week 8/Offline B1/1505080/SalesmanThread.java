package FruitShop;

/**
 * Created by Ishtiaq Niloy on 12-Nov-16.
 */

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class SalesmanThread extends Thread{

    FruitShop fruitShop;
    int salesmanIndex;
    String fileName;
    int sleepTime=0;

    public SalesmanThread(FruitShop fs,int salesmanIndex) {
        //Insert your code here
        fruitShop=fs;
        this.salesmanIndex=salesmanIndex;


        if(salesmanIndex==1){
            fileName="C:\\Users\\Ishti\\Google Drive\\Projects\\IntelliJ_Projects\\Offline_Inheritance_Interface\\src\\FruitShop\\input1.txt";
            sleepTime=3000; //Time is shortened to be fast with viewing results
        }

        else if(salesmanIndex==2){
            fileName="C:\\Users\\Ishti\\Google Drive\\Projects\\IntelliJ_Projects\\Offline_Inheritance_Interface\\src\\FruitShop\\input2.txt";
            sleepTime=2000; //Time is shortened to be fast with viewing results
        }

        else if(salesmanIndex==3){
            fileName="C:\\Users\\Ishti\\Google Drive\\Projects\\IntelliJ_Projects\\Offline_Inheritance_Interface\\src\\FruitShop\\input3.txt";
            sleepTime=4000; //Time is shortened to be fast with viewing results
        }


    }

    @Override
    public void run() {
        //Insert your code here
        Scanner sc = null;

        try {
            sc = new Scanner(new File(fileName));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        int n = sc.nextInt();

        //System.out.println(n);

        int choice, type, amount;
        for (int i = 0; i < n; i++) {
            choice = sc.nextInt();
            if (choice == 1) {
                type=sc.nextInt();
                amount=sc.nextInt();
                fruitShop.buy(type, amount, salesmanIndex);
                //System.out.println(choice+" "+type+" "+amount);
            }
            else if (choice == 2) {
                type=sc.nextInt();
                amount=sc.nextInt();

                try {
                    sleep(sleepTime);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                fruitShop.sell(type, amount, salesmanIndex);
                //System.out.println(choice+" "+type+" "+amount);
            }
            else if (choice == 3) {
                System.out.println(fruitShop.getBalance());
                //System.out.println(choice);
            }

        }

    }


    public static void main(String[] args) {
        FruitShop fs=new FruitShop(20, 70);

        SalesmanThread salesmanThread[]=new SalesmanThread[3];

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
    }
}
