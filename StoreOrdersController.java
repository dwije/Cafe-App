package cafe;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;

/**
This is the controller store orders window.
@author Dharma Wijesinghe, Min Sun You
*/
public class StoreOrdersController {
	
	@FXML
	private ListView<String> ordersList;
	@FXML
	private ListView<String> orderDetailsList;
	@FXML
	private TextField orderTotal;
	@FXML
	private Button cancelOrderButton;
	@FXML
	private Button exportOrdersButton;
	private CafeMainController mainController;
	private double orderTotalAmount = 0.00;
    private static DecimalFormat formatObj = new DecimalFormat("###,##0.00");
    
    /**
     * This is the default constructor.
     */
    public StoreOrdersController() {
    	
    }
    
	/**
	Sets the order list.
	*/
	public void setOrderList() {
		for(Order order : mainController.getStoreOrders().getListOfOrders()) {
			ordersList.getItems().add("Order " + Integer.toString(order.getOrderNumber()));
		}
	}
	
	/**
	This method sets the reference to the main controller class.
	@param controller the CafeMainController.
	*/
	public void setMainController(CafeMainController controller) {
		mainController = controller;
	}
	
	/**
	Updates the GUI with selected order.
	*/
	@FXML
	void orderSelected() {
		if(ordersList.getSelectionModel().isEmpty()) {
			orderTotal.clear();
			return;
		}
		orderDetailsList.getItems().clear();
		Order selectedOrder = mainController.getStoreOrders().getListOfOrders().get(ordersList.getSelectionModel().getSelectedIndex());
		for(int i = 0; i < selectedOrder.getShoppingCart().size(); i++) {
			MenuItem item = selectedOrder.getShoppingCart().get(i);
			orderDetailsList.getItems().add(item.toString(selectedOrder.getQuantityArray().get(i)));
		}
		formatObj.setRoundingMode(RoundingMode.DOWN);
		orderTotalAmount = mainController.getStoreOrders().getListOfOrders().get(ordersList.getSelectionModel().getSelectedIndex()).getTotal();
		orderTotal.setText("$" + formatObj.format(orderTotalAmount));
	}
	
	/**
	Cancels an order from store orders.
	*/
	@FXML
	void cancelOrder() {
		if(ordersList.getSelectionModel().isEmpty()) {
			Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle("Error");
			alert.setHeaderText("No order selected.");
			alert.setContentText("Please select an order to cancel.");
			alert.showAndWait();
			return;
		}
		mainController.getStoreOrders().getListOfOrders().remove(ordersList.getSelectionModel().getSelectedIndex());
		ordersList.getItems().remove(ordersList.getSelectionModel().getSelectedIndex());
		if(ordersList.getItems().isEmpty()) {
			orderDetailsList.getItems().clear();
		}
		orderSelected();
	}
	
	/**
	 * Exports the store orders into a text file.
	 * @throws IOException
	 */
	@FXML
	void exportOrders() throws IOException {
		if(ordersList.getItems().isEmpty()) {
			Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle("Order Export");
			alert.setHeaderText("No orders to export.");
			alert.setContentText("The export failed because no orders have been placed.");
			alert.showAndWait();
			return;
		}
		File file = new File("Orders.txt");
		FileWriter fw = new FileWriter(file);
		PrintWriter pw = new PrintWriter(fw);
		for(Order order : mainController.getStoreOrders().getListOfOrders()) {
			pw.println("*Order " + order.getOrderNumber() + "*");
			for(int i = 0; i < order.getShoppingCart().size(); i++) {
				if(order.getShoppingCart().get(i).getType().equals("YeastDonut")) {
					pw.println(((YeastDonut)(order.getShoppingCart().get(i))).toString(order.getQuantityArray().get(i)));
				} else if(order.getShoppingCart().get(i).getType().equals("CakeDonut")) {
					pw.println(((CakeDonut)(order.getShoppingCart().get(i))).toString(order.getQuantityArray().get(i)));
				} else if(order.getShoppingCart().get(i).getType().equals("DonutHole")) {
					pw.println(((DonutHoles)(order.getShoppingCart().get(i))).toString(order.getQuantityArray().get(i)));
				} else if(order.getShoppingCart().get(i).getType().equals("Coffee")) {
					pw.println(((Coffee)(order.getShoppingCart().get(i))).toString(order.getQuantityArray().get(i)));
				}
			}
			pw.println("*End of Order " + order.getOrderNumber() + "*");
			pw.println();
		}
		pw.close();
		Alert alert = new Alert(AlertType.INFORMATION);
		alert.setTitle("Order Export");
		alert.setHeaderText("Export successful.");
		alert.setContentText("The orders have been exported.");
		alert.showAndWait();
	}
}