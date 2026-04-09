package com.example.word_app;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    TextView questionText;
    EditText answerInput;
    Button checkBtn, nextBtn;

    String[] days = {
            "Sunday", "Monday", "Tuesday",
            "Wednesday", "Thursday", "Friday", "Saturday"
    };

    String currentAnswer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        questionText = findViewById(R.id.questionText);
        answerInput = findViewById(R.id.answerInput);
        checkBtn = findViewById(R.id.checkBtn);
        nextBtn = findViewById(R.id.nextBtn);

        loadNewQuestion();

        checkBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String userAnswer = answerInput.getText().toString().trim();

                if (userAnswer.equalsIgnoreCase(currentAnswer)) {
                    Toast.makeText(MainActivity.this,
                            "Correct Answer ✅", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(MainActivity.this,
                            "Wrong ❌ Correct answer is " + currentAnswer,
                            Toast.LENGTH_SHORT).show();
                }
            }
        });

        nextBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                loadNewQuestion();
                answerInput.setText("");
            }
        });
    }

    private void loadNewQuestion() {
        Random random = new Random();
        int index = random.nextInt(days.length);
        currentAnswer = days[index];

        questionText.setText("Guess the Day: " + (index + 1) + " day of the week");
    }
}
