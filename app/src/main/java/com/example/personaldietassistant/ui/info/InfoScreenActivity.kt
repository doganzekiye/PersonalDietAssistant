package com.example.personaldietassistant.ui.info

import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import com.example.personaldietassistant.R
import com.example.personaldietassistant.databinding.ActivityInfoScreenBinding
import com.example.personaldietassistant.ui.base.BaseActivity

class InfoScreenActivity : BaseActivity() {

    private lateinit var binding: ActivityInfoScreenBinding
    private lateinit var viewModel: InfoScreenViewModel
    private lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(
            this, R.layout.activity_info_screen)

        viewModel = ViewModelProvider(this).get(InfoScreenViewModel::class.java)
        val navHost = supportFragmentManager.findFragmentById(R.id.info_fragment_navhost) as NavHostFragment
        navController = navHost.findNavController()

    }


    private fun observeLiveData() {

    }
}