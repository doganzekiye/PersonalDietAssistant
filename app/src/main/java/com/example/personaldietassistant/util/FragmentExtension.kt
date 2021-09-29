package com.example.personaldietassistant.util

import android.view.View
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import com.example.personaldietassistant.R
import com.google.android.material.snackbar.Snackbar

fun Fragment.showMessage(text: String) {
    Toast.makeText(this.context, text, Toast.LENGTH_SHORT).show()
}

fun Fragment.showMessage(view: View, text: String) {
    val snack = Snackbar.make(view, text, Snackbar.LENGTH_SHORT)
    snack.setBackgroundTint(ContextCompat.getColor(view.context, R.color.black))
    snack.setTextColor(ContextCompat.getColor(view.context, R.color.white))
    snack.show()
}