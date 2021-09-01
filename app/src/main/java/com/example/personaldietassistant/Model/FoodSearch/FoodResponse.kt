package com.example.personaldietassistant.Model.FoodSearch

data class FoodResponse(
    val _links: Links,
    val hints: List<Hint>,
    val parsed: List<Parsed>,
    val text: String
)