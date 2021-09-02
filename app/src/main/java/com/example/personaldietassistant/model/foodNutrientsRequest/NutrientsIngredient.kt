package com.example.personaldietassistant.model.foodNutrientsRequest

data class NutrientsIngredient(
    val foodId: String,
    val measureURI: String,
    val quantity: Double = 1.0
)