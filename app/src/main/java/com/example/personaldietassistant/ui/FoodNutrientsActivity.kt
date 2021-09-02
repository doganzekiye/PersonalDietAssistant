package com.example.personaldietassistant.ui

import android.os.Bundle
import android.util.Log
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.personaldietassistant.R
import com.example.personaldietassistant.model.foodNutrientsRequest.FoodNutrientsRequest
import com.example.personaldietassistant.model.foodNutrientsRequest.NutrientsIngredient
import com.example.personaldietassistant.model.foodNutrientsResponse.FoodNutrientsResponse
import com.example.personaldietassistant.webService.FoodApi
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class FoodNutrientsActivity : AppCompatActivity() {
    lateinit var testTxt: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_food_nutrients)

        val dataX = intent.getParcelableExtra<NutrientsIngredient>("data")
        val titleTextView: TextView = findViewById(R.id.nutrients_txt)
        testTxt = titleTextView
        getFoodNutrients(dataX!!.foodId, dataX!!.measureURI)

    }

    fun getFoodNutrients(
        foodId: String,
        measureURI: String,
        quantity: Double = 1.0
    ) {//foodId: String, measureURI: String, quantity: Double
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
                    testTxt.text = "cal: ${it.calories}  weight: ${it.totalWeight}"
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