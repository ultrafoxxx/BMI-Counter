package com.holzhausen.bmicounter;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

public class DescriptionActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_description);

        final TextView bmiValue = findViewById(R.id.bmi_value_description);
        final TextView description = findViewById(R.id.description);
        bmiValue.setText(String.valueOf(getIntent().getDoubleExtra("bmi", 0)));
        description.setText(getIntent().getStringExtra("description"));

    }
}