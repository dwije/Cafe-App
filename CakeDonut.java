package cafe;

/**
Describes a cake donut object.
Each object has a flavor associated with it.
@author Dharma Wijesinghe, Min Sun You
*/
public class CakeDonut extends MenuItem {
    private final double price = 1.79;
    private CakeDonutFlavor cakeDonutFlavor;
    
    /**
    Default constructor for cake donut.
    */
    public CakeDonut() {
        
    }
    
    /**
    Creates a CakeDonut object with the given flavor.
    @param flavor the associated flavor
    */
    public CakeDonut(CakeDonutFlavor flavor) {
        this.cakeDonutFlavor = flavor;
    }
    
    /**
    Returns a string representation of a cake donut object.
    @param quantity the quantity of the cake donut object ordered.
    @return a string representation.
    */
    @Override
    public String toString(int quantity) {
        return "Cake Donut(" + quantity + ") " + this.cakeDonutFlavor.name();
    }
    
    /**
    Returns the type of Cake Donut Object.
    @return the type of donut.
    */
    @Override
    public String getType() {
        return "CakeDonut";
    }
    
    /**
    Returns the price of a cake donut.
    @return the price of the cake donut.
    */
    @Override
    public double itemPrice() { //calculates price of a menu item
        return price;
    }
    
    /**
    Checks to see if this cake donut object equals the paramter object.
    @param obj the object to compare to.
    @return true if they're equal, false otherwise.
    */
    @Override
    public boolean equals(Object obj) { //works
        if(obj instanceof CakeDonut) {
            CakeDonut donut = (CakeDonut) obj;
            if(this.cakeDonutFlavor == donut.getFlavor()){
                return true;
            }
            else {
                return false;
            }
        }
        return false;
    }
    
    /**
    Returns the flavor of the current cake donut object.
    @return the associated flavor.
    */
    public CakeDonutFlavor getFlavor() {
        return this.cakeDonutFlavor;
    }
}
