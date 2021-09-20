package com.example.personaldietassistant.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.navigation.findNavController
import com.example.personaldietassistant.R
import com.example.personaldietassistant.databinding.ActivityInfoScreenBinding

class InfoScreenActivity : AppCompatActivity() {

    lateinit var binding: ActivityInfoScreenBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_info_screen)
    }
}