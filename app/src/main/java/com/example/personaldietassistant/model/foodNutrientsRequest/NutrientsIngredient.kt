package com.example.personaldietassistant.model.foodNutrientsRequest

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class NutrientsIngredient (
    val foodId: String,
    val measureURI: String,
    val quantity: Double = 1.0
): Parcelable