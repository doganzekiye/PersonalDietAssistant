package com.example.personaldietassistant.Model

data class Food(
    val _links: Links,
    val hints: List<Hint>,
    val parsed: List<Parsed>,
    val text: String
)