package smartkart;

import java.util.Scanner;
import java.io.*;
import java.time.LocalDate;

public class Main {

	/**
	 * Authors Will Davis and Maksym Nikulin
	 * This method creates the file reader to read from the tsv file, as well as prints the store directory to the console and allows users to interact with the store
	 * @param args
	 */
	public static void main(String[] args) {
		//creating general scanner to take user input from keyboard
		//creates new object of storeManager
		Scanner scn = new Scanner(System.in);
		StoreManager store = new StoreManager();
		
		//Reads from tsv file, creates new objects of electronics, clothing, and grocery based on the first letter of the productID(E,C,G), Converts values from tsv into double and Integer values when necessary
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
		//Prints directory to console for user, prompts for user input of int value
		while(true) {
			System.out.println("\n***SmartKart Home***");
			System.out.println("1. View Inventory");
			System.out.println("2. Add to Cart");
			System.out.println("3. Checkout");
			System.out.println("4. Return Kiosk");
			System.out.println("5. Exit");
			int input= scn.nextInt();
			
			//Switch that implements the different features based on user input 1-5
			switch(input) {
				//Prints the inventory
				case 1:
					System.out.println("====STORE INVENTORY====");
					System.out.println("\n**********************");
					for(Product p : store.getInventory()) {
					System.out.println(p);
				}
					break;
				//Add to cart, collects user input productID, makes String id from user input	
				case 2:
					System.out.println("You've Selected: Add to Cart");
					System.out.println("Enter product ID of the Item you wish to purchase: ");
					String id = scn.next();
				
					
					//Creates a null Product called found, iterates through getInventory ArrayList method from StoreManager class. If the productID matches the productID entered by the user, assigns Product found to the value in the ArrayList.
					Product found =null;
					for(Product p: store.getInventory()) {
						if(p.getProductID().equalsIgnoreCase(id)) {
							found = p;
							break;
						}
					}
					//If the item isn't found, prints error message.
					if(found == null) {
						System.out.println("Invalid ProductID: Product not found");
						break;
				}
					//Prompts user to enter the quantity of the item they wish to by, and takes the int input in
					System.out.println("Enter quantity: ");
					int quant = scn.nextInt();
					
					//if found 
					if(found instanceof Grocery) {
						Grocery grocery= (Grocery) found;
						if(grocery.isExpired()) {
							System.out.println("This grocery is expired, you can't purchase it.");
							break;
						}
					}
					if(found.purchase(quant)) {
						store.addToCart(found, quant);
						System.out.println("Added to Cart");
					}
					break;
				case 3:
					if(store.getCart().isEmpty()) {
						System.out.println("Cart is empty. Add items to your cart before checking out.");
						break;
					}
					
					double subtotal = 0.0;
					double totalTax = 0.0;
					
					System.out.println("Item \tQty \tPrice \tTax");
					System.out.println("----------------------------");
					
					for(CartItem item : store.getCart()) {
						Product p = item.getProduct();
						int quantity = item.getQuantity();
						double itemTotal = p.getPrice()*quantity;
						double itemTax = p.calculateTax(quantity);
						
						subtotal+=itemTotal;
						totalTax +=itemTax;
						
						System.out.printf("%s\t%d\t$%.2f\t$%.2f", p.getName(), quantity, itemTotal, itemTax);
						System.out.println();
					}
					System.out.println("-----------------------------");
					System.out.printf("Subtotal: $%.2f", subtotal);
					System.out.println();
					System.out.printf("Tax: $%.2f", totalTax);
					System.out.println();
					System.out.printf("Total: $%.2f", subtotal+totalTax);
					System.out.println();
					
					store.getCart().clear();
					break;
				
				case 4:
					System.out.println("Return Kiosk");
					System.out.println("Enter product ID: ");
					String returnID= scn.next();
					
					Product returnProduct = null;
					for(Product p : store.getInventory()) {
						if(p.getProductID().equalsIgnoreCase(returnID)) {
							returnProduct= p;
							break;
						}
					}
					if(returnProduct==null){
						System.out.println("Product not found. Please enter valid Product ID");
						break;
					}
					if(!(returnProduct instanceof Returnable)) {
						System.out.println("This item can't be returned.");
						break;
					}
					Returnable prod = (Returnable) returnProduct;
					
					System.out.println("Days since purchase: ");
					int days= scn.nextInt();
					
					System.out.println("Enter quantity: ");
					int returnQuantity = scn.nextInt();
					scn.nextLine();
					
					System.out.print("Enter condition: ");
					String condition = scn.nextLine();
					
					if (prod.isEligibleForReturn(days)) {
						double refund = prod.processRefund(returnQuantity, condition);
						returnProduct.restock(returnQuantity);
						System.out.printf("Refunding: $%.2f", refund);
						System.out.println();
						System.out.println("Enter name for return label: ");
						String returnName = scn.nextLine();
						System.out.println("Enter your address for return label: ");
						String address = scn.nextLine();
						System.out.println(prod.generateReturnLabel(returnName, address));
						
					}
					else {
						System.out.println("This item was purchased beyond the return window");
					}
					break;
				case 5:
					System.out.println("Thank you for visting SmartKart. Goodbye.");
					scn.close();
					return;
				
				default:
					System.out.println("Invalid choice, try again");
					break;
			
			}
			
		}
	}

}
