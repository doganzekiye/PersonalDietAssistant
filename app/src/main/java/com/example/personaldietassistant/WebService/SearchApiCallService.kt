package com.example.personaldietassistant.WebService

import android.util.Log
import com.example.personaldietassistant.Model.FoodSearchModel.FoodResponse
import com.example.personaldietassistant.databinding.ActivityMainBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class SearchApiCallService {
    lateinit var binding: ActivityMainBinding

    fun getFoods(keyword: String) {
        FoodApi.create().getFoods(
            FoodApi.EDAMAM_ID,
            FoodApi.EDAMAM_KEY,
            keyword,
            FoodApi.NUTRITION_TYPE
        ).enqueue(object : Callback<FoodResponse?> {
            override fun onResponse(
                call: Call<FoodResponse?>,
                response: Response<FoodResponse?>
            ) { // if response exist -> response
                val responseBody = response?.body()
                if (responseBody != null) {
                    Log.d("searchApiResponse", response.toString())
                    responseBody
                }
            }

            override fun onFailure(
                call: Call<FoodResponse?>,
                t: Throwable
            ) { //if response does not exist -> t
                Log.d("error", t.toString())
            }
        })
    }
}