package com.example.project_5;

/**
 * MenuItem class used to create objects representing cafe menu items.
 * @author Steven Tan, David Fabian
 */
public abstract class MenuItem {

    /**
     * Retrieves the price of the menu item, which can vary depending on the kind of menu item.
     * @return double representing the price of the menu item that is not gaurenteed to be rounded to the nearest
     * hundredth place.
     */
    public abstract double itemPrice();
}
