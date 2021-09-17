package com.example.personaldietassistant

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.viewpager2.widget.ViewPager2
import com.example.personaldietassistant.databinding.ActivityIntroPagerBinding
import com.example.personaldietassistant.ui.adapter.IntroPagerAdapter

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

        binding.introViewPager.registerOnPageChangeCallback(object :
            ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                if (position == 2) {
                    binding.button.text = "Başla"
                    binding.button.icon = null

                } else {
                    binding.button.icon = getDrawable(R.drawable.ic_white_arrow)
                    binding.button.text = ""
                }
            }
        })
    }

}