package smartkart;

public interface Returnable {
	boolean isEligibleForReturn(int daysSincePurchase);
	double processRefund(int quantity, String condition);
	String generateReturnLabel(String customerName, String address);
}
