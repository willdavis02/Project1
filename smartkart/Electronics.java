package smartkart;

public class Electronics extends Product implements Returnable {
	private String brand;
	
	public Electronics(String productID, String name, double price, int quantity, String brand) {
		super(productID, name, price, quantity);
		this.setBrand(brand);
	}
	
	@Override
	public String toString() {
		return super.toString()+"\tBrand: "+brand;
	}
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
	@Override
	public String generateReturnLabel(String customerName, String address) {
		return ("Return Label for: "+customerName+ "\n Address: "+ address);
	}
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
	@Override
	public double calculateTax(int quantity) {
		double electronicsTax =0.15;
		double tax = quantity*price*electronicsTax;
		return tax;
	}
	public String getBrand() {
		return brand;
	}
	public void setBrand(String brand) {
		this.brand = brand;
	}
}
