package com.example.personaldietassistant.ui.info

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.personaldietassistant.R
import com.example.personaldietassistant.databinding.FragmentHeightBinding
import com.example.personaldietassistant.ui.base.BaseFragment

class HeightFragment : BaseFragment() {
    lateinit var binding: FragmentHeightBinding
    var height: Float = 0.0f
    var heightDecimal: Float = 0.0f

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_height, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        onClickNumberPicker()
        val viewModel =
            ViewModelProvider(requireActivity()).get(InfoScreenViewModel::class.java)

        binding.btnHeightAccept.setOnClickListener {
            viewModel.user.height = height + heightDecimal
            findNavController().navigate(R.id.action_heightFragment_to_weightFragment)
        }
        setToolbar(binding.toolbar.root, title = "Boyunu Gir", onClick = {
            findNavController().navigateUp()
        })
    }

    fun onClickNumberPicker() {
        binding.npHeight.apply {
            maxValue = 220
            minValue = 120
            value = 150
            wrapSelectorWheel = false
        }

        binding.npHeight.setOnValueChangedListener { picker, oldVal, newVal ->
            binding.tvPickedHeight.text =
                (String.format("My height is %s.%s cm", newVal, binding.npHeightDecimal.value))
            height = newVal.toFloat()
        }

        binding.npHeightDecimal.apply {
            maxValue = 9
            minValue = 0
            value = 0
            wrapSelectorWheel = false
        }

        binding.tvPickedHeight.text = (String.format(
            "My height is %s.%s cm",
            binding.npHeight.value,
            binding.npHeightDecimal.value
        ))

        binding.npHeightDecimal.setOnValueChangedListener { picker, oldVal, newVal ->
            binding.tvPickedHeight.text =
                (String.format("My height is %s.%s cm", binding.npHeight.value, newVal))

            heightDecimal = (newVal.toFloat() / 10)
        }
    }
}