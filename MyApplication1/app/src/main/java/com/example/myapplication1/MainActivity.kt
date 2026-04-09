package com.example.myapplication1

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val foodName = findViewById<EditText>(R.id.foodName)
        val quantity = findViewById<EditText>(R.id.quantity)
        val unit = findViewById<EditText>(R.id.unit)
        val button = findViewById<Button>(R.id.calcButton)
        val result = findViewById<TextView>(R.id.resultText)

        button.setOnClickListener {

            val food = foodName.text.toString().lowercase()
            val qtyText = quantity.text.toString()
            val unitText = unit.text.toString().lowercase()

            if (food.isEmpty() || qtyText.isEmpty() || unitText.isEmpty()) {
                result.text = "Please enter all details"
                return@setOnClickListener
            }

            val qty = qtyText.toDouble()


            val grams = if (unitText == "kg") qty * 1000 else qty

            val caloriesPer100g: Double
            val proteinPer100g: Double
            val carbsPer100g: Double
            val fatPer100g: Double

            when (food) {
                "apple" -> {
                    caloriesPer100g = 52.0
                    proteinPer100g = 0.3
                    carbsPer100g = 14.0
                    fatPer100g = 0.2
                }

                "banana" -> {
                    caloriesPer100g = 89.0
                    proteinPer100g = 1.1
                    carbsPer100g = 23.0
                    fatPer100g = 0.3
                }

                "mango" -> {
                    caloriesPer100g = 60.0
                    proteinPer100g = 0.8
                    carbsPer100g = 15.0
                    fatPer100g = 0.4
                }

                "orange" -> {
                    caloriesPer100g = 47.0
                    proteinPer100g = 0.9
                    carbsPer100g = 12.0
                    fatPer100g = 0.1
                }

                "kiwi" -> {
                    caloriesPer100g = 61.0
                    proteinPer100g = 1.1
                    carbsPer100g = 15.0
                    fatPer100g = 0.5
                }

                else -> {
                    caloriesPer100g = 50.0
                    proteinPer100g = 0.5
                    carbsPer100g = 12.0
                    fatPer100g = 0.2
                }
            }
            val factor = grams / 100

            val totalCalories = caloriesPer100g * factor
            val protein = proteinPer100g * factor
            val carbs = carbsPer100g * factor
            val fat = fatPer100g * factor

            result.text =
                "Food: ${food.replaceFirstChar { it.uppercase() }}\n" +
                        "Quantity: $grams g\n\n" +
                        "Calories: %.2f kcal\n".format(totalCalories) +
                        "Protein: %.2f g\n".format(protein) +
                        "Carbohydrates: %.2f g\n".format(carbs) +
                        "Fat: %.2f g".format(fat)
        }
    }
}
