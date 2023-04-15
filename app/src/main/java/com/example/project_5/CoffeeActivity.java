//package com.example.project_5;
//
//import javafx.collections.FXCollections;
//import javafx.collections.ObservableList;
//import javafx.fxml.FXML;
//import javafx.scene.control.*;
//import javafx.stage.Stage;
//
//import java.text.DecimalFormat;
//import java.util.ArrayList;
//
///**
// * This class contains methods that allow the user to add or remove coffee orders to the ordering basket
// * @author Steven Tan, David Fabian
// */
//public class CoffeeController {
//    private StoreFrontController mainController;
//    private ObservableList<String> cupSizeList;
//    private ObservableList<String> quantityList;
//
//    @FXML
//    private CheckBox sweetCream, caramel, vanilla, irishCream, frenchVanilla;
//
//    @FXML
//    private ComboBox<String> cupSize;
//    @FXML
//    private Button exitButton;
//    @FXML
//    private ComboBox<String> chosenQuantity;
//
//    @FXML
//    private TextArea itemPrice;
//
//    private Coffee coffeeOrder = new Coffee();
//
//    /**
//     * Initializes the necessary components (Comboboxes, List views) by setting the observable arrays for each.
//     * The itemPrice is set to $1.89, the chosenQuantity is set to 1, and the cupSize is set to Short by all by default
//     */
//    public void initialize() {
//        quantityList = FXCollections.observableArrayList("1","2","3", "4", "5");
//        cupSizeList = FXCollections.observableArrayList("Short", "Tall", "Grande", "Venti");
//        cupSize.setItems(cupSizeList);
//        chosenQuantity.setItems(quantityList);
//        itemPrice.setText("$1.89");
//        chosenQuantity.getSelectionModel().select("1");
//        coffeeOrder.setCupSize("Short");
//        cupSize.getSelectionModel().select("Short");
//        this.setCupSize();
//    }
//
//    /**
//     * Adds caramel to the order if the item is selected and is not in the listOfAddsins. If the item is unselected,
//     * the coffeeOrder removes caramel from it. The itemPrice increases by $0.30 if added and vice versa.
//     */
//    @FXML
//    protected void addCaramel() {
//        if (caramel.isSelected() && !coffeeOrder.listOfAddIns().contains("Caramel")) {
//            coffeeOrder.listOfAddIns().add("Caramel");
//        } else {
//            if(coffeeOrder.listOfAddIns().contains("Caramel")) {
//                coffeeOrder.listOfAddIns().remove("Caramel");
//            }
//        }
//        itemPrice.setText(DecimalFormat.getCurrencyInstance().format((coffeeOrder.itemPrice())));
//    }
//
//    /**
//     * Adds Irish cream to the order if the item is selected and is not in the listOfAddsins. If the item is unselected,
//     * the coffeeOrder removes Irish cream from it. The itemPrice increases by $0.30 if added and vice versa.
//     */
//    @FXML
//    protected void addIrishCream() {
//        if (irishCream.isSelected() && !coffeeOrder.listOfAddIns().contains("Irish Cream")) {
//            coffeeOrder.listOfAddIns().add("Irish Cream");
//        } else {
//            if(coffeeOrder.listOfAddIns().contains("Irish Cream")) {
//                coffeeOrder.listOfAddIns().remove("Irish Cream");
//            }
//        }
//        itemPrice.setText(DecimalFormat.getCurrencyInstance().format((coffeeOrder.itemPrice())));
//    }
//
//    /**
//     * Adds vanilla to the order if the item is selected and is not in the listOfAddsins. If the item is unselected,
//     * the coffeeOrder removes vanilla from it. The itemPrice increases by $0.30 if added and vice versa.
//     */
//    @FXML
//    protected void addVanilla() {
//        if (vanilla.isSelected() && !coffeeOrder.listOfAddIns().contains("Vanilla")) {
//            coffeeOrder.listOfAddIns().add("Vanilla");
//        } else {
//            if(coffeeOrder.listOfAddIns().contains("Vanilla")) {
//                coffeeOrder.listOfAddIns().remove("Vanilla");
//            }
//        }
//        itemPrice.setText(DecimalFormat.getCurrencyInstance().format((coffeeOrder.itemPrice())));
//    }
//
//    /**
//     * Adds French vanilla to the order if the item is selected and is not in the listOfAddsins. If the item is
//     * unselected, the coffeeOrder removes French vanilla from it. The itemPrice increases by $0.30 if added and
//     * vice versa.
//     */
//    @FXML
//    protected void addFrenchVanilla() {
//        if (frenchVanilla.isSelected() && !coffeeOrder.listOfAddIns().contains("French Vanilla")) {
//            coffeeOrder.listOfAddIns().add("French Vanilla");
//        } else {
//            if(coffeeOrder.listOfAddIns().contains("French Vanilla")) {
//                coffeeOrder.listOfAddIns().remove("French Vanilla");
//            }
//        }
//        itemPrice.setText(DecimalFormat.getCurrencyInstance().format((coffeeOrder.itemPrice())));
//    }
//
//    /**
//     * Adds sweet cream  to the order if the item is selected and is not in the listOfAddsins. If the item is unselected,
//     * the coffeeOrder removes sweet cream from it. The itemPrice increases by $0.30 if added and vice versa.
//     */
//    @FXML
//    protected void addSweetCream() {
//        if (sweetCream.isSelected() && !coffeeOrder.listOfAddIns().contains("Sweet Cream")) {
//            coffeeOrder.listOfAddIns().add("Sweet Cream");
//        } else {
//            if(coffeeOrder.listOfAddIns().contains("Sweet Cream")) {
//                coffeeOrder.listOfAddIns().remove("Sweet Cream");
//            }
//        }
//        itemPrice.setText(DecimalFormat.getCurrencyInstance().format((coffeeOrder.itemPrice())));
//    }
//
//    /**
//     * Sets the cupSize to the size that is selected in the ComboBox. The itemPrice changes in accordance to whatever
//     * price the cupSize is worth.
//     */
//    @FXML
//    protected void setCupSize() {
//        String cup = this.cupSize.getSelectionModel().getSelectedItem();
//        if(cup != null) {
//            coffeeOrder.setCupSize(this.cupSize.getSelectionModel().getSelectedItem());
//        }
//        itemPrice.setText(DecimalFormat.getCurrencyInstance().format((coffeeOrder.itemPrice())));
//    }
//
//    /**
//     * Sets the cupSize to the quantity that is selected in the ComboBox. The itemPrice changes to the product of the
//     * quantity value times the sum of the number of addins and cupSize.
//     */
//    @FXML
//    protected void setQuantity() {
//        String value = this.chosenQuantity.getSelectionModel().getSelectedItem();
//        coffeeOrder.setQuantity(Integer.parseInt(value));
//        itemPrice.setText(DecimalFormat.getCurrencyInstance().format((coffeeOrder.itemPrice())));
//    }
//
//    /**
//     * Adds the most recent coffee order to the Order ArrayList after the user clicks on the "Add to Order" button
//     */
//    @FXML
//    protected void addToOrder() {
//        ArrayList<MenuItem> tempOrder = new ArrayList<>();
//        tempOrder.add(coffeeOrder);
//        mainController.updateOrder(tempOrder);
//        Alert alert = new Alert(Alert.AlertType.INFORMATION);
//        alert.setTitle("COFFEE ADDED SUCCESSFULLY");
//        alert.setHeaderText("Coffee was added to your order.");
//        alert.setContentText("Your order details can be seen in the order view.");
//        alert.showAndWait();
//        ((Stage) exitButton.getScene().getWindow()).close();
//    }
//
//    /**
//     * Sets the main controller to the StoreFrontController
//     * @param controller StoreFrontController for which to set this instance's main controller reference to.
//     */
//    public void setMainController (StoreFrontController controller){
//        mainController = controller;
//    }
//}
