package com.example.personaldietassistant.ui

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import com.example.personaldietassistant.R
import com.example.personaldietassistant.databinding.ActivityDailyBinding
import com.example.personaldietassistant.ui.search.FoodSearchActivity

class DailyActivity : AppCompatActivity() {
    lateinit var binding: ActivityDailyBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(
            this, R.layout.activity_daily
        )
        binding.cpbRemainCal.cpProgress.progress = 80f
        binding.cpbRemainCal.cpProgress.backgroundProgressBarWidth = 6f
        binding.cpbRemainCal.cpProgress.progressBarWidth = 6f
        binding.cpbRemainCal.cpProgress
        binding.cpbRemainCal.cpProgress.progressBarColor = ContextCompat.getColor(
            binding.root.context, R.color.green
        )
        binding.cpbTakenCarbs.cpProgress.progress = 60f
        binding.cpbTakenCarbs.cpProgress.progressBarColor = ContextCompat.getColor(
            binding.root.context, R.color.orange
        )
        binding.cpbTakenFat.cpProgress.progress = 40f
        binding.cpbTakenFat.cpProgress.progressBarColor = ContextCompat.getColor(
            binding.root.context, R.color.purple
        )
        binding.cpbTakenProtein.cpProgress.progress = 55f
        binding.cpbTakenProtein.cpProgress.progressBarColor = ContextCompat.getColor(
            binding.root.context, R.color.red
        )
        binding.cpbTakenWater.cpProgress.progress = 90f
        binding.cpbTakenWater.cpProgress.progressBarColor = ContextCompat.getColor(
            binding.root.context, R.color.blue
        )
        binding.cpbTakenSteps.cpProgress.progress = 15f
        binding.cpbTakenSteps.cpProgress.progressBarColor = ContextCompat.getColor(
            binding.root.context, R.color.pink
        )

        binding.cpbRemainCal.tvProgressValue.textSize = 28f
        binding.cpbRemainCal.tvMeasurementType.textSize = 13f
        binding.cpbTakenWater.tvMeasurementType.text = "ml"
        setListener()
    }

    private fun setListener() {
        binding.cvCalender.setOnClickListener {
            val context = binding.cvCalender.context
            val intent = Intent(context, DailyMealsActivity::class.java)
            context.startActivity(intent)
        }
    }
}