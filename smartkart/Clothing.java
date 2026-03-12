/**
 * Created by Will Davis, this class is an extension of Product and implements the Interface Returnable
 * Creates methods that are abstract or in the Interface and uses own implementations of them
 * Also has getters and setters for the private String attributes created
 */

package smartkart;

public class Clothing extends Product implements Returnable {
	private String size;
	private String material;
	
	/**
	 * Constructor has method signature of all below parameters
	 * Calls the super constructor to avoid code redundancy
	 * sets size and material to input values from signature.
	 * @param productID
	 * @param name
	 * @param price
	 * @param quantity
	 * @param size
	 * @param material
	 */
	public Clothing(String productID, String name, double price, int quantity, String size, String material) {
		super(productID, name, price, quantity);
		this.size=size;
		this.material=material;
	}
	/**
	 * Calls super toString, and then prints the information unique to Clothing class
	 */
	@Override
	public String toString() {
		return super.toString()+ "\tSize: "+size+"\tMaterial: "+material;
	}
	/**
	 * This is an abstract class in Product, implements own implementation here, where the tax is 5%
	 */
	@Override
	public double calculateTax(int quantity) {
		double clothingTax= .05;
		double tax = quantity*price*clothingTax;
		
		return tax;
	}
	/**
	 * From Returnable interface, this implements Clothing implementation of it, where the return window is 30 days
	 */
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
	/**
	 * From Returnable interface, this implements Clothing specific requirements, in thise case the String condition reading "worn"
	 */
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
	/**
	 * Generates return label
	 */
	@Override
	public String generateReturnLabel(String customerName, String address) {
		return ("Customer name: "+customerName+ "\n Address: "+address);
	}
	
	//Getters and setters for the attributes unique to this class
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
