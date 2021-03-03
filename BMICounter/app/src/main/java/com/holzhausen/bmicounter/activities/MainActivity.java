package com.holzhausen.bmicounter.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputLayout;
import com.holzhausen.bmicounter.R;
import com.holzhausen.bmicounter.logic.BMICounter;
import com.holzhausen.bmicounter.logic.MetricBMICounter;

import java.util.Objects;

public class MainActivity extends AppCompatActivity {

    private TextInputLayout heightInput;

    private TextInputLayout weightInput;

    private Button countButton;

    private TextView bmiDisplay;
    
    private BMICounter bmiCounter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        heightInput = findViewById(R.id.height_field);
        weightInput = findViewById(R.id.weight_field);
        countButton = findViewById(R.id.count_bmi_button);
        bmiDisplay = findViewById(R.id.bmi_value);
        bmiCounter = new MetricBMICounter();

        countButton.setOnClickListener(view -> {
            int height = Integer.parseInt(Objects.requireNonNull(heightInput.getEditText())
                    .getText().toString());
            int weight = Integer.parseInt(Objects.requireNonNull(weightInput.getEditText())
                    .getText().toString());

            if(!bmiCounter.setHeight(height)){
                showErrorMessage("height", height);
                return;
            }
            if(!bmiCounter.setWeight(weight)){
                showErrorMessage("weight", weight);
                return;
            }
            bmiDisplay.setText(String.valueOf(Math.round(bmiCounter.getBmi() * 100) / 100.0));
        });

    }
    
    private void showErrorMessage(String wrongValueType, int value){
        String msg = "The value " + value + " is out of bounds for provided " + wrongValueType;
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }
}