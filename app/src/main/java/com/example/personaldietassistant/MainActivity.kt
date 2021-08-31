package com.example.personaldietassistant

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.personaldietassistant.Model.Food
import com.example.personaldietassistant.WebService.FoodApiService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        getFoods("banana")
        setContentView(R.layout.activity_main)
    }

    fun getFoods(keyword : String) {

        FoodApiService.create().getFoods(
            FoodApiService.EDAMAM_ID,
            FoodApiService.EDAMAM_KEY,
            keyword,
            FoodApiService.NUTRITION_TYPE
        ).enqueue(object : Callback<Food> {
            override fun onResponse(
                call: Call<Food>?,
                response: Response<Food>?
            ) {
                if (response?.body() != null) {
                    response?.body()
                }
            }

            override fun onFailure(call: Call<Food>?, t: Throwable?) {
                Log.d("error", t.toString())
            }
        })
    }
}