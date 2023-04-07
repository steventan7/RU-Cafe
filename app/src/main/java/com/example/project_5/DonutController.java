package com.example.project_5;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import java.text.DecimalFormat;
import java.util.ArrayList;

/**
 * This class contains methods that allow the user to add or remove the selected donuts to/from the ordering basket.
 * @author Steven Tan, David Fabian
 */
public class DonutController {

    @FXML
    private ImageView cakeDonutImage, yeastDonutImage, donutHoleImage;

    @FXML
    private ListView<String> donutFlavorsListView;

    @FXML
    private ListView<String> donutOrderListView;

    @FXML
    private ComboBox<String> donutOrderComboBox;

    @FXML
    private ComboBox<String> amount;

    @FXML
    private TextArea itemPrice;

    @FXML
    private Button removeDonut, addDonut, exitButton;

    private StoreFrontController mainController;
    private ObservableList<String> donutComboBoxList;
    private ObservableList<String> donutOrderList;
    private ObservableList<String> amountList;
    private ObservableList<String> yeastDonutFlavors;
    private ObservableList<String> cakeDonutFlavors;
    private ObservableList<String> donutHoleFlavors;
    private ArrayList<MenuItem> listOfDonutsOrdered = new ArrayList<>();
    private double totalCost = 0;

    /**
     * Initializes the necessary components (Comboboxes, List views) by setting the observable arrays for each.
     * The yeast donut type is set as default in the selection.
     */
    public void initialize() {
        amountList = FXCollections.observableArrayList("1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13",
                "14", "15", "16", "17", "18", "19", "20");
        yeastDonutFlavors = FXCollections.observableArrayList(Donut.YEASTDONUTFLAVORS);
        cakeDonutFlavors = FXCollections.observableArrayList(Donut.CAKEDONUTFLAVORS);
        donutHoleFlavors = FXCollections.observableArrayList(Donut.DONUTHOLEFLAVORS);
        donutComboBoxList = FXCollections.observableArrayList(Donut.DONUTTYPES);
        donutOrderList = FXCollections.observableArrayList();
        donutOrderComboBox.setItems(donutComboBoxList);

        donutFlavorsListView.setItems(yeastDonutFlavors);
        donutOrderListView.setItems(donutOrderList);
        amount.setItems(amountList);
        donutOrderComboBox.getSelectionModel().select("Yeast Donut");
        itemPrice.setText("0.00");
        amount.getSelectionModel().select("1");
    }

    /**
     * Handles the Combobox that specifies the type of donut the user selected and updates the selection List view to
     * display the flavors specific to that type of donut.
     */
    @FXML
    protected void changeDonutType() {
        String selectedDonutType = donutOrderComboBox.getSelectionModel().getSelectedItem();
        if (selectedDonutType.equals("Yeast Donut")) {
            donutFlavorsListView.setItems(yeastDonutFlavors);
            yeastDonutImage.setVisible(true);
            donutHoleImage.setVisible(false);
            cakeDonutImage.setVisible(false);
        } else if (selectedDonutType.equals("Cake Donut")) {
            donutFlavorsListView.setItems(cakeDonutFlavors);
            yeastDonutImage.setVisible(false);
            donutHoleImage.setVisible(false);
            cakeDonutImage.setVisible(true);
        } else {
            donutFlavorsListView.setItems(donutHoleFlavors);
            yeastDonutImage.setVisible(false);
            donutHoleImage.setVisible(true);
            cakeDonutImage.setVisible(false);
        }
    }

    /**
     * Adds the selected donut and flavor from the donut Combobox and List view to the order List view alongside with
     * the selected quantity from the amount Combobox. Removes that flavor from the selection List view then updates
     * the running subtotal and arraylist that keeps track of the currently selected items.
     */
    @FXML
    protected void addDonut() {
        String selectedDonutType = donutOrderComboBox.getSelectionModel().getSelectedItem();
        String selectedFlavor = donutFlavorsListView.getSelectionModel().getSelectedItem();
        String order = selectedFlavor + "(" + amount.getSelectionModel().getSelectedItem() + ")";
        int quantity = Integer.parseInt(amount.getSelectionModel().getSelectedItem());

        if (selectedFlavor != null) {
            Donut donut;
            if (selectedDonutType.equals("Yeast Donut")) {
                donut = new Donut("Yeast Donut", selectedFlavor, quantity);
                donutOrderList.add(order);
                yeastDonutFlavors.remove(selectedFlavor);
            } else if (selectedDonutType.equals("Cake Donut")) {
                donut = new Donut("Cake Donut", selectedFlavor, quantity);
                donutOrderList.add(order);
                cakeDonutFlavors.remove(selectedFlavor);
            } else {
                donut = new Donut("Donut Hole", selectedFlavor, quantity);
                donutOrderList.add(order);
                donutHoleFlavors.remove(selectedFlavor);
            }
            listOfDonutsOrdered.add(donut);
            totalCost += donut.itemPrice();
            itemPrice.setText(DecimalFormat.getCurrencyInstance().format((totalCost)));
        }
    }

    /**
     * Removes the selected donut flavor from the order List view alongside with quantity. The flavor is then added
     * back to the selection List view under the corresponding donut type. Updates the running subtotal and the
     * arraylist that keeps track of the currently selected items.
     */
    @FXML
    protected void removeDonut() {
        try {
            String selectedFlavor = donutOrderListView.getSelectionModel().getSelectedItem();
            String flavor = selectedFlavor.substring(0, selectedFlavor.indexOf('('));
            Donut donutToBeRemoved = (Donut) listOfDonutsOrdered.get(findDonut(flavor));

            if (donutToBeRemoved.donutType().equals("Yeast Donut")) {
                if (selectedFlavor != null) {
                    donutOrderList.remove(selectedFlavor);
                    yeastDonutFlavors.add(flavor);
                }
            } else if (donutToBeRemoved.donutType().equals("Cake Donut")) {
                if (selectedFlavor != null) {
                    donutOrderList.remove(selectedFlavor);
                    cakeDonutFlavors.add(flavor);
                }
            } else {
                if (selectedFlavor != null) {
                    donutOrderList.remove(selectedFlavor);
                    donutHoleFlavors.add(flavor);
                }
            }
            listOfDonutsOrdered.remove(donutToBeRemoved);
            totalCost -= donutToBeRemoved.itemPrice();
            itemPrice.setText(DecimalFormat.getCurrencyInstance().format((totalCost)));
        }
        catch (Exception e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Order cannot be removed");
            alert.setHeaderText("Please click an order to be removed");
            alert.showAndWait();
        }
    }

    /**
     * Adds the most recent donut order(s) to the Order ArrayList after the user clicks on the "Add to Order" button.
     * Notifies the user for not ordering donuts if the order list is empty.
     */
    @FXML
    protected void addToOrder() {
        if(!listOfDonutsOrdered.isEmpty()) {
            mainController.updateOrder(listOfDonutsOrdered);
            ((Stage) exitButton.getScene().getWindow()).close();
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("DONUTS ADDED SUCCESSFULLY");
            alert.setHeaderText("Selected donuts were added to your order.");
            alert.setContentText("Your order details can be seen in the order view.");
            alert.showAndWait();
            ((Stage) exitButton.getScene().getWindow()).close();
            return;
        }
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("NO ORDERED DONUTS");
        alert.setHeaderText("Donut order list is empty!");
        alert.setContentText("Current order list does not have any donuts. Please select donuts to order first.");
        alert.showAndWait();
    }

    /**
     * Searches through the listOfDonutsOrdered ArrayList to determine whether a specific flavor of donut was ordered.
     * If found, the index of the donut is returned. Else, -1 is returned.
     * @param flavor String representing the flavor of donut for which to find.
     * @return the index, as an int, of the donut in the listOfDonutsOrdered that contains the argument flavor.
     */
    private int findDonut(String flavor) {
        for (int i = 0; i < listOfDonutsOrdered.size(); i++) {
            if (((Donut) listOfDonutsOrdered.get(i)).donutFlavor().equals(flavor)) {
                return i;
            }
        }
        return -1;
    }

    /**
     * Sets the main controller to the StoreFrontController
     * @param controller StoreFrontController for which to set this instance's main controller reference to.
     */
    protected void setMainController(StoreFrontController controller) {
        mainController = controller;
    }
}
