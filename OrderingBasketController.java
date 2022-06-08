package cafe;

import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.ArrayList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;

/**
This is the controller that controls the ordering basket fxml file.
@author Dharma Wijesinghe, Min Sun You
*/
public class OrderingBasketController {
    
    private static final double SALES_TAX_PERCENT = 6.625; 
    private static DecimalFormat formatObj = new DecimalFormat("###,##0.00");
    private static final int GROUPING_SIZE = 3;
    private static final int PERCENTAGE_DIVISION = 100;
    
    @FXML
    private Button placeOrderButton;

    @FXML
    private Button removeItemButton;

    @FXML
    private ListView<String> shoppingCartList;

    @FXML
    private TextField subTotalText;

    @FXML
    private TextField taxText;

    @FXML
    private TextField totalText;

    private CafeMainController cafeMainController;
    
    /**
    Default constructor for this class.
    */
    public OrderingBasketController() {
        
    }
    
    /**
    Allows connection between the main controller with this controller.
    @param controller the controller to connect with.
    */
    public void setCafeMainController(CafeMainController controller) {
        this.cafeMainController = controller;
    }
    
    /**
    Displays all the menu items added on the window.
    */
    public void showAllMenuItemsAdded() {
        Order currentOrder = cafeMainController.getOrder();
        ArrayList<Integer> correspondingQuantity = currentOrder.getQuantityArray();
        int counter = 0;
        this.shoppingCartList.getItems().clear();
        for(MenuItem currMenuItem: currentOrder.getShoppingCart()) {
            this.shoppingCartList.getItems().add(currMenuItem.toString(correspondingQuantity.get(counter)));
            counter++;
        }
    }
    
    /**
    Displays all the sub-total, tax, and the total price of an order.
    */
    public void showSubTotalAndTaxAndTotal() {
        formatObj.setRoundingMode(RoundingMode.DOWN);
        Order currentOrder = cafeMainController.getOrder();
        ArrayList<Integer> correspondingQuantity = currentOrder.getQuantityArray();
        double subTotalPrice = 0;
        int counter = 0;
        for(MenuItem currMenuItem: currentOrder.getShoppingCart()) {
            subTotalPrice += currMenuItem.itemPrice() * correspondingQuantity.get(counter);
            counter++;
        }
        formatObj.setGroupingUsed(true);
        formatObj.setGroupingSize(GROUPING_SIZE);
        subTotalText.setText("$" + formatObj.format(subTotalPrice) + "");
        double tax = subTotalPrice * (SALES_TAX_PERCENT / PERCENTAGE_DIVISION);
        taxText.setText("$" + formatObj.format(tax));
        double totalAmt = subTotalPrice + tax;
        totalText.setText("$" + formatObj.format(totalAmt));
    }
    
    /**
    Sets sub-total, tax, and total to 0
    Clears the shopping cart list.
    */
    private void resetOrderingBasket() {
        this.shoppingCartList.getItems().clear();
        formatObj.setGroupingUsed(true);
        formatObj.setGroupingSize(GROUPING_SIZE);
        subTotalText.setText("$" + formatObj.format(0) + "");
        taxText.setText("$" + formatObj.format(0));
        totalText.setText("$" + formatObj.format(0));
    }
    
    /**
    Adds the order to store orders.
    Does nothing if no items are in the order.
    @param event the ActionEvent object
    */
    @FXML
    void placeOrderButtonAction(ActionEvent event) {
        try {
            Order currentOrder = cafeMainController.getOrder();
            if(currentOrder.getShoppingCart().size() == 0) {
                throw new Exception();
            }
            StoreOrders cafeStoreOrders = cafeMainController.getStoreOrders();
            cafeStoreOrders.add(cafeMainController.getOrder());
            cafeMainController.resetOrder();
            resetOrderingBasket();
            Alert alert = new Alert(AlertType.INFORMATION);
            alert.setTitle("Thank you!");
            alert.setHeaderText("Your order was placed!");
            alert.setContentText("Your order will come shortly!");
            alert.showAndWait();
        }
        catch(Exception e) {
            Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("Error!");
            alert.setHeaderText("No items in order!");
            alert.setContentText("Please choose a menu item first!");
            alert.showAndWait();
        }
    }
    
    /**
    Removes an item from the order.
    @param event the ActionEvent object
    */
    @FXML
    void removeItemButtonAction(ActionEvent event) {
        try {
            if(shoppingCartList.getItems().isEmpty()) {
                throw new Exception();
            }
            else if(this.shoppingCartList.getSelectionModel().getSelectedIndex() < 0) {
                throw new ArithmeticException();
            }
            Order currentOrder = cafeMainController.getOrder();
            int indexToRemove = this.shoppingCartList.getSelectionModel().getSelectedIndex();
            if(indexToRemove >= 0) {
                this.shoppingCartList.getItems().remove(indexToRemove);
                MenuItem itemToRemove = currentOrder.getShoppingCart().get(indexToRemove);
                currentOrder.remove(itemToRemove);
            } 
            showAllMenuItemsAdded();
            showSubTotalAndTaxAndTotal();
        }
        catch(ArithmeticException e) {
            Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("Error!");
            alert.setHeaderText("No item selected!");
            alert.setContentText("Please choose a menu item first!");
            alert.showAndWait();
        }
        catch(Exception e) {
            Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("Error!");
            alert.setHeaderText("No items in order!");
            alert.setContentText("Please order a menu item first!");
            alert.showAndWait();
        }
    }
}