import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class ScannerDemo {

	public static void main(String[] args) throws FileNotFoundException {

		
		// You can give the absolute path or the relative path of the file.
		// This demo uses relative path.
		// Also if you want to use file separator you must use either \\ or only /
		Scanner sc = new Scanner(new File("src/input.txt"));

		int n = sc.nextInt();
		System.out.println(n);
		int choice, type, amount;
		for (int i = 0; i < n; i++) {
			choice = sc.nextInt();
			if (choice == 1) {
				type=sc.nextInt();
				amount=sc.nextInt();
				System.out.println(choice+" "+type+" "+amount);
			} else if (choice == 2) {
				type=sc.nextInt();
				amount=sc.nextInt();
				System.out.println(choice+" "+type+" "+amount);
			} else if (choice == 3) {
				System.out.println(choice);
			}
		}
	}

}
