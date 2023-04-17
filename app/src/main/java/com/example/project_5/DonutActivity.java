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
    private int[] donutImages = {R.drawable.donut,R.drawable.donut,R.drawable.donut, R.drawable.donut, R.drawable.donut,
            R.drawable.donut,R.drawable.donut,R.drawable.donut,R.drawable.donut,R.drawable.donut,R.drawable.donut
            ,R.drawable.donut,R.drawable.donut};

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
        String [] yeastDonutFlavors = getResources().getStringArray(R.array.yeast_donut_flavors);
        String [] cakeDonutFlavors = getResources().getStringArray(R.array.cake_donut_flavors);
        String [] holeDonutFlavors = getResources().getStringArray(R.array.donut_hole_flavors);
        for (int i = 0; i < yeastDonutFlavors.length; i++) {
            listOfDonuts.add(new Donut(donutNames[0], yeastDonutFlavors[i], 1, donutImages[i]));
        }
        for (int i = 0; i < yeastDonutFlavors.length; i++) {
            listOfDonuts.add(new Donut(donutNames[1], yeastDonutFlavors[i], 1, donutImages[i]));
        }
        for (int i = 0; i < holeDonutFlavors.length; i++) {
            listOfDonuts.add(new Donut(donutNames[2], holeDonutFlavors[i], 1, donutImages[i]));
        }
    }
}