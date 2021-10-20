package com.example.personaldietassistant.ui.splash

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import androidx.appcompat.app.AppCompatActivity
import com.example.personaldietassistant.R
import com.example.personaldietassistant.ui.DailyActivity
import com.example.personaldietassistant.ui.MainActivity
import com.example.personaldietassistant.ui.info.InfoScreenActivity
import com.example.personaldietassistant.ui.intro.IntroPagerActivity
import com.example.personaldietassistant.util.PrefUtil
import com.example.personaldietassistant.util.showMessage

class SplashActivity : AppCompatActivity() {
    private val splashScreenLoadingDuration: Long = 6000
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        loadSplashScreen()
    }

    private fun loadSplashScreen() {
        val introCompleted = PrefUtil.getIntroCompleted(this)
        val infoCompleted = PrefUtil.getInfoCompleted(this)

        Handler().postDelayed({
            lateinit var intent: Intent
            if (introCompleted && infoCompleted.not()) {
                intent = Intent(this, InfoScreenActivity::class.java)

            }else if (introCompleted && infoCompleted) {
                intent = Intent(this, DailyActivity::class.java)

            } else if (introCompleted.not()) {
                intent = Intent(this, IntroPagerActivity::class.java)
            }
            startActivity(intent)
            finish()
        }, splashScreenLoadingDuration)
    }
}