/**
 * Created by Will Davis, This class is a subclass of Product
 */
package smartkart;

import java.time.LocalDate;

public class Grocery extends Product{
	private LocalDate expirationDate;
	
	/**
	 * Constructor signature has all values taken in
	 * Calls super constructor for productID, name, price, quantity
	 * assigns class specific attribute expirationDate
	 * @param productID
	 * @param name
	 * @param price
	 * @param quantity
	 * @param expirationDate
	 */
	public Grocery(String productID, String name, double price, int quantity, LocalDate expirationDate) {
		super(productID, name, price, quantity);
		this.expirationDate=expirationDate;
		
	}
	/**
	 * toString method is also a method in parent class Product
	 * calls for the return of values in toString Product method, as well as returns class specific expirationDate
	 */
	@Override
	public String toString() {
		return super.toString()+"\tExpiration Date: "+expirationDate;
	}
	/**
	 * Abstract method from product, there is no tax on groceries per the assignment so just return the double tax of 0.0
	 */
	@Override
	public double calculateTax(int quantity) {
		double tax = 0.0;
		return tax;
	}
	/*
	 * Checks if the current date is after the date of expirationDate of a given item
	 * returns a boolean value of true if date is after, meaning item is expired
	 * returns false if the date is before, meaning item not expired
	 */
	public boolean isExpired() {
		return LocalDate.now().isAfter(expirationDate);
	}
	
	/**
	 * Getter for LocalDate expirationDate
	 * @return
	 */
	public LocalDate getExpirationDate() {
		return expirationDate;
	}
	/**
	 * Setter for LocalDate expirationDate
	 * @param expirationDate
	 */
	public void setExpirationDate(LocalDate expirationDate) {
		this.expirationDate = expirationDate;
	}
}
