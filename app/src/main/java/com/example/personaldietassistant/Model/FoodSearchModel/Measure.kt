package com.example.personaldietassistant.Model.FoodSearchModel

data class Measure(
    val label: String,
    val qualified: List<Qualified>,
    val uri: String,
    val weight: Double
)