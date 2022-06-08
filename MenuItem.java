package cafe;

/**
This is the superclass of all menu items.
Defines methods and instance variables common to all menu items.
@author Dharma Wijesinghe, Min Sun You
*/
public class MenuItem {
    
    private double price;
    
    /**
    Default constructor for MenuItem object.
    */
    public MenuItem() {
        
    }
    
    /**
    Returns the menu item price.
    @return the price.
    */
    public double itemPrice() {
        return price;
    }
    
    /**
    The type of object.
    @return the type.
    */
    public String getType() {
        return "MenuItem";
    }
    
    /**
    Returns string representation of objects.
    @param quantity the amount ordered.
    @return the string representation.
    */
    public String toString(int quantity) {
        return "MenuItem (" + quantity + ")";
    }
}