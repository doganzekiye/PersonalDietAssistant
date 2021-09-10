package com.example.personaldietassistant.data

import androidx.lifecycle.LiveData

class UserRepository(private val userDao : UserDao) {

    val readAllData: LiveData<List<User>> = userDao.readData()
    suspend fun addUser(user : User){
       userDao.addUser(user)
    }
}