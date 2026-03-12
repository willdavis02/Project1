package smartkart;

/**
 * Made by Maksym Nikulin
 */
public class Electronics extends Product implements Returnable {
	private String brand;
	
	/**
	 * @param productID - Id number if the product
	 * @param name - name of the product
	 * @param price - price of the product
	 * @param quantity - quantity of the product available
	 * @param brand - brand of the product
	 */
	public Electronics(String productID, String name, double price, int quantity, String brand) {
		super(productID, name, price, quantity);
		this.setBrand(brand);
	}
	
	/**
	 *Add brand to a String description of the product
	 */
	@Override
	public String toString() {
		return super.toString()+"\tBrand: "+brand;
	}
	
	/**
	 *Add condition for being eligible for return. 
	 *Electronics can only be returned if they were purchased less than 15 day ago 
	 */
	@Override
	public boolean isEligibleForReturn(int daysSincePurchase) {
		if(daysSincePurchase>15) {
			return false;
		}
		else if(daysSincePurchase<0){
			System.out.println("Enter valid amount of days since purchase.");
			return false;
		}
		else {
			return true;
		}
	}
	
	
	/**
	 *Create a return label with customer's name and address
	 */
	@Override
	public String generateReturnLabel(String customerName, String address) {
		return ("Return Label for: "+customerName+ "\n Address: "+ address);
	}
	
	/**
	 *THis method takes care of the refund for returning items. 
	 *There is 10% restock fee for Open Box.Everyhting else - Full refund. 
	 */
	@Override
	public double processRefund(int quantity, String condition) {
		double refund = 0.0;
		
		if(condition.equalsIgnoreCase("Open Box")) {
			refund = .9* quantity*price;
		}
		else {
			refund = quantity*price;
		}
		
		return refund;
		
	}
	
	/**
	 *This method calculates tax for Electronics purchases. The tax is 15%
	 */
	@Override
	public double calculateTax(int quantity) {
		double electronicsTax =0.15;
		double tax = quantity*price*electronicsTax;
		return tax;
		
		
	}
	
	/**
	 * @return Getter for Brand
	 */
	public String getBrand() {
		return brand;
	}
	
	/**
	 * @param Setter for Brand
	 */
	public void setBrand(String brand) {
		this.brand = brand;
	}
}
