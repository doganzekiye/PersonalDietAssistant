package com.example.personaldietassistant.util

import android.content.Context
import android.view.View
import android.widget.Toast
import androidx.annotation.ColorRes
import androidx.core.content.ContextCompat
import com.example.personaldietassistant.R
import com.google.android.material.snackbar.Snackbar

fun Context.showMessage(text: String) {
    Toast.makeText(this, text, Toast.LENGTH_SHORT).show()
}

fun Context.showMessage(view: View, text: String) {
    val snack = Snackbar.make(view, text, Snackbar.LENGTH_SHORT)
    snack.setBackgroundTint(ContextCompat.getColor(view.context, R.color.black))
    snack.setTextColor(ContextCompat.getColor(view.context, R.color.white))
    snack.show()
}

fun Context.getColorRes(@ColorRes color: Int): Int {
    return ContextCompat.getColor(this, color)
}