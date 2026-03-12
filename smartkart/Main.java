package smartkart;

import java.util.Scanner;
import java.io.*;
import java.time.LocalDate;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner scn = new Scanner(System.in);
		StoreManager store = new StoreManager();
		
		try {
			String filePath ="/Users/williamdavis/Desktop/Project1 Store Inventory.tsv";
			FileInputStream fis = new FileInputStream(filePath);
			Scanner fileScn = new Scanner(fis);
			
			while(fileScn.hasNextLine()) {
				String line = fileScn.nextLine();
				String[]parts = line.split("\t");
				String id = parts[0];
				
				if(id.startsWith("E")) {
					store.addToInventory(new Electronics(parts[0], parts[1], Double.parseDouble(parts[2]), Integer.parseInt(parts[3]), parts[4]));
					
				}
				else if(id.startsWith("C")) {
					store.addToInventory(new Clothing(parts[0], parts[1], Double.parseDouble(parts[2]), Integer.parseInt(parts[3]),parts[4],parts[5]));
				}
				else if(id.startsWith("G")) {
					store.addToInventory(new Grocery(parts[0],parts[1],Double.parseDouble(parts[2]),Integer.parseInt(parts[3]), LocalDate.parse(parts[4])));
					
				}
			}
			fileScn.close();
			
		}catch(FileNotFoundException e) {
			System.out.println("Inventory file not found.");
		}
		
	}

}
