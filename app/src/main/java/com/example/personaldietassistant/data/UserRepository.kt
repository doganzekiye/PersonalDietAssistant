package com.example.personaldietassistant.data

import androidx.lifecycle.LiveData

class UserRepository(private val userDao: UserDao) {

    val getAllUser: LiveData<List<User>> = userDao.getUser()
    suspend fun addUser(user: User) {
        userDao.addUser(user)
    }
}