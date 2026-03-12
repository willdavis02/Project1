package smartkart;

/**
 * The Returnable interface defines methods for products that can be returned.
 * Implementing classes must determine return eligibility, calculate refunds,
 * and generate a return label for the customer.
 */
/**
 * Made by Maksym Nikulin
 */
public interface Returnable {
	boolean isEligibleForReturn(int daysSincePurchase);
	double processRefund(int quantity, String condition);
	String generateReturnLabel(String customerName, String address);
}

