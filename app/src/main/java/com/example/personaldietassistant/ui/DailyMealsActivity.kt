package com.example.personaldietassistant.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.example.personaldietassistant.R
import com.example.personaldietassistant.databinding.ActivityDailyMealsBinding
import com.example.personaldietassistant.model.MealDescItem
import com.example.personaldietassistant.ui.adapter.MealBindingAdapter

class DailyMealsActivity : AppCompatActivity() {
    lateinit var binding: ActivityDailyMealsBinding
    lateinit var adapter: MealBindingAdapter
    val testMeal: MealDescItem = MealDescItem(
        name = "Kahvaltı",
        desc = "yumurta, süt, çay, peynir",
        carbsCal = 1234.0,
        fatCal = 104.0,
        proteinCal = 86.2
    )
    val testMeal2: MealDescItem = MealDescItem(
        name = "Akşam",
        desc = "tavuk, makarna, ayran",
        carbsCal = 5.0,
        fatCal = 9.0,
        proteinCal = 10.0
    )
    val testMeal3: MealDescItem = MealDescItem(
        name = "Öğle",
        desc = "yumurta, süt, çay, peynir",
        carbsCal = 123.0,
        fatCal = 104.0,
        proteinCal = 86.2
    )
    val testMeal4: MealDescItem = MealDescItem(
        name = "Ara öğün",
        desc = "yumurta, süt, çay, peynir",
        carbsCal = 134.0,
        fatCal = 104.0,
        proteinCal = 86.2
    )
    var test: MutableList<MealDescItem> = mutableListOf(testMeal, testMeal2, testMeal3, testMeal4)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(
            this, R.layout.activity_daily_meals
        )
        /*testViewModel.mUser.observe(this, {
            setAdapterList(it.meals)
        })*/
        setAdapterList(test)
    }

    private fun setAdapterList(meals: List<MealDescItem>) {
        adapter = MealBindingAdapter(meals.toMutableList())
        // Attach the adapter to the recyclerview to populate items
        binding.rvMealDetails.adapter = adapter
    }
}