package com.example.personaldietassistant.data

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class UserViewModel(application: Application) : AndroidViewModel(application) {
    private val readAllData : LiveData<List<User>>
    private val repository : UserRepository
    init{//first execution of class
        val userDao = UserDatabase.getDatabase(application).userDao()
        repository = UserRepository(userDao)
        readAllData = repository.readAllData
    }
    fun addUser(user : User){//coroutine part
        viewModelScope.launch(Dispatchers.IO) {
            repository.addUser(user)
        }
    }
}