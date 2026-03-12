package smartkart;

public class Clothing extends Product implements Returnable {
	private String size;
	private String material;
	
	
	public Clothing(String productID, String name, double price, int quantity, String size, String material) {
		super(productID, name, price, quantity);
		this.size=size;
		this.material=material;
	}
	@Override
	public String toString() {
		return super.toString()+ "\tSize: "+size+"\tMaterial: "+material;
	}
	@Override
	public double calculateTax(int quantity) {
		double clothingTax= .05;
		double tax = quantity*price*clothingTax;
		
		return tax;
	}
	@Override
	public boolean isEligibleForReturn(int daysSincePurchase) {
		if(daysSincePurchase>30) {
			return false;
		}
		else if(daysSincePurchase<0) {
			return false;
		}
		else {
			return true;
		}
	}
	@Override
	public double processRefund(int quantity, String condition) {
		double refund=0.0;
		if(condition.equalsIgnoreCase("worn")) {
			refund =0.0;
		}
		else {
			refund = quantity*price;
		}
		return refund;
	}
	@Override
	public String generateReturnLabel(String customerName, String address) {
		return ("Customer name: "+customerName+ "\n Address: "+address);
	}
	public String getSize() {
		return size;
	}


	public void setSize(String size) {
		this.size = size;
	}


	public String getMaterial() {
		return material;
	}


	public void setMaterial(String material) {
		this.material = material;
	}
}
