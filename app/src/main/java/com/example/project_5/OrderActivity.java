//package com.example.project_5;
//
//import javafx.collections.FXCollections;
//import javafx.collections.ObservableList;
//import javafx.fxml.FXML;
//import javafx.scene.control.Alert;
//import javafx.scene.control.Button;
//import javafx.scene.control.ListView;
//import javafx.scene.control.TextField;
//import javafx.stage.Stage;
//
//import java.text.DecimalFormat;
//import java.util.ArrayList;
//
///**
// * This class contains methods that replicate a shopping cart that temporarily holds the menu items (donuts or coffee)
// * added so far, but the order hasn’t been placed yet.
// * @author David Fabian, Steven Tan
// */
//public class OrderController {
//    private StoreFrontController mainController;
//    private Order currOrderRef;
//    private static final double NJSALESTAX = 0.06625;
//    @FXML
//    private ListView<String> orderListView;
//    @FXML
//    private TextField orderNumber, orderSubTotal, orderSalesTax, orderTotal;
//    @FXML
//    private Button exitButton;
//    private ObservableList<String> orderedItems;
//    /**
//     * Sets the main controller to the StoreFrontController
//     * @param controller StoreFrontController for which to set this instance's main controller reference to.
//     */
//    public void setMainController (StoreFrontController controller){
//        mainController = controller;
//    }
//    /**
//     * Initializes the prices' text fields and list view component by setting the default values for each.
//     */
//    public void initialize() {
//        orderSubTotal.setText("$0.00");
//        orderSalesTax.setText("$0.00");
//        orderTotal.setText("$0.00");
//        orderedItems = FXCollections.observableArrayList(new ArrayList<>());
//    }
//    /**
//     * Removes the user-selected item from the order. This removal is reflected on the order list view and the values
//     * for the subtotal, sales tax, and total are updated.
//     */
//    @FXML
//    protected void removeItem() {
//        String selectedItem = this.orderListView.getSelectionModel().getSelectedItem();
//        if(selectedItem != null) {
//            this.currOrderRef.removeItem(selectedItem);
//            this.orderedItems.remove(selectedItem);
//        } else {
//            Alert alert = new Alert(Alert.AlertType.ERROR);
//            alert.setTitle("NO MENU ITEM SELECTED");
//            alert.setHeaderText("No menu item was selected.");
//            alert.setContentText("If your order has menu items, please select one in the List view if you would like" +
//                    " to remove that item.");
//            alert.showAndWait();
//            return;
//        }
//        if(this.currOrderRef.menuList().isEmpty()) {
//            Alert alert = new Alert(Alert.AlertType.INFORMATION);
//            alert.setTitle("ORDER IS EMPTY");
//            alert.setHeaderText("Order no longer has menu items.");
//            alert.setContentText("Your order is now empty. Please order menu items in other views to continue " +
//                    "utilizing this view.");
//            alert.showAndWait();
//            this.currOrderRef = null;
//        }
//        this.updatePrices();
//    }
//
//    /**
//     * Adds current order to the list of store orders being held in an arraylist. Immediately closes the view if the
//     * order has menu items. Notifies the user if the order does not.
//     */
//    @FXML
//    protected void addToStoreOrders() {
//        if(this.currOrderRef != null && !this.currOrderRef.menuList().isEmpty()) {
//            this.mainController.addToStoreOrders(this.currOrderRef);
//            Alert alert = new Alert(Alert.AlertType.INFORMATION);
//            alert.setTitle("ORDER PLACED");
//            alert.setHeaderText("Order was successfully placed.");
//            alert.setContentText("Your order was added to the list of store orders, which can be seen in the Store" +
//                            " Orders view.");
//            alert.showAndWait();
//            ((Stage) exitButton.getScene().getWindow()).close();
//            return;
//        }
//        Alert alert = new Alert(Alert.AlertType.WARNING);
//        alert.setTitle("NO ORDERED ITEMS");
//        alert.setHeaderText("Order cannot be placed!");
//        alert.setContentText("Current order list does not have any items. Please go to the Coffee or Donut Views" +
//                " to add menu items to your order before placing.");
//        alert.showAndWait();
//    }
//
//    /**
//     * Sets the order list view and the text field displaying the values for subtotal, sales tax, and total price of
//     * the current order being placed. This method is called immediately after setting the Main Controller containing
//     * the reference to the current order.
//     * Notifies the user if there is no current order.
//     */
//    public void setOrderedItems() {
//        this.currOrderRef = null;
//        if(mainController == null || mainController.currentOrder == null) {
//            Alert alert = new Alert(Alert.AlertType.WARNING);
//            alert.setTitle("NO ORDERED ITEMS");
//            alert.setHeaderText("Order list is empty!");
//            alert.setContentText("Current order list does not have any items. Please go to the Coffee or Donut Views" +
//                    " to add menu items to your order.");
//            alert.showAndWait();
//            return;
//        }
//        this.currOrderRef = mainController.currentOrder;
//        ArrayList<String> itemList = new ArrayList<>();
//        for(MenuItem item : mainController.currentOrder.menuList()) {
//            itemList.add(item.toString());
//        }
//        this.orderNumber.setText(String.valueOf(mainController.currentOrder.orderNumber()));
//        this.orderedItems = FXCollections.observableArrayList(itemList);
//        this.orderListView.setItems(this.orderedItems);
//        this.updatePrices();
//    }
//
//    /**
//     * Updates the price listed for the current order being viewed/placed by the user. If there is no order,
//     * "$0.00" is set as the text.
//     */
//    private void updatePrices() {
//        if(this.currOrderRef != null) {
//            double salesTax = NJSALESTAX * this.currOrderRef.subTotal();
//            orderSubTotal.setText(DecimalFormat.getCurrencyInstance().format(this.currOrderRef.subTotal()));
//            orderSalesTax.setText(DecimalFormat.getCurrencyInstance().format(salesTax));
//            orderTotal.setText(DecimalFormat.getCurrencyInstance().format(this.currOrderRef.subTotal()
//                    + salesTax));
//        } else {
//            orderSubTotal.setText("$0.00");
//            orderSalesTax.setText("$0.00");
//            orderTotal.setText("$0.00");
//        }
//    }
//}
