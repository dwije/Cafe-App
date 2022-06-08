package cafe;

import java.util.ArrayList;

/**
This is the order class that represents an order.
Holds a unique order number, the menu items, and the quantity of each.
@author Dharma Wijesinghe, Min Sun You
*/
public class Order implements Customizable { //keeps list of menu items (donuts and coffee) added by the user
    
    private int orderNumber;    
    private ArrayList<MenuItem> shoppingCart;
    private ArrayList<Integer> quantity;
    private static final double TAX_MULTIPLIER = 1.06625;
    
    /**
    Constructor for order object.
    */
    public Order() {
        this.shoppingCart = new ArrayList<MenuItem>();
        this.quantity = new ArrayList<Integer>();
    }
    
    /**
    Returns the order number of an order.
    @return the order number.
    */
    public int getOrderNumber() {
        return orderNumber;
    }
    
    /**
    Returns the arraylist of menu items.
    @param number the order number.
    */
    public void setOrderNumber(int number) {
    	orderNumber = number;
    }
    
    /** 
    Returns the shopping cart array list.
    @return the shopping cart
    */
    public ArrayList<MenuItem> getShoppingCart() {
        return shoppingCart;
    }
    
    /**
    Returns the quantity of a menu item.
    @return the array list of quantities.
    */
    public ArrayList<Integer> getQuantityArray() {
        return quantity;
    }
    
    /**
    Sets the quantity of an order.
    @param index the index to put the quantity.
    @param amount the quantity.
    */
    public void setQuantity(int index, int amount) {
        quantity.set(index, amount);
    }
    
    /**
     * This method returns the combined price total of all the items in the order.
     * @return The total price.
     */
    public double getTotal() {
    	double total = 0;
    	for(int i = 0; i < shoppingCart.size(); i++) {
    		if(shoppingCart.get(i) instanceof YeastDonut) {
    			total += ((YeastDonut)(shoppingCart.get(i))).itemPrice() * quantity.get(i);
    		} else if(shoppingCart.get(i) instanceof CakeDonut) {
    			total += ((CakeDonut)(shoppingCart.get(i))).itemPrice() * quantity.get(i);
    		} else if(shoppingCart.get(i) instanceof DonutHoles) {
    			total += ((DonutHoles)(shoppingCart.get(i))).itemPrice() * quantity.get(i);
    		} else if(shoppingCart.get(i) instanceof Coffee) {
    			total += ((Coffee)(shoppingCart.get(i))).itemPrice() * quantity.get(i);
    		}
    	}
    	total *= TAX_MULTIPLIER;
    	return total;
    }
    
    /**
    Uses the add method to add a menu item to an order with quantity.
    @param obj the menu item to add.
    @param amount the amount of menu item
    @return true if menu item successfully added, false otherwise.
    */
    public boolean addOrderAndQuantity(Object obj, int amount) {
        if(add(obj)) {
            quantity.add(amount);
            checkOrderForRepeats();
            return true;
        } else return false;
    }
    
    /**
    Adds a menu item to an order.
    @param obj the menu item object to add.
    @return true if menu item added successfully.
    */
    @Override
    public boolean add(Object obj) { //adding menu items
        if(obj instanceof YeastDonut) {
            shoppingCart.add((YeastDonut) obj);
        }
        else if(obj instanceof CakeDonut) {
            shoppingCart.add((CakeDonut) obj);
        }
        else if(obj instanceof DonutHoles) {
            shoppingCart.add((DonutHoles) obj);
        }
        else if(obj instanceof Coffee) {
            shoppingCart.add((Coffee) obj);
        }
        else {
            return false;
        }
        return true;
    }
    
    /**
    Removes an item from shoppingCart and quantity arraylists.
    @param j the index
    */
    private void cartAndQuantityRemove(int j) {
        shoppingCart.remove(j);
        quantity.remove(j);
    }
    
    /**
    This method checks to see if there are any repeat menu items in the order,
    and combines them if there are. The quantities of the two are added together and
    the repeat item is deleted.
    */
    private void checkOrderForRepeats() {
    	if(shoppingCart.size() <= 1) {
    		return;
    	}
        for(int i = 0; i < shoppingCart.size() - 1; i++) {
            for(int j = i + 1; j < shoppingCart.size(); j++) {
                if(shoppingCart.get(i).getType().equals(shoppingCart.get(j).getType())) {
                    if(shoppingCart.get(i).getType().equals("Coffee")) {
                        if(((Coffee) shoppingCart.get(i)).equals((Coffee) shoppingCart.get(j))) {
                            quantity.set(i, quantity.get(i) + quantity.get(j));
                            cartAndQuantityRemove(j);
                        }
                    } else if(shoppingCart.get(i).getType().equals("YeastDonut")) {
                        YeastDonut iIndexDonut = (YeastDonut)shoppingCart.get(i);
                        YeastDonut jIndexDonut = (YeastDonut)shoppingCart.get(j);
                        if(iIndexDonut.equals(jIndexDonut)) {
                            quantity.set(i, quantity.get(i) + quantity.get(j));
                            cartAndQuantityRemove(j);
                        }
                    } else if(shoppingCart.get(i).getType().equals("CakeDonut")) {
                        CakeDonut iIndexDonut = (CakeDonut)shoppingCart.get(i);
                        CakeDonut jIndexDonut = (CakeDonut)shoppingCart.get(j);
                        if(iIndexDonut.equals(jIndexDonut)) {
                            quantity.set(i, quantity.get(i) + quantity.get(j));
                            cartAndQuantityRemove(j);
                        }
                    } else if(shoppingCart.get(i).getType().equals("DonutHole")) {
                        DonutHoles iIndexDonut = (DonutHoles)shoppingCart.get(i);
                        DonutHoles jIndexDonut = (DonutHoles)shoppingCart.get(j);
                        if(iIndexDonut.equals(jIndexDonut)) {
                            quantity.set(i, quantity.get(i) + quantity.get(j));
                            cartAndQuantityRemove(j);
                        }
                    }
                }
            }
        }
    }
    
    /**
    Removes yeast donut from an order.
    @param obj the object to remove.
    @return true if object was removed, false otherwise.
    */
    private boolean removeYeastDonut(Object obj) {
        YeastDonut donutToRemove = (YeastDonut) obj;
        for(int i = 0; i < shoppingCart.size(); i++) {
            if(shoppingCart.get(i) instanceof YeastDonut) {
                YeastDonut currentDonut = ((YeastDonut) shoppingCart.get(i));
                if(currentDonut.equals(donutToRemove)) {
                    shoppingCart.remove(i);
                    quantity.remove(i);
                    return true;
                }
            }
        }
        return false;
    }
    
    /**
    Removes cake donut from an order.
    @param obj the object to remove.
    @return true if object was removed, false otherwise.
    */
    private boolean removeCakeDonuts(Object obj) {
        CakeDonut donutToRemove = (CakeDonut) obj;
        for(int i = 0; i < shoppingCart.size(); i++) {
            if(shoppingCart.get(i) instanceof CakeDonut) {
                CakeDonut currentDonut = ((CakeDonut) shoppingCart.get(i));
                if(currentDonut.equals(donutToRemove)) {
                    shoppingCart.remove(i);
                    quantity.remove(i);
                    return true;
                }
            }
        }
        return false;
    }
    
    /**
    Removes donut holes from an order.
    @param obj the object to remove.
    @return true if object was removed, false otherwise.
    */
    private boolean removeDonutHoles(Object obj) {
        DonutHoles donutToRemove = (DonutHoles) obj;
        for(int i = 0; i < shoppingCart.size(); i++) {
            if(shoppingCart.get(i) instanceof DonutHoles) {
                DonutHoles currentDonut = ((DonutHoles) shoppingCart.get(i));
                if(currentDonut.equals(donutToRemove)) {
                    shoppingCart.remove(i);
                    quantity.remove(i);
                    return true;
                }
            }               
        }
        return false;
    }
    
    /**
    Removes coffee from an order.
    @param obj the object to remove.
    @return true if object was removed, false otherwise.
    */
    private boolean removeCoffee(Object obj) {
        Coffee coffeeToRemove = (Coffee) obj;
        for(int i = 0; i < shoppingCart.size(); i++) {
            if(shoppingCart.get(i) instanceof Coffee) {
                Coffee currentCoffee = ((Coffee) shoppingCart.get(i));
                if(currentCoffee.equals(coffeeToRemove)) {
                    shoppingCart.remove(i);
                    quantity.remove(i);
                    return true;
                }
            }
        }
        return false;
    }
    
    /**
    Removes a menu item from an order.
    @param obj the menu item to remove.
    @return true if menu item is removed, false otherwise.
    */
    @Override
    public boolean remove(Object obj) { //works
        if(obj instanceof YeastDonut) {
            return removeYeastDonut(obj);
        }
        else if(obj instanceof CakeDonut) {
            return removeCakeDonuts(obj);
        }
        else if(obj instanceof DonutHoles) {
            return removeDonutHoles(obj);
        }
        else if(obj instanceof Coffee) {
            return removeCoffee(obj);
        }
        else {
            return false;
        }
    }   
}