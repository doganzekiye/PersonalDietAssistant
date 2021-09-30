package com.example.personaldietassistant.data

data class DailySummary(
    val date: String, val totalWater: Double,
    val totalSteps: Int, val totalCal: Double
)