package com.example.foodapp;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.textfield.TextInputEditText;

public class WelcomeActivity extends AppCompatActivity {

    TextInputEditText name, age, height, weight;
    RadioGroup genderGroup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);

        name = findViewById(R.id.nameInput);
        age = findViewById(R.id.ageInput);
        height = findViewById(R.id.heightInput);
        weight = findViewById(R.id.weightInput);
        genderGroup = findViewById(R.id.genderGroup);
        Button next = findViewById(R.id.nextBtn);

        next.setOnClickListener(v -> {
            if (name.getText().toString().isEmpty()) {
                Toast.makeText(this, "Enter all details", Toast.LENGTH_SHORT).show();
                return;
            }

            int genderId = genderGroup.getCheckedRadioButtonId();
            RadioButton genderBtn = findViewById(genderId);

            Intent intent = new Intent(this, HomeActivity.class);
            intent.putExtra("name", name.getText().toString());
            intent.putExtra("age", Integer.parseInt(age.getText().toString()));
            intent.putExtra("height", Float.parseFloat(height.getText().toString()));
            intent.putExtra("weight", Float.parseFloat(weight.getText().toString()));
            intent.putExtra("gender", genderBtn.getText().toString());
            startActivity(intent);
        });
    }
}
