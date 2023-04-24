package com.example.project_5;

import android.content.Intent;
import android.os.Bundle;
import android.widget.EditText;
import androidx.appcompat.app.AppCompatActivity;

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
        Intent intent = getIntent();
    }
}