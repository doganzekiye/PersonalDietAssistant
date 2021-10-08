package com.example.personaldietassistant.ui.info

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.example.personaldietassistant.R
import com.example.personaldietassistant.databinding.FragmentHeightBinding
import com.example.personaldietassistant.ui.base.BaseFragment
import com.example.personaldietassistant.util.getDecimal
import com.example.personaldietassistant.util.getNumber

class HeightFragment : BaseFragment() {
    lateinit var binding: FragmentHeightBinding
    private val viewModel: InfoScreenViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_height, container, false)
        binding.viewModel = viewModel
        binding.lifecycleOwner = this
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        setTargetWeight(getString(R.string.female))
        setOnClick()
        setStepToolbar(binding.toolbar.root, stepSelectedCount = 5, stepTotalCount = 8, onClick = {
            findNavController().navigateUp()
        })
    }

    private fun setOnClick() {
        var mHeight = 0.0f
        var mHeightDecimal = 0.0f

        binding.npHeight.apply {
            maxValue = 220
            minValue = 120
            value = viewModel.user.height.getNumber()
            wrapSelectorWheel = false
        }

        binding.npHeight.setOnValueChangedListener { picker, oldVal, newVal ->
            viewModel.userHeightText.postValue("My height is " + newVal + "." + binding.npHeightDecimal.value + "cm")
            mHeight = newVal.toFloat()
            viewModel.user.height = mHeight + mHeightDecimal
        }

        binding.npHeightDecimal.apply {
            maxValue = 9
            minValue = 0
            value = viewModel.user.height.getDecimal()
            wrapSelectorWheel = false
        }

        binding.npHeightDecimal.setOnValueChangedListener { picker, oldVal, newVal ->
            viewModel.userHeightText.postValue("My height is " + binding.npHeight.value + "." + newVal + "cm")
            mHeightDecimal = (newVal.toFloat() / 10)
            viewModel.user.height = mHeight + mHeightDecimal
        }
        binding.btnHeightAccept.setOnClickListener {
            findNavController().navigate(R.id.action_heightFragment_to_weightFragment)
        }
    }
    private fun setTargetWeight(gender: String) {
        if (gender == getString(R.string.female)) {
            viewModel.setTargetFemale()
        } else if (gender == getString(R.string.male)) {
            viewModel.setTargetMale()
        }
    }
}