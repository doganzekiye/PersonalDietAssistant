package com.example.personaldietassistant.data

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface UserDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE) // ignores when there is exactly two user
    suspend fun addUser(user: User) // for coroutine

    @Query("SELECT * FROM user_table ORDER BY id ASC")
    fun getUser(): LiveData<List<User>>

    @Delete
    fun deleteUser(user: User)

    @Update
    fun updateUser(user: User)
}