package com.example.personaldietassistant.WebService

import com.example.personaldietassistant.Model.FoodNutrientsRequest.FoodNutrientsRequest
import com.example.personaldietassistant.Model.FoodNutrientsResponse.FoodNutrientsResponse
import com.example.personaldietassistant.Model.FoodSearch.FoodResponse
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.*


interface FoodApi {

    @GET("parser") //endpoint of url
    fun getFoods(
        @Query("app_id") appId: String,
        @Query("app_key") appKey: String,
        @Query("ingr") keyword: String,
        @Query("nutrition-type") nutritionType: String
    ): Call<FoodResponse>

    @Headers("Content-Type: application/json")
    @POST("nutrients")
    fun getFoodNutrients(
        @Body foodNutrientsRequest: FoodNutrientsRequest,
        @Query("app_id") appId: String,
        @Query("app_key") appKey: String,
    ): Call<FoodNutrientsResponse>

    companion object {//static values of the class

        var BASE_URL = "https://api.edamam.com/api/food-database/v2/"
        val EDAMAM_ID = "6a85796a"
        val EDAMAM_KEY = "c17c26a3424687a1bc6f68ee9061ea43"
        val NUTRITION_TYPE = "logging"

        fun create(): FoodApi {

            //create retrofit builder object
            val retrofit = Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create()) //to attach the data from json file to models
                .baseUrl(BASE_URL)
                .build()
            return retrofit.create(FoodApi::class.java) //where the declared GET request

        }
    }
}