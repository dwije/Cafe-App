package cafe;

import java.net.URL;
import java.text.DecimalFormat;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListView;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;

/**
This is the controller for ordering donuts window.
@author Dharma Wijesinghe, Min Sun You
*/
public class OrderingDonutsController implements Initializable {
    
    @FXML
    private RadioButton yeastDonutRadio;
    @FXML
    private RadioButton cakeDonutRadio;
    @FXML
    private RadioButton donutHoleRadio;
    @FXML
    private ComboBox<String> donutFlavorComboBox;
    @FXML
    private TextField numOfDonuts;
    @FXML
    private Button addToOrder;
    @FXML
    private Button removeFromOrder;
    @FXML
    private TextField subTotal;
    @FXML
    private ListView<String> orderList;
    private double subTotalAmount = 0.00;
    private Order tempOrderList;
    private CafeMainController mainController;
    private static DecimalFormat formatObj = new DecimalFormat("###,##0.00");
    
    /**
    Sets the main controller reference.
    @param controller
    */
    public void setCafeMainController(CafeMainController controller) {
        mainController = controller;
    }
    
    /**
    Instantiates this class's object
    Instantiates order, ComboBox and ListView
    */
    public OrderingDonutsController() {
        tempOrderList = new Order();
        donutFlavorComboBox = new ComboBox<String>();
        orderList = new ListView<String>();
    }
    
    
    /**
    Sets the options for the ComboBox depending on which donut type is selected.
    @param event the ActionEvent.
    */
    @FXML
    void setComboBox(ActionEvent event) {
        if(yeastDonutRadio.isSelected()) {
            donutFlavorComboBox.getItems().clear();
            donutFlavorComboBox.getItems().addAll("Cheese", "Cinnamon", "Strawberry");
        } else if(cakeDonutRadio.isSelected()) {
            donutFlavorComboBox.getItems().clear();
            donutFlavorComboBox.getItems().addAll("Blueberry", "Banana", "Mint");
        } else if(donutHoleRadio.isSelected()) {
            donutFlavorComboBox.getItems().clear();
            donutFlavorComboBox.getItems().addAll("Pineapple", "Apple", "Mango");
        }
    }
    
    /**
    Checks the user input from the GUI to ensure all fields are valid.
    @throws Exception
    */
    private void checkInputs() throws Exception {
        if((!yeastDonutRadio.isSelected() && !cakeDonutRadio.isSelected() && !donutHoleRadio.isSelected())
                || numOfDonuts.getText().isEmpty() || donutFlavorComboBox.getValue() == null) {
            throw new Exception("Missing data for adding order.");
        }
        if(Integer.parseInt(numOfDonuts.getText()) <= 0) {
        	throw new Exception("Amount cannot be 0 or negative.");
        }
        Integer.parseInt(numOfDonuts.getText());
    }
    
    /**
    Resets the order list.
    */
    private void resetOrderList() {
    	orderList.getItems().clear();
    	for(int i = 0; i < tempOrderList.getShoppingCart().size(); i++) {
    		MenuItem item = tempOrderList.getShoppingCart().get(i);
    		int amount = tempOrderList.getQuantityArray().get(i);
    		if(item.getType().equals("YeastDonut")) {
    			YeastDonut donut = (YeastDonut) item;
    			if(donut.getFlavor().equals(YeastDonutFlavor.CHEESE)) {
    				orderList.getItems().add("Yeast donut [Cheese] (" + amount + ")");
    			} else if(donut.getFlavor().equals(YeastDonutFlavor.CINNAMON)) {
    				orderList.getItems().add("Yeast donut [Cinnamon] (" + amount + ")");
    			} else if(donut.getFlavor().equals(YeastDonutFlavor.STRAWBERRY)) {
    				orderList.getItems().add("Yeast donut [Strawberry] (" + amount + ")");
    			}
    		} else if(item.getType().equals("CakeDonut")) {
    			CakeDonut donut = (CakeDonut) item;
    			if(donut.getFlavor().equals(CakeDonutFlavor.BANANA)) {
    				orderList.getItems().add("Cake donut [Banana] (" + amount + ")");
    			} else if(donut.getFlavor().equals(CakeDonutFlavor.BLUEBERRY)) {
    				orderList.getItems().add("Cake donut [Blueberry] (" + amount + ")");
    			} else if(donut.getFlavor().equals(CakeDonutFlavor.MINT)) {
    				orderList.getItems().add("Cake donut [Mint] (" + amount + ")");
    			}
    		} else if(item.getType().equals("DonutHole")) {
    			DonutHoles donut = (DonutHoles) item;
    			if(donut.getFlavor().equals(DonutHolesFlavor.APPLE)) {
    				orderList.getItems().add("Donut hole [Apple] (" + amount + ")");
    			} else if(donut.getFlavor().equals(DonutHolesFlavor.MANGO)) {
    				orderList.getItems().add("Donut hole [Mango] (" + amount + ")");
    			} else if(donut.getFlavor().equals(DonutHolesFlavor.PINEAPPLE)) {
    				orderList.getItems().add("Donut hole [Pineapple] (" + amount + ")");
    			}
    		}
    	}
    }
    
    /**
    Adds a donut to an order.
    */
    @FXML
    void addToOrder() {
        try {
            checkInputs();
            int amount = Integer.parseInt(numOfDonuts.getText());
            if(yeastDonutRadio.isSelected()) {
                addYeastDonut(amount);
                subTotalAmount += new YeastDonut().itemPrice() * amount;
                subTotal.setText("$" + formatObj.format(subTotalAmount));
            } else if(cakeDonutRadio.isSelected()) {
                addCakeDonut(amount);
                subTotalAmount += new CakeDonut().itemPrice() * amount;
                subTotal.setText("$" + formatObj.format(subTotalAmount));
            } else if(donutHoleRadio.isSelected()) {
                addDonutHole(amount);
                subTotalAmount += new DonutHoles().itemPrice() * amount;
                subTotal.setText("$" + formatObj.format(subTotalAmount));
            } else throw new Exception("Missing data for adding order.");
            resetOrderList();
        }
        catch(NumberFormatException e) {
            Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Invalid amount!");
            alert.setContentText("Please enter a valid numerical integer for the amount.");
            alert.showAndWait();
        }
        catch (Exception e) {
            Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("Error");
            alert.setContentText(e.getMessage());
            alert.showAndWait();
        }
    }
    
    /**
    This methods creates and adds a YeastDonut object to the temporary Order list.
    @param amount the quantity to add.
    */
    private void addYeastDonut(int amount) {
        if(donutFlavorComboBox.getValue().equals("Cheese")) {
            //orderList.getItems().add("Yeast donut [Cheese] (" + amount + ")");
            YeastDonut yeastDonut = new YeastDonut(YeastDonutFlavor.CHEESE);
            tempOrderList.addOrderAndQuantity(yeastDonut, amount);
        } else if(donutFlavorComboBox.getValue().equals("Cinnamon")) {
            //orderList.getItems().add("Yeast donut [Cinnamon] (" + amount + ")");
            YeastDonut yeastDonut = new YeastDonut(YeastDonutFlavor.CINNAMON);
            tempOrderList.addOrderAndQuantity(yeastDonut, amount);
        } else if(donutFlavorComboBox.getValue().equals("Strawberry")) {
            //orderList.getItems().add("Yeast donut [Cinnamon] (" + amount + ")");
            YeastDonut yeastDonut = new YeastDonut(YeastDonutFlavor.STRAWBERRY);
            tempOrderList.addOrderAndQuantity(yeastDonut, amount);
        }
    }
    
    /**
    This methods creates and adds a CakeDonut object to the temporary Order list.
    @param amount the quantity to add.
    */
    private void addCakeDonut(int amount) {
        if(donutFlavorComboBox.getValue().equals("Blueberry")) {
            //orderList.getItems().add("Cake donut [Blueberry] (" + amount + ")");
            CakeDonut cakeDonut = new CakeDonut(CakeDonutFlavor.BLUEBERRY);
            tempOrderList.addOrderAndQuantity(cakeDonut, amount);
        } else if(donutFlavorComboBox.getValue().equals("Banana")) {
            //orderList.getItems().add("Cake donut [Banana] (" + amount + ")");
            CakeDonut cakeDonut = new CakeDonut(CakeDonutFlavor.BANANA);
            tempOrderList.addOrderAndQuantity(cakeDonut, amount);
        } else if(donutFlavorComboBox.getValue().equals("Mint")) {
            //orderList.getItems().add("Cake donut [Mint] (" + amount + ")");
            CakeDonut cakeDonut = new CakeDonut(CakeDonutFlavor.MINT);
            tempOrderList.addOrderAndQuantity(cakeDonut, amount);
        }
    }
    
    /**
    This methods creates and adds a DonutHoles object to the temporary Order list.
    @param amount the quantity.
    */
    private void addDonutHole(int amount) {
        if(donutFlavorComboBox.getValue().equals("Pineapple")) {
            DonutHoles donutHole = new DonutHoles(DonutHolesFlavor.PINEAPPLE);
            tempOrderList.addOrderAndQuantity(donutHole, amount);
        } else if(donutFlavorComboBox.getValue().equals("Apple")) {
            DonutHoles donutHole = new DonutHoles(DonutHolesFlavor.APPLE);
            tempOrderList.addOrderAndQuantity(donutHole, amount);
        } else if(donutFlavorComboBox.getValue().equals("Mango")) {
            DonutHoles donutHole = new DonutHoles(DonutHolesFlavor.MANGO);
            tempOrderList.addOrderAndQuantity(donutHole, amount);
        }
    }
    
    /**
    This method is invoked from the GUI and deletes the selected item from the ListView.
    @param event the ActionEvent.
    */
    @FXML
    void removeFromOrder(ActionEvent event) {
        if(orderList.getSelectionModel().isEmpty()) {
            Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("No items selected.");
            alert.setContentText("Please select an item for removal.");
            alert.showAndWait();
        } else {
        	MenuItem itemToRemove = tempOrderList.getShoppingCart().get(orderList.getSelectionModel().getSelectedIndex());
            int quantityToRemove = tempOrderList.getQuantityArray().get(orderList.getSelectionModel().getSelectedIndex());
            double removedTotal = getRemovedItemTotal(itemToRemove, quantityToRemove);
            tempOrderList.getShoppingCart().remove(orderList.getSelectionModel().getSelectedIndex());
            tempOrderList.getQuantityArray().remove(orderList.getSelectionModel().getSelectedIndex());
            orderList.getItems().remove(orderList.getSelectionModel().getSelectedIndex());
            subTotalAmount -= removedTotal;
            subTotal.setText("$" + formatObj.format(subTotalAmount));
        }
    }
    
    /**
    Returns the price of removed item.
    @param itemToRemove the MenuItem object to remove
    @param quantityToRemove the quantity to remove.
    @return the price.
    */
    private double getRemovedItemTotal(MenuItem itemToRemove, int quantityToRemove) {
        if(itemToRemove.getType().equals("YeastDonut")) {
            return new YeastDonut().itemPrice() * quantityToRemove;
        } else if(itemToRemove.getType().equals("CakeDonut")) {
            return new CakeDonut().itemPrice() * quantityToRemove;
        } else if(itemToRemove.getType().equals("DonutHole")) {
            return new DonutHoles().itemPrice() * quantityToRemove;
        }
        return 0;
    }
    
    /**
    Adds an item to the shopping cart.
    @param event the ActionEvent.
    */
    @FXML
    void addToShoppingCart(ActionEvent event) {
		if(tempOrderList.getShoppingCart().size() == 0) {
			Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("No Items");
            alert.setHeaderText("No items added.");
            alert.setContentText("You must add items first in order to place them in the shopping cart.");
            alert.showAndWait();
		} else {
			for(int i = 0; i < tempOrderList.getShoppingCart().size(); i++) {
	        	MenuItem item = tempOrderList.getShoppingCart().get(i);
	        	mainController.getOrder().addOrderAndQuantity(item, tempOrderList.getQuantityArray().get(i));
	        }
	        tempOrderList = new Order();
	        subTotalAmount = 0;
	        resetOrderList();
	        resetGUI();
	        Alert alert = new Alert(AlertType.INFORMATION);
	        alert.setTitle("Order Added");
	        alert.setContentText("Order has been added to shopping cart.");
	        alert.showAndWait();
		}
    }
    
    /**
    Resets the GUI page to beginning.
    */
    private void resetGUI() {
    	subTotal.setText("$" + formatObj.format(subTotalAmount));
    	yeastDonutRadio.setSelected(false);
    	cakeDonutRadio.setSelected(false);
    	donutHoleRadio.setSelected(false);
    	numOfDonuts.clear();
    	donutFlavorComboBox.getItems().clear();
    }
    
    /**
    First writes a text to the subTotal text field.
    @param arg0 the URL
    @param arg1 the ResouceBundle
    */
    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        subTotal.setText("$" + formatObj.format(subTotalAmount));
    }
}