/**
 * Created by Will Davis
 * The purpose of this class is to have a way to manage the store's inventory, as well as the user's cart. 
 * This is done by creating ArrayLists of Products and CartItems.
 */
package smartkart;

import java.util.ArrayList;

public class StoreManager{
	 private ArrayList<Product> inventory;
	 private ArrayList<CartItem> cart;

	 /**
	  * Creates new ArrayLists inventory and cart
	  */
	 public StoreManager() {
		 inventory = new ArrayList<>();
		 cart = new ArrayList<>();
	 }
	 /**
	  * Adds input product p to the inventory ArrayList
	  * @param p
	  */
	 public void addToInventory(Product p) {
		 inventory.add(p);
	 }
	 /**
	  * 
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
	 /**
	  * Getter for ArrayList<Product> inventory
	  * @return
	  */
	 public ArrayList<Product> getInventory() {
		 return inventory;
	}
	 /**
	  * getter for ArrayList<CartItem> cart
	  * @return
	  */
	public ArrayList<CartItem> getCart() {
		return cart;
	}






}

