package com.holzhausen.bmicounter.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputLayout;
import com.holzhausen.bmicounter.R;
import com.holzhausen.bmicounter.logic.BMICounter;
import com.holzhausen.bmicounter.logic.ImperialBMICounter;
import com.holzhausen.bmicounter.logic.MetricBMICounter;

import java.util.Objects;

public class MainActivity extends AppCompatActivity {

    private TextInputLayout heightInput;

    private TextInputLayout weightInput;

    private Button countButton;

    private TextView bmiDisplay;
    
    private BMICounter bmiCounter;

    private boolean metricMode;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        heightInput = findViewById(R.id.height_field);
        weightInput = findViewById(R.id.weight_field);
        countButton = findViewById(R.id.count_bmi_button);
        bmiDisplay = findViewById(R.id.bmi_value);
        metricMode = true;
        switchToMetricMode();

        countButton.setOnClickListener(view -> {
            int height = Integer.parseInt(Objects.requireNonNull(heightInput.getEditText())
                    .getText().toString());
            int weight = Integer.parseInt(Objects.requireNonNull(weightInput.getEditText())
                    .getText().toString());

            if(!bmiCounter.isHeightValid(height)){
                showErrorMessage("height", height);
                return;
            }
            bmiCounter.setHeight(height);
            if(!bmiCounter.isWeightValid(weight)){
                showErrorMessage("weight", weight);
                return;
            }
            bmiCounter.setWeight(weight);
            bmiCounter.countBmi();
            bmiDisplay.setText(String.valueOf(Math.round(bmiCounter.getBmi() * 100) / 100.0));
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if(item.getItemId() == R.id.metric_bmi_option && !metricMode){
            switchToMetricMode();
            return true;
        }
        else if (item.getItemId() == R.id.imperial_bmi_option && metricMode){
            switchToImperialMode();
            return true;
        }
        else if(item.getItemId() == R.id.about_option){
            startActivity(new Intent(this, AboutActivity.class));
            return true;
        }
        else {
            return super.onOptionsItemSelected(item);
        }
    }

    private void showErrorMessage(String wrongValueType, int value){
        String msg = "The value " + value + " is not proper for provided " + wrongValueType;
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }

    private void switchToMetricMode(){
        bmiCounter = new MetricBMICounter();
        constructHintsForInputs("cm", "kg");
        clearFields();
    }

    private void switchToImperialMode(){
        bmiCounter = new ImperialBMICounter();
        constructHintsForInputs("in", "lb");
        clearFields();
    }

    private void constructHintsForInputs(String firstUnitType, String secondUnitType){
        heightInput.setHint(getString(R.string.height_label) + " [" + firstUnitType + "]");
        weightInput.setHint(getString(R.string.weight_label) + " [" + secondUnitType + "]");
    }

    private void clearFields(){
        Objects.requireNonNull(heightInput.getEditText()).setText("");
        Objects.requireNonNull(weightInput.getEditText()).setText("");
        bmiDisplay.setText("");
    }
}