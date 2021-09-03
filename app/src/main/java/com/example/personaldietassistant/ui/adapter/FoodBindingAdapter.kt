package com.example.personaldietassistant.ui.adapter

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.example.personaldietassistant.R

class FoodBindingAdapter {
    companion object{
        @JvmStatic
        @BindingAdapter("imageUrl")
        fun loadImage(view: ImageView, url: String?) {//target
            Glide.with(view.context).load(url).placeholder(R.drawable.ic_placeholder_food).error(R.drawable.ic_placeholder_food).into(view)
        }
    }
}