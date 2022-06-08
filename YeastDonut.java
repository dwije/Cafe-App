package cafe;

/**
This describes a YeastDonut object, which a flavor.
@author Dharma Wijesinghe, Min Sun You
*/
public class YeastDonut extends MenuItem {
    private final double price = 1.59;
    private YeastDonutFlavor yeastDonutFlavor;
    
    /**
    Default constructor for YeastDonut.
    */
    public YeastDonut() {
        
    }
    
    /**
    Instantiates a YeastDonut object with given flavor.
    @param flavor the YeastDonutFlavor
    */
    public YeastDonut(YeastDonutFlavor flavor) {
        yeastDonutFlavor = flavor;
    }
    
    /**
    Returns a string representation of YeastDonut object.
    @param quantity the quantity of YeastDonuts ordered.
    @return the string representation.
    */
    @Override
    public String toString(int quantity) {
        return "Yeast Donut(" + quantity + ") " + this.yeastDonutFlavor.name();
    }
    
    /**
    Returns the type of this class's object.
    @return the type.
    */
    @Override
    public String getType() {
        return "YeastDonut";
    }
    
    /**
    Returns price of YeastDonut.
    @return the price.
    */
    @Override
    public double itemPrice() { //calculates price of a menu item
        return price;
    }
    
    /**
    Compares two objects
    @param obj to object to compare with.
    @return true if this object and parameter object are the same YeastDonut, false otherwise.
    */
    @Override
    public boolean equals(Object obj) { //works
        if(obj instanceof YeastDonut) {
            YeastDonut donut = (YeastDonut) obj;
            if(this.yeastDonutFlavor == donut.getFlavor()) {
                return true;
            }
            else {
                return false;
            }
        }
        return false;
    }
    
    /**
    Returns the flavor of this YeastDonut object.
    @return YeastDonutFlavor.
    */
    public YeastDonutFlavor getFlavor() {
        return this.yeastDonutFlavor;
    }   
}