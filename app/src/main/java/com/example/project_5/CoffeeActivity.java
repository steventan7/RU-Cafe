package com.example.project_5;

import android.content.Intent;
import android.os.Bundle;
import android.widget.EditText;
import androidx.appcompat.app.AppCompatActivity;

/**
 * Demonstration on how the extra data is retrieved.
 * @author Steven Tan, David Fabian
 */
public class CoffeeActivity extends AppCompatActivity {
    private EditText tv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_coffee);
        Intent intent = getIntent();
        tv = findViewById(R.id.tv_showData);
        //display the integer on the TextView.
        if (intent.hasExtra("INTEGER"))
            tv.setText(String.valueOf(intent.getIntExtra("INTEGER", 0)));
        //display the string on the TextView
        if (intent.hasExtra("STRING"))
            tv.setText(intent.getStringExtra("STRING"));
    }
}