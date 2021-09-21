package com.example.personaldietassistant.ui

import android.content.Intent
import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.example.personaldietassistant.R
import com.example.personaldietassistant.databinding.ActivityMainBinding
import com.example.personaldietassistant.ui.search.FoodSearchActivity

class MainActivity : AppCompatActivity() {
    lateinit var mainTextView: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding: ActivityMainBinding =
            DataBindingUtil.setContentView(this, R.layout.activity_main)
        mainTextView = binding.foodText
        setListener()
    }

    private fun setListener() {
        mainTextView.setOnClickListener {
            val context = mainTextView.context
            val intent = Intent(context, FoodSearchActivity::class.java)
            //intent.putExtra("doctorName", doctor.full_name)
            context.startActivity(intent)
        }
    }
}