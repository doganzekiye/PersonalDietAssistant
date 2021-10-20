package com.example.personaldietassistant.ui.intro

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.viewpager2.widget.ViewPager2
import com.example.personaldietassistant.R
import com.example.personaldietassistant.databinding.ActivityIntroPagerBinding
import com.example.personaldietassistant.ui.info.InfoScreenActivity
import com.example.personaldietassistant.util.AppConstants.PREF_INTRO_COMPLETED
import com.example.personaldietassistant.util.PrefUtil

class IntroPagerActivity : AppCompatActivity() {
    private lateinit var binding: ActivityIntroPagerBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_intro_pager)
        binding = DataBindingUtil.setContentView(
            this, R.layout.activity_intro_pager
        )
        binding.introViewPager.adapter = IntroPagerAdapter()
        binding.introViewPager.orientation = ViewPager2.ORIENTATION_HORIZONTAL
        binding.introIndicator.setViewPager(binding.introViewPager)

        pageOperations()

    }

    fun pageOperations() {
        binding.introViewPager.registerOnPageChangeCallback(object :
            ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                if (position == 2) {
                    lastPageProcess()
                } else {
                    interPageProcess(position)
                }
            }
        })
    }

    fun lastPageProcess() {
        binding.introPageButton.text = getString(R.string.intro_start)
        binding.introPageButton.icon = null
        binding.introSkip.visibility = View.GONE

        binding.introPageButton.setOnClickListener {
            val context = it.context
            val intent = Intent(context, InfoScreenActivity::class.java)
            context.startActivity(intent)
            PrefUtil.setIntroCompleted(context)
        }
    }

    fun interPageProcess(position: Int) {
        @SuppressLint("UseCompatLoadingForDrawables") // to use getDrawable function
        binding.introPageButton.icon = getDrawable(R.drawable.ic_white_arrow)
        binding.introPageButton.text = ""
        binding.introSkip.visibility = View.VISIBLE

        binding.introSkip.setOnClickListener {
            val context = it.context
            val intent = Intent(context, InfoScreenActivity::class.java)
            context.startActivity(intent)
            PrefUtil.setIntroCompleted(context)
        }
        binding.introPageButton.setOnClickListener {
            binding.introViewPager.setCurrentItem(position.inc(), true)
        }
    }
}