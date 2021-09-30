package com.example.personaldietassistant.ui.search

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.personaldietassistant.model.foodSearch.FoodResponse
import com.example.personaldietassistant.model.foodSearch.Hint
import com.example.personaldietassistant.webService.FoodApi
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class FoodSearchViewModel : ViewModel() {
    val hintLiveData: MutableLiveData<List<Hint>> = MutableLiveData()
    fun getFoodSearch(keyword: String, showLoading: (Boolean) -> Unit) {
        showLoading.invoke(true) // for loading image
        FoodApi.create().getFoods(
            FoodApi.EDAMAM_ID,
            FoodApi.EDAMAM_KEY,
            keyword,
            FoodApi.NUTRITION_TYPE
        ).enqueue(object : Callback<FoodResponse?> {
            override fun onResponse(
                call: Call<FoodResponse?>,
                response: Response<FoodResponse?>
            ) {
                showLoading.invoke(false)
                val responseBody = response.body()
                if (response.code() == 400) {
                    hintLiveData.value = emptyList()
                } else {
                    if (responseBody != null) {
                        hintLiveData.value = response.body()?.hints
                    }
                }
            }

            override fun onFailure(
                call: Call<FoodResponse?>,
                t: Throwable
            ) {
                showLoading.invoke(false)
                Log.d("error", t.toString())
            }
        })
    }
}