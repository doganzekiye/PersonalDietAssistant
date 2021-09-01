package com.example.personaldietassistant

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.personaldietassistant.WebService.NutrientsApiCallService
import com.example.personaldietassistant.WebService.SearchApiCallService
import com.example.personaldietassistant.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val searchApiCallService = SearchApiCallService()
        searchApiCallService.getFoods("banana")
        val nutrientsApiCallService = NutrientsApiCallService()
        nutrientsApiCallService.getFoodNutrients()
    }
}