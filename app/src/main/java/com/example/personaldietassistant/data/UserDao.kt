package com.example.personaldietassistant.data

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface UserDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE) // ignores when there is exactly two user
    suspend fun addUser(user: User) // for coroutine
    @Query("SELECT * FROM user_table ORDER BY id ASC")
    fun readData(): LiveData<List<User>>
}