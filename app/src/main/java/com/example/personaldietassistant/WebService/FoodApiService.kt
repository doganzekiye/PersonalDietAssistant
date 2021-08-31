package com.example.personaldietassistant.WebService

import com.example.personaldietassistant.Model.Food
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query


interface FoodApiService {

    @GET("parser")
    fun getFoods(
        @Query("app_id") appId: String,
        @Query("app_key") appKey: String,
        @Query("ingr") keyword: String,
        @Query("nutrition-type") nutritionType: String
    ): Call<Food>

    companion object {//static values of the class

        var BASE_URL = "https://api.edamam.com/api/food-database/v2/"
        val EDAMAM_ID = "6a85796a"
        val EDAMAM_KEY = "c17c26a3424687a1bc6f68ee9061ea43"
        val NUTRITION_TYPE = "logging"

        fun create(): FoodApiService {

            val retrofit = Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create()) //to attach the data from json file to models
                .baseUrl(BASE_URL)
                .build()
            return retrofit.create(FoodApiService::class.java)

        }
    }
}