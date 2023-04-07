package com.example.project_5;

import java.util.ArrayList;

/**
 * Donut class used to create objects representing different orders for cups of coffee
 * @author Steven Tan, David Fabian
 */
public class Coffee extends MenuItem{

    public static final double NONE = 0.00;
    public static final double COSTOFSHORT = 1.89;
    public static final double COSTOFTALL = 2.29;
    public static final double COSTOFGRANDE = 2.69;
    public static final double COSTOFVENTI = 3.09;
    public static final double COSTOFADD_IN = 0.30;

    private String cupSize;
    private ArrayList<String> addIns;
    private int quantity;

    /**
     * Constructs a Coffee instance for a coffee order.
     * Holds an empty list of addIns and has a default quantity of 1.
     */
    public Coffee() {
        this.addIns = new ArrayList<>();
        this.quantity = 1;
    }

    /**
     * Calculates the itemPrice based upon cupSize, the number of add-ins, and the number of cups ordered.
     * @return the new itemPrice for the most recent coffee order.
     */
    @Override
    public double itemPrice() {
        double price = 0;
        if (cupSize == null) {
            price = NONE;
        } else if(cupSize.equals("Short")) {
            price = COSTOFSHORT;
        } else if(cupSize.equals("Tall")) {
            price = COSTOFTALL;
        } else if(cupSize.equals("Grande")) {
            price = COSTOFGRANDE;
        } else if (cupSize.equals("Venti")){
            price = COSTOFVENTI;
        }
        return (price + this.addIns.size() * COSTOFADD_IN) * this.quantity;
    }

    /**
     * Gets the list of add-ins in the current coffee order.
     * @return ArrayList of Strings of the list of add-ins of this coffee instance.
     */
    public ArrayList<String> listOfAddIns() {
        return this.addIns;
    }

    /**
     * Retrieves the cup size of this Coffee instance as a String.
     * @return String representing the size of this coffee.
     */
    public String cupSize() {
        return this.cupSize;
    }

    /**
     * Sets the current cupSize to the newCupSize.
     * @param newCupSize String representation of the cupSize the order is changed to
     */
    public void setCupSize(String newCupSize) {
        this.cupSize = newCupSize;
    }

    /**
     * Retrieves the quantity of coffees of this kind as a positive integer.
     * @return int representing the number of coffees of this kind.
     */
    public int coffeeQuantity() {
        return this.quantity;
    }

    /**
     * Sets the current quantity to the newQuantity.
     * @param newQuantity the newQuantity value the quantity value is changed to
     */
    public void setQuantity (int newQuantity) {
        this.quantity = newQuantity;
    }

    /**
     * Forms a formatted string [cupSize Black Coffee (quantity) [listOfAddIns]] of this Date instance.
     * @return a string formatted as above representing a coffee order.
     */
    public String toString() {
        return cupSize + " Black Coffee (" + quantity + ")" + "[" + stringListOfAddIns() + "]";
    }

    /**
     * Creates a String of the list of add-ins, if any.
     * @return a string representation of the list of add-ins
     */
    private String stringListOfAddIns() {
        String list = "";
        for (String addIn: addIns) {
            list += addIn + ", ";
        }
        if(list.isEmpty()) {
            list = "No Add-ons  ";
        }
        return list.substring(0, list.length() - 2);
    }

    /**
     * Checks whether the argument object is a Coffee instance object with the same add-ins and cup size as this Coffee
     * instance.
     * @param otherObj Object to be compared to this Coffee instance.
     * @return boolean true if the other Object has a similar list of add-ins and cup size as this Coffee instance;
     * false otherwise.
     */
    @Override
    public boolean equals(Object otherObj) {
        if(otherObj instanceof Coffee) {
            Coffee otherCoffee = (Coffee) otherObj;
            return this.cupSize.equals(otherCoffee.cupSize()) && this.addIns.containsAll(otherCoffee.listOfAddIns())
                    && otherCoffee.listOfAddIns().containsAll(this.addIns);
        }
        return false;
    }
}
