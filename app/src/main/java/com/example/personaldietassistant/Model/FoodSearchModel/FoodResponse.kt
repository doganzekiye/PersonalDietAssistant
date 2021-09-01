package com.example.personaldietassistant.Model.FoodSearchModel

data class FoodResponse(
    val _links: Links,
    val hints: List<Hint>,
    val parsed: List<Parsed>,
    val text: String
)