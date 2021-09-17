package com.example.personaldietassistant.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.example.personaldietassistant.R
import com.example.personaldietassistant.databinding.ActivityFoodNutrientsBinding
import com.example.personaldietassistant.model.foodNutrientsRequest.NutrientsIngredient

class FoodNutrientsActivity : AppCompatActivity() {
    lateinit var nutrientsViewModel: FoodNutrientsViewModel
    lateinit var binding: ActivityFoodNutrientsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_food_nutrients)
        nutrientsViewModel = ViewModelProvider(this).get(FoodNutrientsViewModel::class.java)
        val dataForRequest = intent.getParcelableExtra<NutrientsIngredient>("data")
        nutrientsViewModel.getFoodNutrients(dataForRequest!!.foodId, dataForRequest!!.measureURI)
        observeFoodNutrients()
    }

    private fun observeFoodNutrients() {
        nutrientsViewModel.nutrientsLiveData.observe(this, { nutrients ->
            val textValue = "cal: ${String.format("%.2f", nutrients.calories)} " + "fat: ${
                String.format(
                    "%.2f",
                    nutrients.totalDaily.FAT.quantity
                )
            }"
            binding.nutrientsTxt.text = textValue

        })
    }
}