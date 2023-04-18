package com.example.project_5;

import android.content.Intent;
import android.os.Bundle;
import android.widget.*;
import android.view.View;
import androidx.appcompat.app.AppCompatActivity;

import java.text.DecimalFormat;

/**
 * Demonstration on how the extra data is retrieved.
 * @author Steven Tan, David Fabian
 */
public class CoffeeActivity extends AppCompatActivity{
    private CheckBox caramel, vanilla, irishCream, sweetCream, frenchVanilla;
    private TextView itemPrice;
    private Spinner quantitySpinner;
    private Spinner cupSizeSpinner;
    private String[] coffeeQuantity = {"1","2","3", "4", "5"};
    private String[] cupSize = {"Short", "Tall", "Grande", "Venti"};
    private ArrayAdapter<String> adapterQuantity;
    private ArrayAdapter<String> adapterCupSize;

    private Coffee coffeeOrder = new Coffee();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_coffee);
        initiate();
        quantitySpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                itemPrice.setText(adapterView.getSelectedItem().toString());
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                itemPrice.setText("");
            }
        });
//        cupSizeSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
//            @Override
//            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
//                itemPrice.setText(adapterView.getSelectedItem().toString());
//            }
//
//            @Override
//            public void onNothingSelected(AdapterView<?> adapterView) {
//                itemPrice.setText("");
//            }
//        });
        Intent intent = getIntent();
    }

    private void initiate() {
        caramel = findViewById(R.id.caramel);
        vanilla = findViewById(R.id.vanilla);
        irishCream = findViewById(R.id.irishcream);
        sweetCream = findViewById(R.id.sweetcream);
        frenchVanilla = findViewById(R.id.frenchvanilla);
        itemPrice = findViewById(R.id.currentcoffeeprice);
        quantitySpinner = findViewById(R.id.coffeequantity);
        cupSizeSpinner = findViewById(R.id.coffeesize);
        adapterQuantity = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, coffeeQuantity);
        adapterCupSize = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, cupSize);
        quantitySpinner.setAdapter(adapterQuantity);
        cupSizeSpinner.setAdapter(adapterCupSize);
    }

    /**
     * Adds caramel to the order if the item is selected and is not in the listOfAddsins. If the item is unselected,
     * the coffeeOrder removes caramel from it. The itemPrice increases by $0.30 if added and vice versa.
     */
    public void addCaramel(View view) {
        if (caramel.isChecked() && !coffeeOrder.listOfAddIns().contains("Caramel")) {
            coffeeOrder.listOfAddIns().add("Caramel");
        } else {
            if(coffeeOrder.listOfAddIns().contains("Caramel")) {
                coffeeOrder.listOfAddIns().remove("Caramel");
            }
        }
        itemPrice.setText(DecimalFormat.getCurrencyInstance().format((coffeeOrder.itemPrice())));
    }

    /**
     * Adds Irish cream to the order if the item is selected and is not in the listOfAddsins. If the item is unselected,
     * the coffeeOrder removes Irish cream from it. The itemPrice increases by $0.30 if added and vice versa.
     */
    public void addIrishCream(View view) {
        if (irishCream.isChecked()  && !coffeeOrder.listOfAddIns().contains("Irish Cream")) {
            coffeeOrder.listOfAddIns().add("Irish Cream");
        } else {
            if(coffeeOrder.listOfAddIns().contains("Irish Cream")) {
                coffeeOrder.listOfAddIns().remove("Irish Cream");
            }
        }
        itemPrice.setText(DecimalFormat.getCurrencyInstance().format((coffeeOrder.itemPrice())));
    }

    /**
     * Adds vanilla to the order if the item is selected and is not in the listOfAddsins. If the item is unselected,
     * the coffeeOrder removes vanilla from it. The itemPrice increases by $0.30 if added and vice versa.
     */
    public void addVanilla(View view) {
        if (vanilla.isChecked()  && !coffeeOrder.listOfAddIns().contains("Vanilla")) {
            coffeeOrder.listOfAddIns().add("Vanilla");
        } else {
            if(coffeeOrder.listOfAddIns().contains("Vanilla")) {
                coffeeOrder.listOfAddIns().remove("Vanilla");
            }
        }
        itemPrice.setText(DecimalFormat.getCurrencyInstance().format((coffeeOrder.itemPrice())));
    }

    /**
     * Adds French vanilla to the order if the item is selected and is not in the listOfAddsins. If the item is
     * unselected, the coffeeOrder removes French vanilla from it. The itemPrice increases by $0.30 if added and
     * vice versa.
     */
    public void addFrenchVanilla(View view) {
        if (frenchVanilla.isChecked()  && !coffeeOrder.listOfAddIns().contains("French Vanilla")) {
            coffeeOrder.listOfAddIns().add("French Vanilla");
        } else {
            if(coffeeOrder.listOfAddIns().contains("French Vanilla")) {
                coffeeOrder.listOfAddIns().remove("French Vanilla");
            }
        }
        itemPrice.setText(DecimalFormat.getCurrencyInstance().format((coffeeOrder.itemPrice())));
    }

    /**
     * Adds sweet cream  to the order if the item is selected and is not in the listOfAddsins. If the item is unselected,
     * the coffeeOrder removes sweet cream from it. The itemPrice increases by $0.30 if added and vice versa.
     */
    public void addSweetCream(View view) {
        if (sweetCream.isChecked()  && !coffeeOrder.listOfAddIns().contains("Sweet Cream")) {
            coffeeOrder.listOfAddIns().add("Sweet Cream");
        } else {
            if(coffeeOrder.listOfAddIns().contains("Sweet Cream")) {
                coffeeOrder.listOfAddIns().remove("Sweet Cream");
            }
        }
        itemPrice.setText(DecimalFormat.getCurrencyInstance().format((coffeeOrder.itemPrice())));
    }

//    /**
//     * Sets the cupSize to the size that is selected in the ComboBox. The itemPrice changes in accordance to whatever
//     * price the cupSize is worth.
//     */
//    public void setCupSize() {
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
//    public void setQuantity() {
//        String value = this.chosenQuantity.getSelectionModel().getSelectedItem();
//        coffeeOrder.setQuantity(Integer.parseInt(value));
//        itemPrice.setText(DecimalFormat.getCurrencyInstance().format((coffeeOrder.itemPrice())));
//    }
}