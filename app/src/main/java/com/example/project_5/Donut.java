package com.example.project_5;

/**
 * Donut class used to create objects representing different types and flavors of donuts
 * @author Steven Tan, David Fabian
 */
public class Donut extends MenuItem{
    public static final double NONE = 0.00;
    static public final String [] DONUTTYPES = {"Yeast Donut", "Cake Donut", "Donut Hole"};
    public static final double COSTOFYEASTDONUT = 1.59;
    public static final double COSTOFCAKEDONUT = 1.79;
    public static final double COSTOFDONUTHOLE = 0.39;
    static public final String [] YEASTDONUTFLAVORS = {"Jelly", "Vanilla", "Cinnamon", "Apple Cider", "Blueberry",
            "Pumpkin Spice"};
    static public final String [] CAKEDONUTFLAVORS = {"Chocolate", "Rainbow", "Sugar"};
    static public final String [] DONUTHOLEFLAVORS = {"Red Velvet", "Apple Fritter", "Powdered"};

    private String donutType;
    private int donutQuantity;
    private String donutFlavor;
    private int donutImage;

    /**
     * Constructs a Donut instance for a donut order.
     * Holds the donut type, flavor of donut, and number of donuts ordered
     * @param  donutType  type of donut
     * @param  donutFlavor  flavor of donut
     * @param  donutQuantity  number of donut type and flavor ordered
     */
    public Donut(String donutType, String donutFlavor, int donutQuantity, int image) {
        this.donutType = donutType;
        this.donutFlavor = donutFlavor;
        this.donutQuantity = donutQuantity;
        this.donutImage = image;
    }

    /**
     * Calculates the itemPrice based upon the cost of type of donut and the donut quantity.
     * @return the new itemPrice for the most recent coffee order.
     */
    @Override
    public double itemPrice() {
        if(this.donutType.equals(DONUTTYPES[0])) {
            return COSTOFYEASTDONUT * this.donutQuantity;
        } else if(this.donutType.equals(DONUTTYPES[1])) {
            return COSTOFCAKEDONUT * this.donutQuantity;
        } else if(this.donutType.equals(DONUTTYPES[2])){
            return COSTOFDONUTHOLE * this.donutQuantity;
        }
        return NONE;
    }

    /**
     * Gets the most recently selected donut flavor
     * @return a string representation of the donut flavor
     */
    public String donutFlavor() {
        return this.donutFlavor;
    }

    /**
     * Gets the most recently selected donut type
     * @return a string representation of the donut type
     */
    public String donutType() {
        return this.donutType;
    }

    /**
     * Retrieves the quantity of donuts of this Donut instance.
     * @return int representing the number of donuts of this kind.
     */
    public int donutQuantity() {
        return this.donutQuantity;
    }

    /**
     * Retrieves the donut image of this Donut instance.
     * @return int representing the donut image
     */
    public int donutImage() {
        return this.donutImage;
    }

    /**
     * Adds the argument integer to the quantity value of this Donut instance.
     * @param quantityToAdd quantity of donuts to add onto this instance.
     */
    public void addQuantity(int quantityToAdd) {
        this.donutQuantity += quantityToAdd;
    }

    /**
     * Forms a formatted string [donut flavor (quantity)]
     * @return a string formatted as above representing a donut order.
     */
    public String toString() {
        return this.donutFlavor + "(" + this.donutQuantity +")";
    }

    /**
     * Checks whether the argument Object is a Donut instance with the same String content as this Donut instance
     * regarding the donut flavor and type.
     * @param otherObj Object to be compared to this Donut instance.
     * @return true if the argument shares the same donut flavor and type as this Donut instance; false otherwise.
     */
    @Override
    public boolean equals(Object otherObj) {
        if(otherObj instanceof Donut) {
            Donut otherDonut = (Donut) otherObj;
            return this.donutFlavor.equals(otherDonut.donutFlavor()) && this.donutType.equals(otherDonut.donutType());
        }
        return false;
    }
}
