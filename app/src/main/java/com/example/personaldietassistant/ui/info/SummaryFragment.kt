package com.example.personaldietassistant.ui.info

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.example.personaldietassistant.R
import com.example.personaldietassistant.databinding.FragmentSummaryBinding
import com.example.personaldietassistant.ui.MainActivity
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

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        setStepToolbar(binding.toolbar.root, stepSelectedCount = 8, stepTotalCount = 8, onClick = {
            findNavController().navigateUp()
        })
        binding.btnSummaryAccept.setOnClickListener {
            val intent = Intent(activity, MainActivity::class.java)
            startActivity(intent)
        }
    }
}