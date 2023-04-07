package com.example.project_5;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;

import java.io.File;
import java.io.PrintWriter;
import java.nio.file.FileAlreadyExistsException;
import java.text.DecimalFormat;
import java.util.ArrayList;

/**
 * This class contains methods that allows the user to view all the orders placed so far, make any final changes, and
 * export the order.
 * @author David Fabian, Steven Tan
 */
public class StoreOrderController {
    private StoreFrontController mainController;
    private ArrayList<Order> storeOrdersRef;
    private Order selectedOrder;
    private static final double TOTALNJTAX = 1.06625;
    @FXML
    private ListView<String> orderListView;
    @FXML
    private ComboBox<Integer> orderNumberBox;
    @FXML
    private TextField orderTotal;
    private ObservableList<Integer> orderNumberArray;
    private ObservableList<String> orderedItemsArray;
    /**
     * Sets the main controller to the StoreFrontController
     * @param controller StoreFrontController for which to set this instance's main controller reference to.
     */
    public void setMainController (StoreFrontController controller){
        mainController = controller;
    }
    /**
     * Initializes the total price text field and list view component by setting the default values for each.
     */
    public void initialize() {
        this.orderNumberArray = FXCollections.observableArrayList(new ArrayList<>());
        this.orderedItemsArray = FXCollections.observableArrayList(new ArrayList<>());
        this.orderTotal.setText("$0.00");
    }
    /**
     * Sets the order list view and the text field displaying the price of the order to the first order in the list of
     * all store orders. This method is called immediately after setting the Main Controller containing the reference
     * to all the non-cancelled store orders.
     * Notifies the user if there are no placed orders.
     */
    public void setOrder() {
        this.storeOrdersRef = null;
        if(mainController == null || mainController.storeOrders == null || mainController.storeOrders.isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("NO STORE ORDERS");
            alert.setHeaderText("Store order list is empty!");
            alert.setContentText("Current store order list does not have any items. Please place orders first to " +
                    "utilize this tool.");
            alert.showAndWait();
            return;
        }
        this.storeOrdersRef = mainController.storeOrders;
        ArrayList<Integer> orderNumList = new ArrayList<>();
        for(Order item : this.storeOrdersRef) {
            orderNumList.add(item.orderNumber());
        }
        this.orderNumberArray = FXCollections.observableArrayList(orderNumList);
        this.orderNumberBox.setItems(orderNumberArray);
        this.orderNumberBox.getSelectionModel().select(0);
        this.selectedOrder = this.storeOrdersRef.get(0);
        this.updateDisplayedItems();
        this.updatePrice();
    }

    /**
     * Updates the currently user-viewed order by switching to the store order selected by the user in the Combo box.
     * Updates the order list view and the total price listed in the view according the new selected order.
     */
    @FXML
    protected void changeOrder() {
        if(this.orderNumberArray.isEmpty() || this.orderNumberBox.getSelectionModel().getSelectedItem() == null) {
            return;
        }
        int orderNum = this.orderNumberBox.getSelectionModel().getSelectedItem();
        int findOrderIndex = 0;
        while(findOrderIndex < this.storeOrdersRef.size() &&
                this.storeOrdersRef.get(findOrderIndex).orderNumber() != orderNum) {
            findOrderIndex++;
        }
        if(findOrderIndex < this.storeOrdersRef.size()) {
            this.selectedOrder = this.storeOrdersRef.get(findOrderIndex);
            this.updateDisplayedItems();
            this.updatePrice();
        }
    }

    /**
     * Cancels the currently user-viewed order and removes it from the list of all store orders. It updates the
     * currently user-viewed order by switching to the first order currently in the list of store orders, if any.
     */
    @FXML
    protected void cancelOrder() {
        if(this.selectedOrder == null) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("NO STORE ORDER");
            alert.setHeaderText("No store order selected!");
            alert.setContentText("Please place orders first before utilizing this tool.");
            alert.showAndWait();
            this.selectedOrder = null;
            return;
        }
        this.storeOrdersRef.remove(this.selectedOrder);
        this.orderNumberArray.remove((Integer)this.selectedOrder.orderNumber());
        if(this.storeOrdersRef.isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("NO STORE MORE ORDERS");
            alert.setHeaderText("Store order list now is empty!");
            alert.setContentText("Current store order list does not have any items. Please place orders first to " +
                    "utilize this tool.");
            alert.showAndWait();
            this.selectedOrder = null;
        } else {
            this.selectedOrder = this.storeOrdersRef.get(0);
        }
        this.orderNumberBox.getSelectionModel().select(0);
        this.updateDisplayedItems();
        this.updatePrice();
    }

    /**
     * Creates a text file in the format "Order-(Store Order #).txt" and writes all the item descriptions (that the
     * currently user-viewed order contains) into that text file. Notifies the user of any errors in the file creation
     * and updates the currently user-viewed order by switching to another, if any.
     */
    @FXML
    protected void exportOrder() {
        if(this.selectedOrder == null) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("ORDER ERROR");
            alert.setHeaderText("Cannot Export");
            alert.setContentText("There is no order selected for which to export.");
            alert.showAndWait();
            return;
        }
        String orderName = "Order-" + selectedOrder.orderNumber();
        String fullFileName = orderName + ".txt";
        File newFile = new File(fullFileName);
        int duplicates = 1;
        while(newFile.exists()) {
            fullFileName = orderName + "(" + duplicates + ")" + ".txt";
            newFile = new File(fullFileName);
            duplicates += 1;
        }
        String content = "Store Order # " + selectedOrder.orderNumber() + ":\n";
        for(String itemDesc : orderListView.getItems()) {
            content += itemDesc + "\n";
        }
        try {
            if(!newFile.createNewFile()) {
                throw new FileAlreadyExistsException(newFile.getAbsolutePath());
            }
            PrintWriter outputWriter = new PrintWriter(newFile);
            outputWriter.print(content);
            outputWriter.close();
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("FILE CREATED");
            alert.setHeaderText("New File Was Created");
            alert.setContentText(newFile.getName() + " was created and is located: " + newFile.getAbsolutePath());
            alert.showAndWait();
            this.cancelOrder();
        } catch (Exception e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("FILE ERROR");
            alert.setHeaderText("Writing to File Error");
            alert.setContentText("New file could not be created to export store order.");
            alert.showAndWait();
        }
    }
    /**
     * Updates the displayed items in the order List view. Called the controller is first invoked and whenever the user
     * switches store orders in the Combo box.
     */
    private void updateDisplayedItems() {
        ArrayList<String> itemList = new ArrayList<>();
        if(this.selectedOrder != null) {
            for(MenuItem item : this.selectedOrder.menuList()) {
                itemList.add(item.toString());
            }
        }
        this.orderedItemsArray = FXCollections.observableArrayList(itemList);
        this.orderListView.setItems(orderedItemsArray);
    }
    /**
     * Updates the price listed for the current store order being viewed by the user. If there are no store orders,
     * "$0.00" is set as the text.
     */
    private void updatePrice() {
        if(this.selectedOrder != null) {
            this.orderTotal.setText(DecimalFormat.getCurrencyInstance().format(this.selectedOrder.subTotal() *
                    TOTALNJTAX));
        } else {
            this.orderTotal.setText("$0.00");
        }
    }
}
