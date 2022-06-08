package cafe;

import java.io.IOException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
This is the controller for CafeMain page.
Provides option to go to four other windows.
@author Dharma Wijesinghe, Min Sun You
*/
public class CafeMainController {
    private static final int WIDTH = 600;
    private static final int HEIGHT = 400;
    private Order order;
    private StoreOrders storeOrders;
   
    /**
    Instantiates a CafeMainController object.
    Instantiates an Order and StoreOrders Object.
    */
    public CafeMainController() {
        this.order = new Order();
        this.storeOrders = new StoreOrders();
    }
    
    /**
    Resets the current Order object.
    */
    public void resetOrder() {
        order = new Order();
    }
    
    /**
    Returns the current order object.
    @return the current Order object.
    */
    public Order getOrder() {
        return order;
    }
    
    /**
    Returns a StoreOrders object
    @return the current StoreOrders object.
    */
    public StoreOrders getStoreOrders() {
        return storeOrders;
    }
    
    /**
    Opens an ordering coffee window.
    Users order coffee in this window.
    @param event an ActionEvent object.
    */
    @FXML
    void orderCoffeeButton(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("OrderingCoffee.fxml"));
        BorderPane root = (BorderPane) loader.load(); //if BorderPane is the root Node
        Scene newScene = new Scene(root, WIDTH, HEIGHT);
        //create a new stage (second window)
        Stage newStage = new Stage();
        newStage.setTitle("Ordering Coffee Window");
        newStage.initModality(Modality.APPLICATION_MODAL);
        newStage.setScene(newScene);
        newStage.show();
        OrderingCoffeeController orderingCoffeeController = loader.getController();
        orderingCoffeeController.setCafeMainController(this);
    }
    
    /**
    Opens an ordering donuts window.
    Allows users to order donuts.
    @param event an ActionEvent object.
    */
    @FXML
    void orderDonutsButton(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("OrderingDonuts.fxml"));
        BorderPane root = (BorderPane) loader.load(); //if BorderPane is the root Node
        Scene newScene = new Scene(root, WIDTH, HEIGHT);
        //create a new stage (second window)
        Stage newStage = new Stage();
        newStage.setTitle("Ordering Donuts Window");
        newStage.initModality(Modality.APPLICATION_MODAL);
        newStage.setScene(newScene);
        newStage.show();
        OrderingDonutsController orderingDonutsController = loader.getController();
        orderingDonutsController.setCafeMainController(this);
        
    }
    
    /**
    Opens an ordering basket window.
    Holds all the current menu items.
    @param event an ActionEvent object
    */
    @FXML
    void orderingBasketButton(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("OrderingBasket.fxml"));
        AnchorPane root = (AnchorPane) loader.load(); //if BorderPane is the root Node
        Scene newScene = new Scene(root, WIDTH, HEIGHT);
        //create a new stage (second window)
        Stage newStage = new Stage();
        newStage.setTitle("Ordering Basket Window");
        newStage.initModality(Modality.APPLICATION_MODAL);
        newStage.setScene(newScene);
        newStage.show();
        OrderingBasketController orderingBasketController = loader.getController();
        orderingBasketController.setCafeMainController(this);
        orderingBasketController.showAllMenuItemsAdded();
        orderingBasketController.showSubTotalAndTaxAndTotal();
    }
    
    /**
    Opens a store order window.
    This is a window that holds all store orders.
    @param event an ActionEvent object.
    */
    @FXML
    void storeOrdersButton(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("StoreOrders.fxml"));
        AnchorPane root = (AnchorPane) loader.load(); //if BorderPane is the root Node
        Scene newScene = new Scene(root, WIDTH, HEIGHT);
        Stage newStage = new Stage();
        newStage.setTitle("Store Orders Window");
        newStage.initModality(Modality.APPLICATION_MODAL);
        newStage.setScene(newScene);
        newStage.show();
        StoreOrdersController storeOrdersController = loader.getController();
        storeOrdersController.setMainController(this);
        storeOrdersController.setOrderList();
    }
}