package smartkart;

public abstract class Product {
	private String productID;
	private String name;
	protected double price;
	private int quantity;
	
	
	
	public Product (String productID, String name, double price, int quantity) {
		this.productID=productID;
		this.name=name;
		this.price=price;
		this.quantity= quantity;
		
	}
	
	public abstract double calculateTax(int quantity);
	
	
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
	
	public void restock(int amount) {
		quantity+=amount;
		
	}
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
