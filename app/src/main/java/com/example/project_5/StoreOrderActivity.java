package com.example.project_5;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;

import android.view.View;
import android.widget.Spinner;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Button;
import android.widget.Toast;
import android.widget.TextView;

import java.text.DecimalFormat;
import java.util.ArrayList;


/**
 * Activity class that allows the user to view all the orders placed so far, make any final changes, and export the
 * order.
 * @author David Fabian, Steven Tan
 */
public class StoreOrderActivity extends AppCompatActivity {
    private Spinner orderNumSpinner;
    private ListView orderListView;
    private Button removeOrderButton;
    private TextView orderPriceField;
    private ArrayList<String> menuItemListDesc;
    private ArrayList<String> orderNumbers;
    private ArrayAdapter adapter;

    /**
     * Gets the references of all instances of Views defined in the layout file and sets up the list of
     * items to be display in the RecyclerView.
     * @param savedInstanceState Bundle that contains data sent through the change in activity.
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_storeorder);
        orderListView = findViewById(R.id.orderListview);
        orderNumSpinner = findViewById(R.id.orderNumSpinner);
        removeOrderButton = findViewById(R.id.removeOrderButton);
        orderPriceField = findViewById(R.id.orderPrice);
        updateOrderNumList();
        updateListView();
        setRemoveOrderButtonOnClick();
    }

    /**
     * Updates the list view and order price field with the corresponding values of the currently
     * selected order.
     */
    private void updateListView() {
        if(Order.storeOrders.isEmpty()) {
            setArrayList(new Order());
            adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1,
                    menuItemListDesc);
            orderListView.setAdapter(adapter);
            String totalOrderPrice = DecimalFormat.getCurrencyInstance()
                    .format(new Order().subTotal());
            orderPriceField.setText(totalOrderPrice);
            return;
        }
        int orderNum = Integer.parseInt(orderNumSpinner.getSelectedItem().toString());
        int orderIndex = 0;
        while(orderIndex < Order.storeOrders.size() &&
                Order.storeOrders.get(orderIndex).orderNumber() != orderNum) {
            orderIndex++;
        }
        setArrayList(Order.storeOrders.get(orderIndex));
        adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1,
                menuItemListDesc);
        orderListView.setAdapter(adapter);
        String totalOrderPrice = DecimalFormat.getCurrencyInstance()
                .format(Order.storeOrders.get(orderNum).subTotal() * Order.NJTAX);
        orderPriceField.setText(totalOrderPrice);
    }

    /**
     * Updates the ArrayList representing the item description of the currently selected order.
     * @param selectedOrder Order which is currently selecting by the Spinner.
     */
    private void setArrayList(Order selectedOrder) {
        if(menuItemListDesc == null) {
            menuItemListDesc = new ArrayList<>();
        } else {
            menuItemListDesc.clear();
        }
        for(MenuItem item : selectedOrder.menuList()) {
            menuItemListDesc.add(item.toString());
        }
    }

    /**
     * Updates the Spinner with the list of order numbers that are available to see.
     */
    private void updateOrderNumList() {
        if(orderNumbers != null) {
            orderNumbers.clear();
        } else {
            orderNumbers = new ArrayList<>();
        }
        for(Order currOrder : Order.storeOrders) {
            orderNumbers.add(String.valueOf(currOrder.orderNumber()));
        }
        ArrayAdapter<String> adapter = new ArrayAdapter(this,
                android.R.layout.simple_spinner_item, orderNumbers);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        orderNumSpinner.setAdapter(adapter);
        if(!orderNumbers.isEmpty()) {
            orderNumSpinner.setSelection(0);
        }
    }

    /**
     * Sets up the remove button for removing the currently selected order in the Spinner.
     * Returns to the previous activity if the store orders list is empty afterwards.
     */
    private void setRemoveOrderButtonOnClick() {
        removeOrderButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(Order.storeOrders.isEmpty()) {
                    int duration = Toast.LENGTH_SHORT;
                    Toast toast = Toast.makeText(getApplicationContext(),
                            R.string.empty_order_list_alert, duration);
                    toast.show();
                } else {
                    int orderNum = Integer.parseInt(orderNumSpinner.getSelectedItem().toString());
                    Order.storeOrders.remove(orderNum);
                    updateOrderNumList();
                    orderNumSpinner.setSelection(0);
                }
                updateListView();
            }
        });
    }
}