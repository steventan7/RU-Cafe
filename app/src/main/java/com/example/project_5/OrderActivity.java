package com.example.project_5;

import android.content.Intent;
import android.os.Bundle;
import android.widget.EditText;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

/**
 * Activity class that replicates a shopping cart that temporarily holds the menu items (donuts or coffee)
 *  * added so far, but the order hasn’t been placed yet.
 * @author David Fabian, Steven Tan
 */
public class OrderActivity extends AppCompatActivity {
    private EditText tv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_yourorder);
        RecyclerView orderRV = findViewById(R.id.orderRecyclerView);
        OrderAdapter adapter = new OrderAdapter(this);
        orderRV.setAdapter(adapter);
        orderRV.setLayoutManager(new LinearLayoutManager(this));
    }
}