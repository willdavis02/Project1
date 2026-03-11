package smartkart;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;

public class StoreManager{
	 private ArrayList<Product> inventory;
	 private ArrayList<CartItem> cart;

public StoreManager() {
    inventory = new ArrayList<>();
    cart = new ArrayList<>();
}

public void loadInventoryFromFile(String fileName) {
    try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
        String line;
        while ((line = br.readLine()) != null) {
            String[] parts = line.split("\t");

            String type = parts[0];

            if (type.equalsIgnoreCase("Electronics")) {
                inventory.add(new Electronics(
                        parts[1], parts[2],
                        Double.parseDouble(parts[3]),
                        Integer.parseInt(parts[4]),
                        parts[5]
                ));
            } else if (type.equalsIgnoreCase("Clothing")) {
                inventory.add(new Clothing(
                        parts[1], parts[2],
                        Double.parseDouble(parts[3]),
                        Integer.parseInt(parts[4]),
                        parts[5], parts[6]
                ));
            } else if (type.equalsIgnoreCase("Grocery")) {
                inventory.add(new Grocery(
                        parts[1], parts[2],
                        Double.parseDouble(parts[3]),
                        Integer.parseInt(parts[4]),
                        LocalDate.parse(parts[5])
                ));
            }
        }
    } catch (IOException e) {
        System.out.println("Error loading inventory file.");
    }
}


	
	
public void viewInventory() {
    System.out.println("\n=== INVENTORY ===");
    for (Product p : inventory) {
        System.out.println(p);
    }
}

public Product findProductByID(String id) {
    for (Product p : inventory) {
        if (p.getProductID().equalsIgnoreCase(id)) {
            return p;
        }
    }
    return null;
}

public void addToCart(String productID, int quantity) {
    Product p = findProductByID(productID);

    if (p == null) {
        System.out.println("Invalid product ID.");
        return;
    }

    if (quantity <= 0) {
        System.out.println("Quantity must be positive.");
        return;
    }

    if (p instanceof Grocery) {
        Grocery g = (Grocery) p;
        if (g.isExpired()) {
            System.out.println("Cannot purchase expired grocery item.");
            return;
        }
    }

    if (!p.purchase(quantity)) {
        System.out.println("Insufficient stock.");
        return;
    }

    cart.add(new CartItem(p, quantity));
    System.out.println("Item added to cart.");
}

public void checkout() {
    if (cart.isEmpty()) {
        System.out.println("Cart is empty.");
        return;
    }

    double subtotal = 0;
    double totalTax = 0;

    System.out.println("\nItem\tQty\tPrice\tTax");
    System.out.println("--------------------------------");

    for (CartItem item : cart) {
        double itemTotal = item.getItemTotal();
        double tax = item.getTax();

        subtotal += itemTotal;
        totalTax += tax;

        System.out.printf("%s\t%d\t%.2f\t%.2f%n",
                item.getProduct().getName(),
                item.getQuantity(),
                itemTotal,
                tax);
    }

    System.out.println("--------------------------------");
    System.out.printf("Subtotal: %.2f%n", subtotal);
    System.out.printf("Total Tax: %.2f%n", totalTax);
    System.out.printf("Total: %.2f%n", subtotal + totalTax);

    cart.clear();
}

public void processReturn(String productID, int daysSincePurchase, int quantity,
                          String condition, String customerName, String address) {
    Product p = findProductByID(productID);

    if (p == null) {
        System.out.println("Invalid product ID.");
        return;
    }

    if (!(p instanceof Returnable)) {
        System.out.println("Sorry, this item cannot be returned.");
        return;
    }

    if (quantity <= 0) {
        System.out.println("Invalid quantity.");
        return;
    }

    Returnable r = (Returnable) p;

    if (!r.isEligibleForReturn(daysSincePurchase)) {
        System.out.println("Item is not eligible for return.");
        return;
    }

    double refund = r.processRefund(quantity, condition);

    System.out.printf("Refund amount: $%.2f%n", refund);
    System.out.println(r.generateReturnLabel(customerName, address));
}
}




