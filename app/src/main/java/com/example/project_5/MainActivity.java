package com.example.project_5;

import android.content.Intent;
import android.net.Uri;
import android.view.View;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    /**
     * Create an Intent object with the Java class to start under current context
     * @param view
     */
    public void showCoffeeActivity(View view) {
        Intent intent = new Intent(this, CoffeeActivity.class);
        startActivity(intent);
    }

    /**
     * Create an Intent object with the Java class to start under current context
     * @param view
     */
    public void showDonutActivity(View view) {
        Intent intent = new Intent(this, DonutActivity.class);
        startActivity(intent);
    }

    /**
     * Create an Intent object with extra data, which is an integer value 3. The KEY name for
     * retrieving the integer value is "INTEGER".
     * @param view
     */
    public void showOrderActivity(View view) {
        Intent intent = new Intent(this, OrderActivity.class);
        intent.putExtra("INTEGER", 3);
        startActivity(intent);
    }

    /**
     * Create an Intent object with extra data, which is a string literal.
     * The KEY name for retrieving the string value is "STRING".
     * @param view
     */
    public void showStoreOrderActivity(View view) {
        Intent intent = new Intent(this, StoreOrderActivity.class);
        intent.putExtra("STRING", "CS 213 SOFTWARE METHODOLOGY");
        startActivity(intent);
    }
}