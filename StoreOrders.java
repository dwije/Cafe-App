package cafe;

import java.util.ArrayList;

/**
This is class represents a StoreOrders object
This object holds a list of orders.
@author Dharma Wijesinghe, Min Sun You
*/
public class StoreOrders implements Customizable { //keeps list of orders place by user
	
	private static final int LOWEST_ORDER_NUMBER = 1;
	
    private ArrayList<Order> listOfOrders;
    
    /**
    Instantiates a StoreOrders object,
    with an ArrayList of orders.
    */
    public StoreOrders() {
        listOfOrders = new ArrayList<Order>();
    }
    
    /**
    Returns this object's list of orders.
    @return the list of orders.
    */
    public ArrayList<Order> getListOfOrders() {
    	return listOfOrders;
    }
    
    /**
    Adds an order object to store orders.
    @param obj the object to add.
    @return true if order object is added, false otherwise.
    */
    public boolean add(Object obj) { //adding orders
        if(obj instanceof Order) {
            listOfOrders.add((Order) obj);
            assignOrderNumber();
            return true;
        }
        return false;
    } //works
    
    /**
    Removes an order object from store orders.
    @param obj the object to remove.
    @return true if order object removed, false otherwise.
    */
    public boolean remove(Object obj) {
        if(obj instanceof Order) {
            Order orderToRemove = (Order) obj;
            int orderNumberToRemove = orderToRemove.getOrderNumber();
            for(int i = 0; i < this.listOfOrders.size(); i++) {
               Order currentOrder = this.listOfOrders.get(i);
               if(currentOrder.getOrderNumber() == orderNumberToRemove) {
                   this.listOfOrders.remove(i);
                   return true;
               }
            }
            return false;
        }
        return false;
    } //works
    
    /**
    This methods is called by the add method to assign the lowest unused order number to the new order.
    */
    private void assignOrderNumber() {
    	if(listOfOrders.size() == 1) {
    		listOfOrders.get(0).setOrderNumber(LOWEST_ORDER_NUMBER);
    		return;
    	}
    	int orderNumber = 1;
    	boolean orderNumberFound = false;
    	while(!orderNumberFound) {
    		orderNumberFound = true;
    		for(Order order : listOfOrders) {
    			if(order.getOrderNumber() == orderNumber) {
    				orderNumberFound = false;
    				break;
    			}
    		}
    		if(orderNumberFound) {
    			break;
    		}
    		orderNumber++;
    	}
    	listOfOrders.get(listOfOrders.size()-1).setOrderNumber(orderNumber);
    	/*
    	for(int i=LOWEST_ORDER_NUMBER; i < listOfOrders.get(listOfOrders.size()-1).getOrderNumber(); i++) {
    		orderNumberFound = true;
    		for(Order order : listOfOrders) {
    			if(order.getOrderNumber() == i) {
    				orderNumberFound = false;
    				break;
    			}
    		}
    		if(orderNumberFound) {
    			listOfOrders.get(listOfOrders.size()-1).setOrderNumber(i);
        		break;
    		}
    	}
    	*/
    }
}