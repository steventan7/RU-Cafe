package com.example.project_5;

import android.content.Intent;
import android.os.Bundle;
import android.widget.EditText;
import androidx.appcompat.app.AppCompatActivity;

/**
 * Activity class that allows the user to view all the orders placed so far, make any final changes, and export the
 * order.
 * @author David Fabian, Steven Tan
 */
public class StoreOrderActivity extends AppCompatActivity {
    private EditText tv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_storeorder);
        Intent intent = getIntent();
    }
}