package com.example.project_5;

import android.os.Bundle;
import android.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import android.content.DialogInterface;

import android.view.View;
import android.widget.TextView;
import android.widget.ListView;
import android.widget.Button;
import android.widget.Toast;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import java.text.DecimalFormat;

import java.util.ArrayList;

/**
 * Activity class that replicates a shopping cart that temporarily holds the menu items (donuts or coffee)
 *  * added so far, but the order hasn’t been placed yet.
 * @author David Fabian, Steven Tan
 */
public class OrderActivity extends AppCompatActivity implements AdapterView.OnItemClickListener{
    private TextView subTotalLabel;
    private TextView taxLabel;
    private TextView totalLabel;
    private ListView orderListView;
    private Button addOrderButton;
    private ArrayList<String> menuItemDesc = new ArrayList<>();
    private ArrayAdapter<String> adapter;

    /**
     * Gets the references of all instances of Views defined in the layout file and sets up the list of
     * items to be display in the order view
     * @param savedInstanceState Bundle that contains data sent through the change in activity.
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_yourorder);
        subTotalLabel = findViewById(R.id.subtotalField);
        taxLabel = findViewById(R.id.taxField);
        totalLabel = findViewById(R.id.totalField);
        orderListView = findViewById(R.id.orderListView);
        addOrderButton = findViewById(R.id.addOrderButton);
        updateAdapter();
        updatePrices();
        setAddOrderButtonOnClick();
    }

    /**
     * Updates the order adapter by first clearing the menuItemDesc ArrayList and then adding all the current
     * order menu items to it. The orderList is then set to the newly updated adapter
     */
    private void updateAdapter() {
        if(menuItemDesc != null) {
            menuItemDesc.clear();
        } else {
            menuItemDesc = new ArrayList<>();
        }
        for(MenuItem item : Order.currOrder.menuList()) {
            menuItemDesc.add(item.toString());
        }
        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1,
                menuItemDesc);
        orderListView.setOnItemClickListener(this);
        orderListView.setAdapter(adapter);
    }

    /**
     * Updates the order prices. If the current order is not null or there is no menu items currently added, then the
     * subTotal,tax, and total cost of the order are set to a price of zero. Else, the subTotal, tax, and total cost of
     * order is set to their respective cost.
     */
    private void updatePrices() {
        if(Order.currOrder == null || Order.currOrder.menuList().isEmpty()) {
            subTotalLabel.setText(R.string.zero_price);
            taxLabel.setText(R.string.zero_price);
            totalLabel.setText(R.string.zero_price);
            return;
        }
        subTotalLabel.setText(DecimalFormat.getCurrencyInstance()
                .format(Order.currOrder.subTotal()));
        taxLabel.setText(DecimalFormat.getCurrencyInstance()
                .format(Order.currOrder.subTotal() * (Order.NJTAX - 1)));
        totalLabel.setText(DecimalFormat.getCurrencyInstance()
                .format(Order.currOrder.subTotal() * (Order.NJTAX)));
    }

    /**
     * Displays the respective alert dialog message when the user clicks on the button. If the user clicks "yes",
     * the order selected is removed. Else, nothing happens.
     * @param adapterView the current order adapter view being used
     * @param view the current order view being displayed
     * @param position the position of the current order
     * @param id the ID of the current order
     */
    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
        AlertDialog.Builder alert = new AlertDialog.Builder(this);
        alert.setTitle("Demo the alert dialog.");
        alert.setMessage(adapterView.getAdapter().getItem(position).toString());
        alert.setPositiveButton(R.string.yes, new DialogInterface.OnClickListener() {
            /**
             * Displays a message stating that the selected order has been removed
             * @param dialog the DialogInterface that is displayed
             * @param which the position of the current order
             */
            public void onClick(DialogInterface dialog, int which) {
                Order.currOrder.removeItem(adapterView.getAdapter().getItem(position).toString());
                updateAdapter();
                updatePrices();
                Toast.makeText(getApplicationContext(), R.string.remove_item_message,
                        Toast.LENGTH_LONG).show();
            }
        }).setNegativeButton(R.string.no, new DialogInterface.OnClickListener() {
            /**
             * Displays nothing when nothing has been selected
             * @param dialog the DialogInterface that is displayed
             * @param which the position of the current order
             */
            public void onClick(DialogInterface dialog, int which) {
            }
        });
        AlertDialog dialog = alert.create();
        dialog.show();
    }

    /**
     * Set the onClickListener for the button that checks whether the current store order is empty. If the store
     * order is empty, then the curr order is set to null and an"Added Order to Store Orders" Toast is sent.
     * Else a "Store Order List is Empty" Toast is sent.
     */
    private void setAddOrderButtonOnClick() {
        addOrderButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(Order.storeOrders.isEmpty()) {
                    Order.storeOrders.add(Order.currOrder);
                    Order.currOrder = null;
                    Toast.makeText(getApplicationContext(),
                            R.string.add_order_message, Toast.LENGTH_SHORT).show();
                    updateAdapter();
                    updatePrices();
                } else {
                    Toast.makeText(getApplicationContext(),
                            R.string.empty_order_alert, Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}