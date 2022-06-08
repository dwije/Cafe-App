package cafe;

/**
This class describes donut holes.
Each donut hole has their own flavor.
@author Dharma Wijesinghe, Min Sun You
*/
public class DonutHoles extends MenuItem{
    
    private final double price = 0.39;
    private DonutHolesFlavor donutHolesFlavor;
    
    /**
    Default constructor for donut holes.
    */
    public DonutHoles() {
        
    }
    
    /**
    Instantiates a donut hole object with given flavor.
    @param flavor the given flavor.
    */
    public DonutHoles(DonutHolesFlavor flavor) {
        this.donutHolesFlavor = flavor;
    }
    
    /**
    Returns a string representation of a DonutHoles object.
    @param quantity the quantity of donut holes ordered.
    @return the string representation.
    */
    @Override
    public String toString(int quantity) {
        return "Donut Holes(" + quantity + ") " + this.donutHolesFlavor.name();
    }
    
    /**
    Returns the type of donut object.
    @return the type of donut object.
    */
    @Override
    public String getType() {
        return "DonutHole";
    }
    
    /**
    Returns the price a donut hole.
    @return the price.
    */
    @Override
    public double itemPrice() { //calculates price of a menu item
        return price;
    }
    
    /**
    Compares two objects to see if they're the same donut hole object.
    @param obj the object to compare with.
    @return true if they have the same content, false otherwise.
    */
    @Override
    public boolean equals(Object obj) { //works
        if(obj instanceof DonutHoles) {
            DonutHoles donut = (DonutHoles) obj;
            if(this.donutHolesFlavor == donut.getFlavor()){
                return true;
            }
            else {
                return false;
            }
        }
        return false;
    }
    
    /**
    Returns the flavor of this donut hole.
    @return the flavor.
    */
    public DonutHolesFlavor getFlavor() {
        return this.donutHolesFlavor;
    }   
}