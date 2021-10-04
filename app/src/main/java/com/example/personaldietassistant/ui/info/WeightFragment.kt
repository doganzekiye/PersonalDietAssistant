package com.example.personaldietassistant.ui.info

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.example.personaldietassistant.R
import com.example.personaldietassistant.databinding.FragmentWeightBinding
import com.example.personaldietassistant.ui.base.BaseFragment
import com.example.personaldietassistant.util.getDecimal
import com.example.personaldietassistant.util.getNumber
import kotlin.math.min

class WeightFragment : BaseFragment() {
    lateinit var binding: FragmentWeightBinding
    private val viewModel: InfoScreenViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_weight, container, false)
        binding.viewModel = viewModel
        binding.lifecycleOwner = this
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        setOnClick()
        setStepToolbar(binding.toolbar.root, stepSelectedCount = 6, stepTotalCount = 8, onClick = {
            findNavController().navigateUp()
        })
    }

    private fun setOnClick() {
        var mWeight = 0.0f
        var mWeightDecimal = 0.0f

        binding.npWeight.apply {
            maxValue = 220
            minValue = 30
            value = viewModel.user.weight.getNumber()
            wrapSelectorWheel = false
        }

        binding.npWeight.setOnValueChangedListener { picker, oldVal, newVal ->
            viewModel.userWeightText.postValue("My weight is " + newVal + "." + binding.npWeightDecimal.value + "kg")
            mWeight = newVal.toFloat()
            viewModel.user.weight = mWeight + mWeightDecimal
        }

        binding.npWeightDecimal.apply {
            maxValue = 9
            minValue = 0
            value = viewModel.user.weight.getDecimal()
            wrapSelectorWheel = false
        }

        binding.npWeightDecimal.setOnValueChangedListener { picker, oldVal, newVal ->
            viewModel.userWeightText.postValue("My weight is " + binding.npWeight.value + "." + newVal + "kg")
            mWeightDecimal = (newVal.toFloat() / 10)
            viewModel.user.weight = mWeight + mWeightDecimal
        }
        binding.btnWeightAccept.setOnClickListener {
            findNavController().navigate(R.id.action_weightFragment_to_targetFragment)
        }
    }
}