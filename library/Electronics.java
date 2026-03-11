package library;

public abstract class Electronics extends Products implements Returnable {
	private String brand;

	public Electronics(String id, String name, double price, int quantity, String brand) {
		super(id, name, price, quantity);
		this.brand = brand;
	}
	
	@Override
	public double calculateTax(int quantity) {
		return getPrice() * quantity * 0.15;
		
	}
	
	@Override
	public boolean isEligibleForReturn(int daysSincePurchase) {
		return daysSincePurchase <= 15;
	}
	
	@Override 
	public 	double processRefund(int quantity, String condition) {
		double refund = getPrice()* quantity;
		
		if (condition.equalsIgnoreCase("Open Box")) {
			refund = refund *0.90;
		}
		
		restock(quantity);
		return refund;
	}
	
	@Override
	public String generateReturnLabel(String customerName, String address) {
		return "---RETURN LABLE---"
				+"Customer: " + customerName + "\n"
				+"Address: " + address + "\n"
				+"Product: " + getName() + "\n"
				+"Product ID: " + getID() + "\n";
	}
	
	public String getBrand() {
		return brand;
	}
	
	
}
