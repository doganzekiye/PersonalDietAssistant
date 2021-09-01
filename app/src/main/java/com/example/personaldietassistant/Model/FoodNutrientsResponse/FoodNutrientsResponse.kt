package com.example.personaldietassistant.Model.FoodNutrientsResponse

data class FoodNutrientsResponse(
    val calories: Double,
    val cautions: List<String>,
    val dietLabels: List<Any>,
    val healthLabels: List<String>,
    val ingredients: List<Ingredient>,
    val totalDaily: TotalDaily,
    val totalNutrients: TotalNutrients,
    val totalWeight: Double,
    val uri: String
)