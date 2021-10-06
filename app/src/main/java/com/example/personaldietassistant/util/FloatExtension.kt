package com.example.personaldietassistant.util

import kotlin.math.roundToInt

fun Float.getDecimal(): Int {
    val indexOfDecimal: Int = this.toString().indexOf(".")
    return String.format(this.toString().substring(indexOfDecimal + 1,indexOfDecimal + 2)).toInt()
}

fun Float.getNumber(): Int {
    return this.toInt()
}