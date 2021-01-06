package com.sourishgupta.bmi_calculator;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;

import java.text.DecimalFormat;

public class MainActivity extends AppCompatActivity {
    private RadioButton male;
    private RadioButton female;
    private EditText age;
    private EditText feet;
    private EditText inches;
    private EditText weight;
    private TextView result;
    private Button calculate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        find();
        setupButtonOnClickListener();
    }

    private void find() {
        male = findViewById(R.id.radio_button_male);
        female = findViewById(R.id.radio_button_female);
        age = findViewById(R.id.edit_text_age);
        feet = findViewById(R.id.edit_text_feet);
        inches = findViewById(R.id.edit_text_inches);
        weight = findViewById(R.id.edit_text_weight);
        result = findViewById(R.id.text_view_result);
        calculate = findViewById(R.id.button_calculate);
    }

    private void setupButtonOnClickListener() {
        calculate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                double bmiResult = calculateBmi();
                int ageInt = Integer.parseInt(age.getText().toString());
                if (ageInt >= 18) {
                    displayResult(bmiResult);
                } else {
                    displayGuidance(bmiResult);
                }
            }
        });
    }

    private double calculateBmi() {
        double hMeters = (Integer.parseInt(feet.getText().toString()) * 12 + Integer.parseInt(inches.getText().toString())) * 0.0254;
        return Integer.parseInt(weight.getText().toString()) / (hMeters * hMeters);
    }

    private void displayResult(double bmi) {
        DecimalFormat formatter = new DecimalFormat("0.00");
        String finalResultText;
        if (bmi < 18.5) {
            finalResultText = formatter.format(bmi) + " - " + "You are underweight";
        } else if (bmi > 25) {
            finalResultText = formatter.format(bmi) + " - " + "You are overweight";
        } else {
            finalResultText = formatter.format(bmi) + " - " + "You are a healthy weight";
        }

        result.setText(finalResultText);
    }

    private void displayGuidance(double bmi) {
        DecimalFormat formatter = new DecimalFormat("0.00");
        String finalResultText;
        if (male.isChecked()) {
            finalResultText = formatter.format(bmi) + " - " + "As you are under 18, please contact your doctor for the healthy range for boys";
        } else if (female.isChecked()) {
            finalResultText = formatter.format(bmi) + " - " + "As you are under 18, please contact your doctor for the healthy range for girls";
        } else {
            finalResultText = formatter.format(bmi) + " - " + "As you are under 18, please contact your doctor for the healthy range";
        }

        result.setText(finalResultText);
    }
}