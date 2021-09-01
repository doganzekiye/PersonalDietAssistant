package com.example.personaldietassistant.Model.FoodNutrientsRequestModel

data class NutrientsIngredient(
    val foodId: String,
    val measureURI: String,
    val quantity: Double
)