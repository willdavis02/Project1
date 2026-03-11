package smartkart;

import java.util.ArrayList;

public class StoreManager {
	private ArrayList<Product> inventory;
	private ArrayList<Product> cart;
	
	public StoreManager() {
		inventory= new ArrayList<>();
		cart = new ArrayList<>();
		
	}
	public void addInventory(Product p) {
		inventory.add(p);
	}
	
	

	public ArrayList<Product> getInventory() {
		return inventory;
	}

	public ArrayList<Product> getCart() {
		return cart;
	}




}
