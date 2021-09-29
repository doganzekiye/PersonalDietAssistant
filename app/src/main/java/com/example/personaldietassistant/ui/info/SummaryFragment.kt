package com.example.personaldietassistant.ui.info

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.activityViewModels
import com.example.personaldietassistant.R
import com.example.personaldietassistant.databinding.FragmentNameBinding
import com.example.personaldietassistant.databinding.FragmentSummaryBinding
import com.example.personaldietassistant.ui.base.BaseFragment

class SummaryFragment : BaseFragment() {
    lateinit var binding: FragmentSummaryBinding
    private val viewModel: InfoScreenViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_summary, container, false)
        binding.viewModel = viewModel
        binding.lifecycleOwner = this
        return binding.root
    }

    /*override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        with(binding){
            userAge.text = viewModel!!.user.age.toString()
            userDaily.text = viewModel!!.user.dailyCal.toString()
            userGender.text = viewModel?.user?.gender
            userHeight.text = viewModel?.user?.height.toString()
            userName.text = viewModel?.user?.name
            userWeight.text = viewModel?.user?.weight.toString()
            userTarget.text = viewModel?.user?.targetWeight.toString()
        }
    }*/
}