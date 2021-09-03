package com.example.personaldietassistant.ui

import android.content.Intent
import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.example.personaldietassistant.R
import com.example.personaldietassistant.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    lateinit var mainTextView: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding: ActivityMainBinding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        mainTextView = binding.foodText
        setListener()
        //val nutrientsApiCallService = NutrientsApiCallService()
        //nutrientsApiCallService.getFoodNutrients()
    }

    private fun setListener() {

        mainTextView.setOnClickListener {
            val context = mainTextView.context
            val intent = Intent(context, FoodSearchActivity::class.java)

            //intent.putExtra("doctorName", doctor.full_name)
            // intent.putExtra("userStatus", doctor.user_status)
            //intent.putExtra("imageUrl", doctor.image.url)
            context.startActivity(intent)

        }
    }
}