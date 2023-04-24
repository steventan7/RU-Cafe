package com.example.project_5;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;

/**
 * ??? Not sure if this class is really needed for the donut order
 * @author Steven Tan, David Fabian
 */
public class DonutSelectedActivity extends AppCompatActivity {
    private Button btn_itemName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_donut_selected);
        btn_itemName = findViewById(R.id.btn1);
        Intent intent = getIntent();
        btn_itemName.setText(intent.getStringExtra("ITEM"));
    }
}
