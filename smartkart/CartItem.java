/**
 * Created by the Will Davis, this package creates an object CartItem, that is later used in StoreManager, where there is an ArrayList that stores items
 * of CartItem.
 */
package smartkart;

public class CartItem {
	private Product product;
	private int quantity;

	/**
	 * This constructor signature takes in a Product product and int quantity, so when 
	 * added to ArrayList, it will store both the product, and the amount wanted of that product in the cart.
	 * @param product
	 * @param quantity
	 */
	public CartItem(Product product, int quantity) {
		this.product=product;
		this.quantity=quantity;
	}
	/**
	 * Getter for product
	 * @return
	 */
	public Product getProduct() {
		return product;
	}
	/**
	 * Setter for product
	 * @param product
	 */
	public void setProduct(Product product) {
		this.product = product;
	}
	/**
	 * Getter for quantity
	 * @return
	 */
	public int getQuantity() {
		return quantity;
	}
	/**
	 * Setter for quantity
	 * @param quantity
	 */
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
}
