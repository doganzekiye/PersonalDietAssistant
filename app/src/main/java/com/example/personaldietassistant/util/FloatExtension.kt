package com.example.personaldietassistant.util

fun Float.getDecimal(): Int {
    val indexOfDecimal: Int = this.toString().indexOf(".")
    return String.format(this.toString().substring(indexOfDecimal + 1),2).toInt()
}

fun Float.getNumber(): Int {
    return this.toInt()
}