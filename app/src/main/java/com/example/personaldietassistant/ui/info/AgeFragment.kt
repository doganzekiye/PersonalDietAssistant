package com.example.personaldietassistant.ui.info

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.example.personaldietassistant.R
import com.example.personaldietassistant.databinding.FragmentAgeBinding
import com.example.personaldietassistant.ui.base.BaseFragment

class AgeFragment : BaseFragment() {

    lateinit var binding: FragmentAgeBinding
    private val viewModel: InfoScreenViewModel by activityViewModels()
    override fun onCreateView(
        inflater: LayoutInflater,

        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_age, container, false)
        //binding.viewModel = viewModel
        binding.lifecycleOwner = this
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        onClickNumberPicker()
        binding.buttonNext.setOnClickListener {
            findNavController().navigate(R.id.action_ageFragment_to_heightFragment)
        }

        setToolbar(binding.toolbar.root, title = "Select Your Age", onClick = {
            findNavController().navigateUp()
        })
    }

    private fun onClickNumberPicker() {
        binding.npAge.apply {
            maxValue = 70
            minValue = 18
            value = 18
            wrapSelectorWheel = false
        }
        binding.tvPickedAge.text = (String.format(
            "My age is %s",
            binding.npAge.value
        ))

        binding.npAge.setOnValueChangedListener { picker, oldVal, newVal ->
            viewModel.user.age = newVal
            binding.tvPickedAge.text =
                (String.format("My age is %s", newVal))
        }
    }
}

