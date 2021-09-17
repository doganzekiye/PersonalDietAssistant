package com.example.personaldietassistant.data

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class UserViewModel(app: Application) : AndroidViewModel(app) {
    private val readAllData: LiveData<List<User>>
    private val repository: UserRepository

    init {//first execution of the class
        val userDao = UserDatabase.getDatabase(app).userDao()
        repository = UserRepository(userDao)
        readAllData = repository.getAllUser
    }

    fun addUser(user: User) {//coroutine part
        viewModelScope.launch(Dispatchers.IO) {
            repository.addUser(user)
        }
    }
}