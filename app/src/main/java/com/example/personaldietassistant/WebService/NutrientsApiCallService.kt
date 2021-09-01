package com.example.personaldietassistant.WebService

import android.util.Log
import com.example.personaldietassistant.Model.FoodNutrientsRequestModel.FoodNutrientsRequest
import com.example.personaldietassistant.Model.FoodNutrientsRequestModel.NutrientsIngredient
import com.example.personaldietassistant.Model.FoodNutrientsResponseModel.FoodNutrientsResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class NutrientsApiCallService {

    fun getFoodNutrients() {
        val request = FoodNutrientsRequest(
            ingredients = listOf(NutrientsIngredient(
                foodId = "food_bjsfxtcaidvmhaa3afrbna43q3hu",
                measureURI = "http://www.edamam.com/ontologies/edamam.owl#Measure_serving",
                quantity = 2.0
            ))

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
                val responseBody = response?.body()
                val stringBuilder = StringBuilder()
                if(responseBody != null){
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