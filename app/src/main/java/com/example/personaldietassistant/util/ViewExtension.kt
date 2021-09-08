package com.example.personaldietassistant.util

import android.view.View

fun View.show(shouldShow: Boolean) {
    if (shouldShow) {
        this.visibility = View.VISIBLE
    } else {
        this.visibility = View.GONE
    }
}