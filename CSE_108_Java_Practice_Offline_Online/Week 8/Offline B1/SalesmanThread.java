import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class SalesmanThread extends Thread{

	FruitShop fruitShop;
	int salesmanIndex;
	String fileName="input";
	public SalesmanThread(FruitShop fs,int salesnamIndex) {
		//Insert your code here
	}
	
	@Override
	public void run() {
		//Insert your code here
	}
	
	
	public static void main(String[] args) {
		FruitShop fs=new FruitShop(20, 70);
		
		SalesmanThread salesmanThread[]=new SalesmanThread[3];
		
		for (int i = 0; i < salesmanThread.length; i++) {
			salesmanThread[i]= new SalesmanThread(fs,i+1);
			salesmanThread[i].start();
		}
		
				
		for (ShopItem s : fs.getInventory()) {
			System.out.println(s);
		}
	}
}
