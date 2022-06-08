package cafe;

import javafx.event.ActionEvent;
import java.net.URL;
import java.text.DecimalFormat;
import java.util.ResourceBundle;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;

/**
This is the controller for the ordering coffee window.
@author Dharma Wijesinghe, Min Sun You
*/
public class OrderingCoffeeController implements Initializable {
    
    private static DecimalFormat formatObj = new DecimalFormat("###,##0.00");
    
    private static final int GROUPING_SIZE = 3;
 
    private int currentValue = 1;
    
    @FXML
    private CheckBox caramelAddOn;

    @FXML
    private Button coffeeAddToOrder;

    @FXML
    private ToggleGroup coffeeCupSize;

    @FXML
    private TextField coffeeSubTotal;

    @FXML
    private CheckBox creamAddOn;

    @FXML
    private RadioButton grandeCupSize;

    @FXML
    private CheckBox milkAddOn;

    @FXML
    private RadioButton shortCupSize;

    @FXML
    private CheckBox syrupAddOn;

    @FXML
    private RadioButton tallCupSize;

    @FXML
    private RadioButton ventiCupSize;

    @FXML
    private CheckBox whippedCreamAddOn;
    
    @FXML
    private TextField quantityText;
    
    @FXML
    private Button lowerButtonID;
    
    @FXML
    private Button higherButtonID;
    
    private Coffee currentCoffeeOrder;

    private CafeMainController cafeMainController;
    
    /**
    Connects the main controller to this controller.
    @param controller the controller to connect with.
    */
    public void setCafeMainController(CafeMainController controller) {
        this.cafeMainController = controller;
    }

    /**
    Instantiates this class's object with a new Coffee object.
    */
    public OrderingCoffeeController() {
        currentCoffeeOrder = new Coffee();
    }
    
    /**
    Sets the sub total text and quantity text to 0.
    @param arg0 the URL
    @param arg1 the ResourceBundle
    */
    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        formatObj.setGroupingUsed(true);
        formatObj.setGroupingSize(GROUPING_SIZE);
        coffeeSubTotal.setText("$" + formatObj.format(0) + "");
        quantityText.setDisable(false);
        quantityText.setText("1");
    }
    
    /**
    Enables buttons.
    */
    private void enableOtherButtons() {
        creamAddOn.setDisable(false);
        syrupAddOn.setDisable(false);
        milkAddOn.setDisable(false);
        caramelAddOn.setDisable(false);
        whippedCreamAddOn.setDisable(false);
        lowerButtonID.setDisable(false);
        higherButtonID.setDisable(false);
        coffeeAddToOrder.setDisable(false);
    }
    
    /**
    Disables buttons.
    */
    private void disableOtherButtons() {
        creamAddOn.setDisable(true);
        syrupAddOn.setDisable(true);
        milkAddOn.setDisable(true);
        caramelAddOn.setDisable(true);
        whippedCreamAddOn.setDisable(true);
        lowerButtonID.setDisable(true);
        higherButtonID.setDisable(true);
        coffeeAddToOrder.setDisable(true);
    }
    
    /**
    Changes the sub-total price depending on which size chosen.
    @param event the ActionEvent.
    */
    @FXML
    void cupSizeSelected(ActionEvent event) {
        if(shortCupSize.isSelected()) {
            this.currentCoffeeOrder.changeSize(CoffeeSize.SHORT);
        }
        else if(tallCupSize.isSelected()) {
            this.currentCoffeeOrder.changeSize(CoffeeSize.TALL);
        }
        else if(grandeCupSize.isSelected()) {
            this.currentCoffeeOrder.changeSize(CoffeeSize.GRANDE);
        }
        else if(ventiCupSize.isSelected()) {
            this.currentCoffeeOrder.changeSize(CoffeeSize.VENTI);
        }
        enableOtherButtons();
        formatObj.setGroupingUsed(true);
        formatObj.setGroupingSize(GROUPING_SIZE);
        coffeeSubTotal.setText("$" + formatObj.format(currentCoffeeOrder.itemPrice() * currentValue) + "");        
    }
    
    /**
    Changes the sub-total price if creamButton is selected.
    Throws and handles exception if user didn't choose size first.
    @param event the ActionEvent
    */
    @FXML
    void creamButton(ActionEvent event) {
        if (creamAddOn.isSelected()) {
            currentCoffeeOrder.add(CoffeeAddIns.CREAM);
        } else {
            currentCoffeeOrder.remove(CoffeeAddIns.CREAM);
        }
        formatObj.setGroupingUsed(true);
        formatObj.setGroupingSize(GROUPING_SIZE);
        coffeeSubTotal.setText("$" + formatObj.format(currentCoffeeOrder.itemPrice() * currentValue) + "");
    }
    
    /**
    Changes sub-total price if milk button is selected or not.
    Throws and handles exception if size not chosen first.
    @param event the ActionEvent.
    */
    @FXML
    void milkButton(ActionEvent event) {
        if (milkAddOn.isSelected()) {
            currentCoffeeOrder.add(CoffeeAddIns.MILK);
        } else {
            currentCoffeeOrder.remove(CoffeeAddIns.MILK);
        }
        formatObj.setGroupingUsed(true);
        formatObj.setGroupingSize(GROUPING_SIZE);
        coffeeSubTotal.setText("$" + formatObj.format(currentCoffeeOrder.itemPrice() * currentValue) + "");
    }
    
    /**
    Changes sub-total price if syrup button is selected or not.
    Throws and handles exception if size not chosen first.
    @param event the ActionEvent.
    */
    @FXML
    void syrupButton(ActionEvent event) {
        if (syrupAddOn.isSelected()) {
            currentCoffeeOrder.add(CoffeeAddIns.SYRUP);
        } else {
            currentCoffeeOrder.remove(CoffeeAddIns.SYRUP);
        }
        formatObj.setGroupingUsed(true);
        formatObj.setGroupingSize(GROUPING_SIZE);
        coffeeSubTotal.setText("$" + formatObj.format(currentCoffeeOrder.itemPrice() * currentValue) + "");
    }
    
    /**
    Changes sub-total price if caramel button is selected or not.
    Throws and handles exception if size not chosen first.
    @param event the ActionEvent.
    */
    @FXML
    void caramelButton(ActionEvent event) {
        if (caramelAddOn.isSelected()) {
            currentCoffeeOrder.add(CoffeeAddIns.CARAMEL);
        } else {
            currentCoffeeOrder.remove(CoffeeAddIns.CARAMEL);
        }
        formatObj.setGroupingUsed(true);
        formatObj.setGroupingSize(GROUPING_SIZE);
        coffeeSubTotal.setText("$" + formatObj.format(currentCoffeeOrder.itemPrice() * currentValue) + "");
    }
    
    /**
    Changes sub-total price if whipped cream button is selected or not.
    Throws and handles exception if size not chosen first.
    @param event the ActionEvent.
    */
    @FXML
    void whippedCreamButton(ActionEvent event) {
        if (whippedCreamAddOn.isSelected()) {
            currentCoffeeOrder.add(CoffeeAddIns.WHIPPEDCREAM);
        } else {
            currentCoffeeOrder.remove(CoffeeAddIns.WHIPPEDCREAM);
        }
        formatObj.setGroupingUsed(true);
        formatObj.setGroupingSize(GROUPING_SIZE);
        coffeeSubTotal.setText("$" + formatObj.format(currentCoffeeOrder.itemPrice() * currentValue) + "");
    }
   
    /**
    Increases quantity by 1.
    @param event the ActionEvent.
    */
    @FXML
    void higherButton(ActionEvent event) {
        currentValue += 1;
        quantityText.setText(currentValue + "");
        formatObj.setGroupingUsed(true);
        formatObj.setGroupingSize(GROUPING_SIZE);
        coffeeSubTotal.setText("$" + formatObj.format(currentCoffeeOrder.itemPrice() * currentValue) + "");
    }
    
    /**
    Decreases the quantity by 1.
    Throws exception if quantity is less than 1.
    @param event the ActionEvent.
    */
    @FXML
    void lowerButton(ActionEvent event) {
        try{
            if(currentValue - 1 < 1) {
                throw new ArithmeticException();
            }
            currentValue -= 1;
            quantityText.setText(currentValue + "");
            formatObj.setGroupingUsed(true);
            formatObj.setGroupingSize(GROUPING_SIZE);
            coffeeSubTotal.setText("$" + formatObj.format(currentCoffeeOrder.itemPrice() * currentValue) + "");        
        }
        catch(ArithmeticException e) {
            Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("Error.");
            alert.setHeaderText("Can't have negative or zero quantity!");
            alert.setContentText("We accept at least 1 coffee!");
            alert.showAndWait();            
        }
    }
    
    /**
    Deselects all the buttons for resetting.
    */
    private void deselectAllButtons() {
        shortCupSize.setSelected(false);
        tallCupSize.setSelected(false);
        grandeCupSize.setSelected(false);
        ventiCupSize.setSelected(false);
        creamAddOn.setSelected(false);
        syrupAddOn.setSelected(false);
        milkAddOn.setSelected(false);
        caramelAddOn.setSelected(false);
        whippedCreamAddOn.setSelected(false);
    }
    
    /**
    Creates a confirmation alert that order was placed
    Resets sub-total to 0.
    Resets the coffee object.
    */
    private void afterEveryCoffeeOrder() {
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle("Added to Order!");
        alert.setHeaderText("Your coffee was added to the order.");
        alert.setContentText("Thank you!");
        alert.showAndWait();
        deselectAllButtons();
        disableOtherButtons();
        this.cafeMainController.getOrder().addOrderAndQuantity(currentCoffeeOrder, Integer.parseInt(quantityText.getText()));
        this.currentCoffeeOrder = new Coffee();
        this.currentValue = 1;
        quantityText.setText(currentValue + "");
        formatObj.setGroupingUsed(true);
        formatObj.setGroupingSize(GROUPING_SIZE);
        coffeeSubTotal.setText("$" + formatObj.format(0) + "");        
    }
    
    /**
    Handles event when user clicks button to add coffee to order.
    Calls afterEveryCoffeeOrder() method
    Makes sure the size is selected first, 
    and quantity is greater than or equal to 1.
    @param event the ActionEvent.
    */
    @FXML
    void addCoffeeOrderButton(ActionEvent event) {
        try {
            if(Integer.parseInt(quantityText.getText()) < 1) {
                throw new ArithmeticException();
            }
            afterEveryCoffeeOrder();
        }
        catch(ArithmeticException e) {
            Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("Error.");
            alert.setHeaderText("Can't have negative or zero quantity.!");
            alert.setContentText("We accept at least 1 coffee!");
            alert.showAndWait();  
        }  
    }
}