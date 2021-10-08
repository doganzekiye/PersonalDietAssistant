package com.example.personaldietassistant.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "user_table")
data class User(
    @PrimaryKey(autoGenerate = true)// generates unique ids automatically
    val id: Int = 0,
    var name: String,
    var height: Float,
    var weight: Float,
    var gender: String,
    var age: Int,
    var dailyCal: Int,
    var targetWeight: Float,
    var recommendedCal : Int
) {

}