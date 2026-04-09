package com.example.foodapp;

import android.os.Bundle;
import android.widget.*;
import androidx.appcompat.app.AppCompatActivity;

import java.util.HashMap;

public class HomeActivity extends AppCompatActivity {

    TextView welcomeText, resultText, totalText;
    Spinner foodSpinner;
    EditText quantityInput;
    Button calcBtn;

    String userName, gender;
    int age;
    double height, weight;

    double totalCalories = 0;
    double dailyLimit;

    HashMap<String, Double> foodCalories = new HashMap<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        // Receive data
        userName = getIntent().getStringExtra("name");
        gender = getIntent().getStringExtra("gender");
        age = getIntent().getIntExtra("age", 20);
        height = getIntent().getDoubleExtra("height", 170);
        weight = getIntent().getDoubleExtra("weight", 60);

        // UI
        welcomeText = findViewById(R.id.welcomeText);
        foodSpinner = findViewById(R.id.foodSpinner);
        quantityInput = findViewById(R.id.quantityInput);
        calcBtn = findViewById(R.id.calcBtn);
        resultText = findViewById(R.id.resultText);
        totalText = findViewById(R.id.totalText);

        welcomeText.setText("Welcome, " + userName);

        // Food calories per 100g
        foodCalories.put("Apple", 52.0);
        foodCalories.put("Banana", 89.0);
        foodCalories.put("Orange", 47.0);
        foodCalories.put("Mango", 60.0);
        foodCalories.put("Kiwi", 61.0);
        foodCalories.put("Rice", 130.0);
        foodCalories.put("Bread", 265.0);
        foodCalories.put("Egg", 155.0);
        foodCalories.put("Chicken", 239.0);
        foodCalories.put("Milk", 42.0);
        foodCalories.put("Paneer", 265.0);
        foodCalories.put("Potato", 77.0);

        ArrayAdapter<String> adapter = new ArrayAdapter<>(
                this,
                android.R.layout.simple_spinner_dropdown_item,
                foodCalories.keySet().toArray(new String[0])
        );
        foodSpinner.setAdapter(adapter);

        // Daily calorie limit
        dailyLimit = gender.equals("Male") ? 2500 : 2000;
        if (age < 18) dailyLimit += 300;
        if (age > 50) dailyLimit -= 300;

        calcBtn.setOnClickListener(v -> calculateCalories());
    }

    private void calculateCalories() {

        String food = foodSpinner.getSelectedItem().toString();
        String qtyStr = quantityInput.getText().toString();

        if (qtyStr.isEmpty()) {
            Toast.makeText(this, "Enter quantity", Toast.LENGTH_SHORT).show();
            return;
        }

        double quantity = Double.parseDouble(qtyStr);
        double calPer100g = foodCalories.get(food);

        double calories = (calPer100g / 100) * quantity;

        // Gender & age adjustment
        if (gender.equals("Male")) calories *= 1.05;
        if (age < 18) calories *= 1.1;
        if (age > 50) calories *= 0.9;

        totalCalories += calories;
        double remainingCalories = dailyLimit - totalCalories;

        // BMI
        double heightM = height / 100;
        double bmi = weight / (heightM * heightM);

        String bmiStatus;
        if (bmi < 18.5) bmiStatus = "Very Low";
        else if (bmi < 25) bmiStatus = "Normal";
        else if (bmi < 30) bmiStatus = "Obese";
        else bmiStatus = "Very Obese";

        // DISPLAY OUTPUT
        resultText.setText(
                "Food: " + food +
                        "\nQuantity: " + quantity + " g" +
                        "\nCalories Added: " + String.format("%.2f", calories) + " kcal" +
                        "\n\nBMI: " + String.format("%.1f", bmi) +
                        "\nStatus: " + bmiStatus +
                        "\n\nRemaining Calories: " +
                        String.format("%.2f", remainingCalories) + " kcal"
        );

        totalText.setText(
                "Total Calories Today: " +
                        String.format("%.2f", totalCalories) + " kcal"
        );

        quantityInput.setText("");
    }
}
