package com.example.project_5;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;

/**
 * This class contains methods that replicate a main menu that provides 4 navigation options (Image Buttons):
 * ordering donuts, ordering coffee, the ordering basket (before placing the order), and the store orders
 * (the orders that have been placed.)
 * @author David Fabian, Steven Tan
 */
public class StoreFrontController {
    public Order currentOrder;
    public ArrayList<Order> storeOrders;

    /**
     * Creates a new view for the user and initializes it with the methods in the DonutController class that sets
     * the default values for the user to begin interacting with the view.
     */
    @FXML
    protected void donutView() {
        Stage view1 = new Stage();
        AnchorPane root;
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("donutView.fxml"));
            root = loader.load();
            Scene scene = new Scene(root, 832, 600);
            view1.setScene(scene);
            view1.show();
            DonutController view1controller = loader.getController();
            view1controller.setMainController(this);
        } catch (IOException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("ERROR");
            alert.setHeaderText("Loading View1.fxml.");
            alert.setContentText("Couldn't load View1.fxml.");
            alert.showAndWait();
        }
    }
    /**
     * Creates a new view for the user and initializes it with the methods in the OrderController class that sets
     * the default values for the user to begin interacting with the view. Sends a reference to the currentOrder
     * object that contains the order that the view would display the details of and modify.
     */
    @FXML
    protected void orderView() {
        Stage view1 = new Stage();
        AnchorPane root;
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("orderView.fxml"));
            root = loader.load();
            Scene scene = new Scene(root, 832, 600);
            view1.setScene(scene);
            view1.show();
            OrderController view1controller = loader.getController();
            view1controller.setMainController(this);
            view1controller.setOrderedItems();
        } catch (IOException e) {
            e.getCause().printStackTrace();
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("ERROR");
            alert.setHeaderText("Loading View1.fxml.");
            alert.setContentText("Couldn't load View1.fxml.");
            alert.showAndWait();
        }
    }
    /**
     * Creates a new view for the user and initializes it with the methods in the StoreOrderController class that sets
     * the default values for the user to begin interacting with the view. Sends a reference to the storeOrders
     * arraylist that the view will display the details of and modify.
     */
    @FXML
    protected void storeOrderView() {
        Stage view1 = new Stage();
        AnchorPane root;
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("storeOrderView.fxml"));
            root = loader.load();
            Scene scene = new Scene(root, 832, 600);
            view1.setScene(scene);
            view1.show();
            StoreOrderController view1controller = loader.getController();
            view1controller.setMainController(this);
            view1controller.setOrder();
        } catch (IOException e) {
            e.getCause().printStackTrace();
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("ERROR");
            alert.setHeaderText("Loading View1.fxml.");
            alert.setContentText("Couldn't load View1.fxml.");
            alert.showAndWait();
        }
    }
    /**
     * Creates a new view for the user and initializes it with the methods in the CoffeeController class that sets
     * the default values for the user to being interacting with the view.
     */
    @FXML
    protected void coffeeView() {
        Stage view1 = new Stage();
        AnchorPane root;
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("coffeeView.fxml"));
            root = loader.load();
            Scene scene = new Scene(root, 832, 600);
            view1.setScene(scene);
            view1.show();
            CoffeeController view1controller = loader.getController();
            view1controller.setMainController(this);
        } catch (IOException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("ERROR");
            alert.setHeaderText("Loading View1.fxml.");
            alert.setContentText("Couldn't load View1.fxml.");
            alert.showAndWait();
        }
    }

    /**
     * Updates the object referred by the currentObject to add the menu items selected in the CoffeeController view
     * and/or the DonutController by the user. This is used in those views.
     * @param newItems ArrayList of menu items that would be appended to the current built order.
     */
    public void updateOrder(ArrayList<MenuItem> newItems) {
        if(currentOrder == null) {
            currentOrder = new Order();
        }
        currentOrder.addItems(newItems);
    }

    /**
     * Updates the ArrayList referred to by storeOrders to add an Order instance which holds details about an order
     * that was placed by the user. This is called in the OrderController.
     * @param newOrder Order instance that would be added to the list of store orders.
     */
    public void addToStoreOrders(Order newOrder) {
        if(storeOrders == null) {
            storeOrders = new ArrayList<>();
        }
        storeOrders.add(newOrder);
        currentOrder = null;
    }

}
