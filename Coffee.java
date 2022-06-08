package cafe;

import java.util.ArrayList;

/**
This is the Coffee class describing a coffee.
Each coffee has addins and a size.
@author Dharma Wijesinghe, Min Sun You
*/
public class Coffee extends MenuItem implements Customizable{
    
    //are we allowed to add instance variables and methods?
    
    //constants
    private static final double PRICE_SHORT_COFFEE = 1.69;
    private static final double ADD_IN_PRICE = 0.30;
    private static final double SIZE_INCREMENT_PRICE = 0.40;
    private ArrayList<CoffeeAddIns> addIns; //holds all add ins for a coffee obj
    private CoffeeSize coffeeSize;
    
    /**
    Creates a coffee object.
    ArrayList holds the add ins.
    */
    public Coffee() {
        addIns = new ArrayList<CoffeeAddIns>();
    }
    
    /**
    Returns the type of object, Coffee.
    @return the type of this object.
    */
    @Override
    public String getType() {
        return "Coffee";
    }
    
    /**
    Returns a string representation of the add ins.
    @return the string representation.
    */
    public String addInsToString() {
        String output = "[";
        for(int i = 0; i < this.addIns.size(); i++) {
            String currentAddIn = addIns.get(i).name();
            if(i != this.addIns.size() - 1) {
                output += currentAddIn + ", ";
            }
            else {
                output += currentAddIn;
            }
        }
        output += "]";
        return output;
    }
    
    /**
    Returns string representation of Coffee objects.
    @param quantity the quantity ordered.
    @return the string representation of Coffee objects.
    */
    @Override
    public String toString(int quantity) {
        return "Coffee(" + quantity + ") " + this.coffeeSize + " " + addInsToString();
    }
    
    /**
    Compares two objects to see if they're the same coffee objects.
    @param obj the object to compare with.
    @return true if they're equal, false otherwise.
    */
    @Override
    public boolean equals(Object obj) { //works
        if(obj instanceof Coffee) {
            Coffee coffee = (Coffee) obj;
            if(this.coffeeSize == coffee.coffeeSize) {
                if(this.addIns.size() == coffee.addIns.size()) {
                    if(addIns.size() == 0) { //if no addins, they both have none
                        return true;
                    }
                    for(int i = 0; i < this.addIns.size(); i++) { //since sizes are equal, see if elements are equal
                        CoffeeAddIns currentAddIns = this.addIns.get(i);
                        boolean exists = false;
                        for(int j = 0; j < coffee.addIns.size(); j++) {
                            CoffeeAddIns paramCurrentAddIn = coffee.addIns.get(j);
                            if(currentAddIns == paramCurrentAddIn) {
                                exists = true;
                                break;
                            }
                        }
                        if(!exists) {
                            return false;
                        }
                    }
                    return true;
                }
            }
        }
        return false;
    }
    
    /**
    Returns the price of a coffee object.
    Price is based on add ins and size.
    @return the price.
    */
    @Override
    public double itemPrice() { //works
        double price = PRICE_SHORT_COFFEE; 
        price = price + this.addIns.size() * ADD_IN_PRICE;
        price = price + (SIZE_INCREMENT_PRICE * this.coffeeSize.getMultiplier()); 
        return price;
    }
    
    /**
    Adds an add in to a current coffee object.
    @param obj the add in object.
    @return true if add in was successfully added, false otherwise.
    */
    public boolean add(Object obj) { //customizable interface to add/remove add-ins
        if(obj instanceof CoffeeAddIns) {
            CoffeeAddIns addObj = (CoffeeAddIns) obj;
            this.addIns.add(addObj);
            return true;
        }
        return false;
    } //works
    
    /**
    Removes an add in from a coffee object.
    @param obj the name of the object to remove.
    @return true if add in object was removed, false otherwise.
    */
    public boolean remove(Object obj) {
        if(obj instanceof CoffeeAddIns) {
            CoffeeAddIns objToRemove = (CoffeeAddIns) obj;
            for(int i = 0; i < this.addIns.size(); i++) {
                CoffeeAddIns currentAddIn = this.addIns.get(i);
                if(currentAddIn == objToRemove) {
                    this.addIns.remove(i);
                    return true;
                }
            }
        }
        return false;
    } //works
    
    /**
    Changes the size of the coffee object.
    @param obj the size object to change to.
    @return true if size was changed, false otherwise.
    */
    public boolean changeSize(Object obj) {
        if(obj instanceof CoffeeSize) {
            this.coffeeSize = (CoffeeSize) obj;
            return true;
        }
        return false;
    } //works     
}