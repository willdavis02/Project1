Porject 1 Smart Cart implmenets a back end for the online store. It has functionality of pulling items from 
the file and putting them into ArrayList. Users are presented with the interface where they can
view the inventory, add itmes to the cart, checkout, return items, and close the intrface. 


*CartItem* - this class represents an item in the shopping cart. 
It stores a Product object and the quantity of that product that the customer wants to purchase, 
and provides methods to access or modify those values.

*Clothing* - this calss represents a clothing product in the store. 
It extends the Product class and implements the Returnable interface, adding clothing-specific
details like size and material, and defining how clothing items are taxed, returned, and refunded.

*Electronics* - this class represents electronic products in the store. 
It extends the Product class and implements the Returnable interface, adding a brand attribute 
and defining specific rules for taxes, returns, and refunds for electronic items.

*Grocery* - this class represents grocery products in the store. 
It extends the Product class and adds an expiration date, includes a method to check if 
the item is expired, and overrides the tax calculation so that groceries have no tax.

*Main* - this class runs the SmartKart program. 
It loads inventory items from a TSV file, creates the store menu, and lets the user view products, 
add items to the cart, check out, process returns, or exit the program.

*Product* - this class is an abstract base class that represents a general product in the store.
It stores common information like product ID, name, price, and quantity, and provides shared 
methods for purchasing, restocking, and displaying product details, while requiring subclasses 
to define how tax is calculated.

*Returnable* - this interface defines methods that allow a product to be returned. 
It requires classes that implement it to determine if an item is eligible for return, 
calculate the refund amount, and generate a return label for the customer.

*Store Manager* - this class manages the store’s inventory and the customer’s shopping cart.
It stores products in an inventory list, allows products to be added to the cart, 
and updates the quantity if the same product is added more than once.
