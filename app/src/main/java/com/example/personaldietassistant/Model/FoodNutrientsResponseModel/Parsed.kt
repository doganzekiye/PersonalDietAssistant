package com.example.personaldietassistant.Model.FoodNutrientsResponseModel

data class Parsed(
    val food: String,
    val foodId: String,
    val measure: String,
    val measureURI: String,
    val quantity: Double,
    val retainedWeight: Double,
    val status: String,
    val weight: Double
)