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
    private ArrayList<String> menuItemDesc;
    private ArrayAdapter<String> adapter;

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
    private void updateAdapter() {
        menuItemDesc.clear();
        for(MenuItem item : Order.currOrder.menuList()) {
            menuItemDesc.add(item.toString());
        }
        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1,
                menuItemDesc);
        orderListView.setOnItemClickListener(this);
        orderListView.setAdapter(adapter);
    }
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

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
        AlertDialog.Builder alert = new AlertDialog.Builder(this);
        alert.setTitle("Demo the alert dialog.");
        alert.setMessage(adapterView.getAdapter().getItem(position).toString());
        alert.setPositiveButton(R.string.yes, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                Order.currOrder.removeItem(adapterView.getAdapter().getItem(position).toString());
                updateAdapter();
                updatePrices();
                Toast.makeText(getApplicationContext(), R.string.remove_item_message,
                        Toast.LENGTH_LONG).show();
            }
        }).setNegativeButton(R.string.no, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
            }
        });
        AlertDialog dialog = alert.create();
        dialog.show();
    }
    private void setAddOrderButtonOnClick() {
        addOrderButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(Order.storeOrders.isEmpty()) {
                    Order.storeOrders.add(Order.currOrder);
                    Order.currOrder = null;
                    Toast.makeText(getApplicationContext(),
                            R.string.add_order_message, Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(getApplicationContext(),
                            R.string.empty_order_alert, Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}