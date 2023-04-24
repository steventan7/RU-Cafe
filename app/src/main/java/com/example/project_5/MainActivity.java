package com.example.project_5;

import android.content.Intent;
import android.view.View;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

/**
 * MainActivity class used to initiate the starting activity of the RuCafe Android app
 * @author Steven Tan, David Fabian
 */
public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    /**
     * Creates an Intent object that is used to take the user to the CoffeeActivity class
     * @param view the view that the user is taken to after clicking the respective button
     */
    public void showCoffeeActivity(View view) {
        Intent intent = new Intent(this, CoffeeActivity.class);
        startActivity(intent);
    }

    /**
     * Creates an Intent object that is used to take the user to the DonutActivity class
     * @param view the view that the user is taken to after clicking the respective button
     */
    public void showDonutActivity(View view) {
        Intent intent = new Intent(this, DonutActivity.class);
        startActivity(intent);
    }

    /**
     * Creates an Intent object that is used to take the user to the OrderActivity class
     * @param view the view that the user is taken to after clicking the respective button
     */
    public void showOrderActivity(View view) {
        Intent intent = new Intent(this, OrderActivity.class);
        intent.putExtra("INTEGER", 3);
        startActivity(intent);
    }

    /**
     * Creates an Intent object that is used to take the user to the showStoreOrderActivity class
     * @param view the view that the user is taken to after clicking the respective button
     */
    public void showStoreOrderActivity(View view) {
        Intent intent = new Intent(this, StoreOrderActivity.class);
        intent.putExtra("STRING", "CS 213 SOFTWARE METHODOLOGY");
        startActivity(intent);
    }
}