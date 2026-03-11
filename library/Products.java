package library;

public abstract class Products {
private String id;
private String name;
private double price;
private int quantity;

public Products(String id, String name, double price, int quantity) {
	this.id = id;
	this.name = name;
	this.price = price;
	this.quantity = quantity; 
}

public abstract double calculateTax(int quantity);

public boolean purchase (int amount) {
	if (amount <=0 || amount > quantity) {
		return false;
		
	}
	quantity -= amount;
	return true;
}

public void restock(int amount) {
	if (amount > 0) {
		quantity += amount;
	}
}

public String getID() {
	return id;
}

public String getName() {
	return name;
}

public double getPrice() {
	return price;
}

public int getQuantity() {
	return quantity;
}

@Override
public String toString() {
	return "Product ID: " + id + "|" + " Name: " + name + " Price: " + String.format("%.2f",price ) + " Quantity: " + quantity;
}
	
}
