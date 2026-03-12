Project 1 Smart Cart implements a back end for the online store. It has functionality of pulling items from 
the file and putting them into ArrayList. Users are presented with the interface where they can
view the inventory, add items to the cart, checkout, return items, and close the interface. 


*CartItem* - this class represents an item in the shopping cart. 
It stores a Product object and the quantity of that product that the customer wants to purchase, 
and provides methods to access or modify those values. This class was created by Will Davis.

*Clothing* - this class represents a clothing product in the store. 
It extends the Product class and implements the Returnable interface, adding clothing-specific
details like size and material, and defining how clothing items are taxed, returned, and refunded. This class
was created by Will Davis.

*Electronics* - this class represents electronic products in the store. 
It extends the Product class and implements the Returnable interface, adding a brand attribute 
and defining specific rules for taxes, returns, and refunds for electronic items. This class was created by Maksym 
Nikulin.

*Grocery* - this class represents grocery products in the store. 
It extends the Product class and adds an expiration date, includes a method to check if 
the item is expired, and overrides the tax calculation so that groceries have no tax.
This class was created by Will Davis.

*Main* - this class runs the SmartKart program. 
It loads inventory items from a TSV file, creates the store menu, and lets the user view products, 
add items to the cart, check out, process returns, or exit the program. This class was created by both
Will Davis and Maksym Nikulin.

*Product* - this class is an abstract base class that represents a general product in the store.
It stores common information like product ID, name, price, and quantity, and provides shared 
methods for purchasing, restocking, and displaying product details, while requiring subclasses 
to define how tax is calculated. This class was created by Maksym Nikulin.

*Returnable* - this interface defines methods that allow a product to be returned. 
It requires classes that implement it to determine if an item is eligible for return, 
calculate the refund amount, and generate a return label for the customer. This interface was
created by Maksym Nikulin.

*Store Manager* - this class manages the store’s inventory and the customer’s shopping cart.
It stores products in an inventory list, allows products to be added to the cart, 
and updates the quantity if the same product is added more than once. This class was created by Will Davis.

