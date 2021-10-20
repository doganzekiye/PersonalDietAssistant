package com.example.personaldietassistant.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "user_table")
data class User(
    @PrimaryKey(autoGenerate = true)// generates unique ids automatically
    val id: Int = 0,
    var name: String = "",
    var height: Float = 150f,
    var weight: Float = 70f,
    var gender: String = "",
    var age: Int = 18,
    var dailyCal: Int = 0,
    var targetWeight: Float = 0f,
    var recommendedCal: Int = 0,
)