package com.example.personaldietassistant.ui

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import androidx.appcompat.app.AppCompatActivity
import com.example.personaldietassistant.R

class SplashActivity : AppCompatActivity() {
    private val splashScreenLoadingDuration: Long = 6000
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        loadSplashScreen()
    }

    private fun loadSplashScreen() {
        Handler().postDelayed({
            val intent = Intent(this, IntroPagerActivity::class.java)
            startActivity(intent)
            finish()
        }, splashScreenLoadingDuration)
    }
}