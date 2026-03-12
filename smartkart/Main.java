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
		
		while(true) {
			System.out.println("\n***SmartKart Home***");
			System.out.println("1. View Inventory");
			System.out.println("2. Add to Cart");
			System.out.println("3. Checkout");
			System.out.println("4. Return Kiosk");
			System.out.println("5. Exit");
			int input= scn.nextInt();
			
			
			switch(input) {
				case 1:
					System.out.println("====STORE INVENTORY====");
					System.out.println("\n**********************");
					for(Product p : store.getInventory()) {
					System.out.println(p);
				}
					break;
				case 2:
					System.out.println("You've Selected: Add to Cart");
					System.out.println("Enter product ID of the Item you wish to purchase: ");
					String id = scn.next();
				
					Product found =null;
					for(Product p: store.getInventory()) {
						if(p.getProductID().equalsIgnoreCase(id)) {
							found = p;
							break;
						}
					}
					if(found == null) {
						System.out.println("Invalid ProductID: Product not found");
						break;
				}
				
					System.out.println("Enter quantity: ");
					int quant = scn.nextInt();
				
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
