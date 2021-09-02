package com.example.personaldietassistant.model.foodSearch

data class FoodResponse(
    val _links: Links,
    val hints: List<Hint>,
    val parsed: List<Parsed>,
    val text: String
)