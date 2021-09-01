package com.example.personaldietassistant.Model.FoodNutrientsRequest

data class NutrientsIngredient(
    val foodId: String,
    val measureURI: String,
    val quantity: Double
)