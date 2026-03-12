/**
 * Created by Will Davis
 */
package smartkart;

import java.time.LocalDate;

public class Grocery extends Product{
	private LocalDate expirationDate;
	
	
	public Grocery(String productID, String name, double price, int quantity, LocalDate expirationDate) {
		super(productID, name, price, quantity);
		this.expirationDate=expirationDate;
		
	}
	@Override
	public String toString() {
		return super.toString()+"\tExpiration Date: "+expirationDate;
	}
	@Override
	public double calculateTax(int quantity) {
		double tax = 0.0;
		return tax;
	}
	/*
	 * 
	 */
	public boolean isExpired() {
		return LocalDate.now().isAfter(expirationDate);
	}

	public LocalDate getExpirationDate() {
		return expirationDate;
	}


	public void setExpirationDate(LocalDate expirationDate) {
		this.expirationDate = expirationDate;
	}
}
