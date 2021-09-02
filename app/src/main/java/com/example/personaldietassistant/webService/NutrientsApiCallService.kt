package com.example.personaldietassistant.webService

import android.util.Log
import com.example.personaldietassistant.model.foodNutrientsRequest.FoodNutrientsRequest
import com.example.personaldietassistant.model.foodNutrientsRequest.NutrientsIngredient
import com.example.personaldietassistant.model.foodNutrientsResponse.FoodNutrientsResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class NutrientsApiCallService {

    fun getFoodNutrients(quantity :Double = 1.0) {//foodId: String, measureURI: String, quantity: Double
        val request = FoodNutrientsRequest(
            ingredients = listOf(
                NutrientsIngredient(
                    foodId = "food_bjsfxtcaidvmhaa3afrbna43q3hu",
                    measureURI = "http://www.edamam.com/ontologies/edamam.owl#Measure_serving",
                    quantity = quantity
                )
            )

        )

        FoodApi.create().getFoodNutrients(
            request,
            FoodApi.EDAMAM_ID,
            FoodApi.EDAMAM_KEY,
        ).enqueue(object : Callback<FoodNutrientsResponse?> {
            override fun onResponse(
                call: Call<FoodNutrientsResponse?>,
                response: Response<FoodNutrientsResponse?>
            ) { // if response exist -> response
                val responseBody = response.body()
                val stringBuilder = StringBuilder()
                if (responseBody != null) {
                    Log.d("nutrientApiResponse", response.toString())
                    responseBody
                }
            }

            override fun onFailure(
                call: Call<FoodNutrientsResponse?>,
                t: Throwable
            ) { //if response does not exist -> t
                Log.d("error", t.toString())
            }
        })
    }
}