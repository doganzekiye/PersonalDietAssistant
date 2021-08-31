package com.example.personaldietassistant

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.personaldietassistant.Model.Food
import com.example.personaldietassistant.WebService.FoodApiService
import com.example.personaldietassistant.databinding.ActivityMainBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        getFoods("banana")
    }

    fun getFoods(keyword: String) {

        FoodApiService.create().getFoods(
            FoodApiService.EDAMAM_ID,
            FoodApiService.EDAMAM_KEY,
            keyword,
            FoodApiService.NUTRITION_TYPE
        ).enqueue(object : Callback<Food?> {
            override fun onResponse(
                call: Call<Food?>,
                response: Response<Food?>
            ) { // if response exist -> response
                val responseBody = response?.body()
                val stringBuilder = StringBuilder()
                if(responseBody != null){
                    //responseBody
                        for(foodLabel in responseBody.hints){
                            stringBuilder.append(foodLabel.food.label)
                            stringBuilder.append("\n")
                        }

                }
                binding.foodText.text = stringBuilder

            }

            override fun onFailure(
                call: Call<Food?>,
                t: Throwable
            ) { //if response does not exist -> t
                Log.d("error", t.toString())
            }
        })
    }
}