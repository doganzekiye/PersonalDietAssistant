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
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_age, container, false)
        binding.viewModel = viewModel
        binding.lifecycleOwner = this
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        setOnClick()
        setStepToolbar(
            toolbar = binding.toolbar.root,
            stepSelectedCount = 4,
            stepTotalCount = 8,
            onClick = {
                findNavController().navigateUp()
            })
    }

    private fun setOnClick() {
        binding.npAge.apply {
            maxValue = 70
            minValue = 18
            value = viewModel.user.age
            wrapSelectorWheel = false
        }
        binding.npAge.setOnValueChangedListener { picker, oldVal, newVal ->
            viewModel.user.age = newVal
            viewModel.userAgeText.postValue("My age is " + viewModel.user.age.toString())
        }

        binding.buttonNext.setOnClickListener {
            findNavController().navigate(R.id.action_ageFragment_to_heightFragment)
        }
    }
}

