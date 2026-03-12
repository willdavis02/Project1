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
        int lineNumber = 0;

        while ((line = br.readLine()) != null) {
            lineNumber++;

            if (line.trim().isEmpty()) {
                continue;
            }

            String[] parts = line.split("\t");

            try {
                String type = parts[0].trim();

                if (type.equalsIgnoreCase("Electronics")) {
                    if (parts.length < 6) {
                        System.out.println("Skipping invalid Electronics row at line " + lineNumber);
                        continue;
                    }

                    String id = parts[1].trim();
                    String name = parts[2].trim();
                    double price = Double.parseDouble(parts[3].trim());
                    int quantity = Integer.parseInt(parts[4].trim());
                    String brand = parts[5].trim();

                    if (price < 0 || quantity < 0) {
                        System.out.println("Skipping negative price/quantity at line " + lineNumber);
                        continue;
                    }

                    inventory.add(new Electronics(id, name, price, quantity, brand));

                } else if (type.equalsIgnoreCase("Clothing")) {
                    if (parts.length < 7) {
                        System.out.println("Skipping invalid Clothing row at line " + lineNumber);
                        continue;
                    }

                    String id = parts[1].trim();
                    String name = parts[2].trim();
                    double price = Double.parseDouble(parts[3].trim());
                    int quantity = Integer.parseInt(parts[4].trim());
                    String size = parts[5].trim();
                    String material = parts[6].trim();

                    if (price < 0 || quantity < 0) {
                        System.out.println("Skipping negative price/quantity at line " + lineNumber);
                        continue;
                    }

                    inventory.add(new Clothing(id, name, price, quantity, size, material));

                } else if (type.equalsIgnoreCase("Grocery")) {
                    if (parts.length < 6) {
                        System.out.println("Skipping invalid Grocery row at line " + lineNumber);
                        continue;
                    }

                    String id = parts[1].trim();
                    String name = parts[2].trim();
                    double price = Double.parseDouble(parts[3].trim());
                    int quantity = Integer.parseInt(parts[4].trim());
                    LocalDate expirationDate = LocalDate.parse(parts[5].trim());

                    if (price < 0 || quantity < 0) {
                        System.out.println("Skipping negative price/quantity at line " + lineNumber);
                        continue;
                    }

                    inventory.add(new Grocery(id, name, price, quantity, expirationDate));

                } else {
                    System.out.println("Unknown product type at line " + lineNumber + ": " + type);
                }

            } catch (NumberFormatException e) {
                System.out.println("Skipping line " + lineNumber + ". Invalid number format.");
            } catch (DateTimeParseException e) {
                System.out.println("Skipping line " + lineNumber + ". Invalid date format.");
            } catch (Exception e) {
                System.out.println("Skipping line " + lineNumber + ". Invalid data.");
            }
        }

    } catch (IOException e) {
        System.out.println("Error loading inventory file: " + e.getMessage());
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




