package com.example.personaldietassistant.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "user_table")
data class User(
    @PrimaryKey(autoGenerate = true)// generates unique ids automatically
    val id: Int = 0,
    val name: String,
    val height: Double,
    val weight: Double,
    val gender: String,
    val age: Int,
    val dailyCal: Int,
    val targetWeight: Double
) {

}