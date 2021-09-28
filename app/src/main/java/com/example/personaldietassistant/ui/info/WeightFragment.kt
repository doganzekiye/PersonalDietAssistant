package com.example.personaldietassistant.ui.info

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.personaldietassistant.R
import com.example.personaldietassistant.databinding.FragmentWeightBinding
import com.example.personaldietassistant.ui.base.BaseFragment

class WeightFragment : BaseFragment() {
    lateinit var binding: FragmentWeightBinding
    var mWeight : Float = 0.0f
    var mWeightDecimal : Float = 0.0f

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_weight, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        onClickNumberPicker()
        val viewModel =
            ViewModelProvider(requireActivity()).get(InfoScreenViewModel::class.java)

        binding.btnWeightAccept.setOnClickListener {
            viewModel.user.weight = mWeight+ mWeightDecimal
            //findNavController().navigate(R.id.)
        }
        setToolbar(binding.toolbar.root, title = "Kilonu Gir", onClick = {
            findNavController().navigateUp()
        })
    }

    fun onClickNumberPicker() {
        binding.npWeight.apply {
            maxValue = 220
            minValue = 30
            value = 70
            wrapSelectorWheel = false
        }

        binding.npWeight.setOnValueChangedListener { picker, oldVal, newVal ->
            binding.tvPickedWeight.text =
                (String.format("My weight is %s.%s kg", newVal, binding.npWeightDecimal.value))
            mWeight = newVal.toFloat()
        }

        binding.npWeightDecimal.apply {
            maxValue = 9
            minValue = 0
            value = 0
            wrapSelectorWheel = false
        }

        binding.tvPickedWeight.text = (String.format(
            "My weight is %s.%s kg",
            binding.npWeight.value,
            binding.npWeightDecimal.value
        ))

        binding.npWeightDecimal.setOnValueChangedListener { picker, oldVal, newVal ->
            binding.tvPickedWeight.text =
                (String.format("My weight is %s.%s kg", binding.npWeight.value, newVal))
            mWeightDecimal = (newVal.toFloat()/10)

        }

    }
}