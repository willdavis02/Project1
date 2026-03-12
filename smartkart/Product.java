package smartkart;

/**
 * Made by Maksym Nikulin
 */
/**
 * 
 */
/**
 * 
 */
public abstract class Product {
	private String productID;
	private String name;
	protected double price;
	private int quantity;
	
	
	
	/**
	 * @param productID - Id number of the product
	 * @param name - name of the product
	 * @param price - price of the product
	 * @param quantity - quantity of the product available
	 */
	public Product (String productID, String name, double price, int quantity) {
		this.productID=productID;
		this.name=name;
		this.price=price;
		this.quantity= quantity;
		
	}
	
	/**
	 * @param This method defines CalculateTax method
	 * @return
	 */
	public abstract double calculateTax(int quantity);
	
	
	/**
	 * THis class allows customer to make a purchase
	 * @param amount - how many of the certain item customer wants to purchase
	 * @return Can purchase or not. Subtract the purchased amount from the total
	 */
	public boolean purchase(int amount) {
		if (amount>quantity) {
			System.out.println("There are only "+quantity+ " of this item available.");
			return false;
		}
		else {
			quantity= quantity-amount;
			return true;
		}
	}
	
	/**
	 * THis method adds restock items to the total amount
	 * @param amount - amount to restock
	 * 
	 */
	public void restock(int amount) {
		quantity+=amount;
		
	}
	
	
	/**
	 *This method make a string describing the item
	 */
	public String toString() {
		return "Product ID: " + productID +"\tItem name: " + name+ "\tPrice: $"+price+"\tQuantity: "+getQuantity();
	}
	
	
	/**
	 * Getters and setters
	 */
	public String getProductID() {
		return productID;
	}
	public void setProductID(String productID) {
		this.productID = productID;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity=quantity;
	}
}
