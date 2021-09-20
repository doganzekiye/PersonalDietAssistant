package com.example.personaldietassistant.ui.base

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.example.personaldietassistant.R


/**
 * Created by Akın DEMİR on 20.09.2021.
 * Copyright (c) 2021
 */


open class BaseFragment: Fragment() {

    protected fun setToolbar(toolbar: View, title: String = "", onClick: () -> Unit) {
        val backButton = toolbar.findViewById<ImageView>(R.id.backButton)
        backButton.setOnClickListener {
            onClick.invoke()
        }
        val titleView = toolbar.findViewById<TextView>(R.id.toolbarTitle)
        titleView.text = title
    }
}