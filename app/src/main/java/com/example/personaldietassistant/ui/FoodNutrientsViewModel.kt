package com.example.personaldietassistant.ui

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.personaldietassistant.model.foodNutrientsRequest.FoodNutrientsRequest
import com.example.personaldietassistant.model.foodNutrientsRequest.NutrientsIngredient
import com.example.personaldietassistant.model.foodNutrientsResponse.FoodNutrientsResponse
import com.example.personaldietassistant.webService.FoodApi
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class FoodNutrientsViewModel : ViewModel() {
    val nutrientsLiveData: MutableLiveData<FoodNutrientsResponse> = MutableLiveData()
    fun getFoodNutrients(
        foodId: String,
        measureURI: String,
        quantity: Double = 1.0
    ) {
        val request = FoodNutrientsRequest(
            ingredients = listOf(
                NutrientsIngredient(
                    foodId = foodId,
                    measureURI = measureURI,
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
                responseBody?.let {
                    nutrientsLiveData.value = responseBody
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