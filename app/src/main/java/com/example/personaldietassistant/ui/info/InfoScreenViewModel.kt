package com.example.personaldietassistant.ui.info

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.personaldietassistant.data.User

class InfoScreenViewModel : ViewModel() {
    var user: User = User(
        name = "",
        height = 0.0f,
        weight = 0.0f,
        gender = "",
        age = 18,
        dailyCal = 0,
        targetWeight = 0.0f
    )

    var isNameValid : MutableLiveData<Boolean> = MutableLiveData()
    var userName: MutableLiveData<String> = MutableLiveData()

    init {
        userName.value = "Hadi seni tanıyalım.."
    }
}