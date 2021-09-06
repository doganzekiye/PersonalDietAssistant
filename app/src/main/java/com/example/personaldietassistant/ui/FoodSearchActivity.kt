package com.example.personaldietassistant.ui

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.View
import android.widget.EditText
import android.widget.ProgressBar
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.airbnb.lottie.LottieAnimationView
import com.example.personaldietassistant.R
import com.example.personaldietassistant.databinding.ActivityFoodSearchBinding
import com.example.personaldietassistant.model.foodSearch.FoodResponse
import com.example.personaldietassistant.model.foodSearch.Hint
import com.example.personaldietassistant.ui.adapter.SearchAdapter
import com.example.personaldietassistant.webService.FoodApi
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class FoodSearchActivity : AppCompatActivity() {
    lateinit var editTextDoctor: EditText
    lateinit var searchLoadingAnimationView: LottieAnimationView
    lateinit var foodSearchRecyclerView: RecyclerView
    lateinit var adapter: SearchAdapter
    var searchResult: MutableList<Hint> = mutableListOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding: ActivityFoodSearchBinding = DataBindingUtil.setContentView(this, R.layout.activity_food_search)
        foodSearchRecyclerView = binding.foodSearchRecyclerView
        editTextDoctor = binding.editTextSearchFilter
        searchLoadingAnimationView = binding.loadingAnimation
        setListener()

    }

    fun getFoodSearch(keyword: String) {
        searchLoadingAnimationView.visibility = View.VISIBLE
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
                val responseBody = response.body()
                setAdapterList()
                if (response.code() == 400) {
                    adapter.clearAdapterList()
                } else {
                    if (responseBody != null) {
                        searchResult = responseBody.hints.toMutableList()
                        adapter.filterList(searchResult)
                    }
                }
                searchLoadingAnimationView.visibility = View.GONE
            }

            override fun onFailure(
                call: Call<FoodResponse?>,
                t: Throwable
            ) { //if response does not exist -> t
                Log.d("error", t.toString())
                searchLoadingAnimationView.visibility = View.GONE
            }

        })
    }

    private fun setListener() {

        editTextDoctor.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {}
            override fun afterTextChanged(s: Editable) {
                getFoodSearch(s.toString())
            }
        })
    }

    fun setAdapterList() {
        adapter = SearchAdapter(searchResult)
        // Attach the adapter to the recyclerview to populate items
        foodSearchRecyclerView.adapter = adapter
    }
}