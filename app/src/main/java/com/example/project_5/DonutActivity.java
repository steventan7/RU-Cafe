package com.example.project_5;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

/**
 *
 * @author Steven Tan, David Fabian
 */
public class DonutActivity extends AppCompatActivity {
    private ArrayList<Donut> listOfDonuts = new ArrayList<>();
    private int[] donutImages = {R.drawable.cart,R.drawable.donut,R.drawable.donut};

    /**
     * Get the references of all instances of Views defined in the layout file, set up the list of
     * items to be display in the RecyclerView.
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_donut);
        RecyclerView rcview = findViewById(R.id.donutRecycler);
        setupMenuItems();
        DonutAdapter adapter = new DonutAdapter(this, listOfDonuts); //create the adapter
        rcview.setAdapter(adapter);
        rcview.setLayoutManager(new LinearLayoutManager(this));
    }

    /**
     * Helper method to set up the data (the Model of the MVC).
     */
    private void setupMenuItems() {
        String [] donutNames = getResources().getStringArray(R.array.donut_types);
        for (int i = 0; i < donutNames.length; i++) {
            listOfDonuts.add(new Donut(donutNames[i], "Chocolate", 1, donutImages[i]));
        }
    }
}