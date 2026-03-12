package smartkart;

import java.util.ArrayList;

public class StoreManager{
	 private ArrayList<Product> inventory;
	 private ArrayList<CartItem> cart;

	 public StoreManager() {
		 inventory = new ArrayList<>();
		 cart = new ArrayList<>();
	 }

	 public void addToInventory(Product p) {
		 inventory.add(p);
	 }
	 /**
	  * Author Will Davis
	  * This method calls methods from CartItem and Product by checking to see if they have matching productIDs, in which case it adds to the existing quantity.
	  * Otherwise it creates a new cartItem.
	  * @param p
	  * @param quantity
	  */
	 public void addToCart(Product p, int quantity) {
		 for(CartItem item: cart) {
			 if(item.getProduct().getProductID().equals(p.getProductID())) {
				 item.setQuantity(item.getQuantity()+quantity);
				 return;
			 }
		 }
		 cart.add(new CartItem(p ,quantity));
	 }
	 
	 public ArrayList<Product> getInventory() {
		 return inventory;
	}

	public ArrayList<CartItem> getCart() {
		return cart;
	}






}

