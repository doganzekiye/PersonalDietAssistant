package com.example.personaldietassistant.ui

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.personaldietassistant.model.foodSearch.Hint

class FoodSearchViewModel : ViewModel() {
    val hintLiveData: MutableLiveData<Hint> = MutableLiveData()
    fun getHint(){

    }
}