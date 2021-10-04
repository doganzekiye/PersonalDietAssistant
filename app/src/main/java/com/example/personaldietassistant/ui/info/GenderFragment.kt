package com.example.personaldietassistant.ui.info

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.example.personaldietassistant.R
import com.example.personaldietassistant.databinding.FragmentGenderBinding
import com.example.personaldietassistant.ui.base.BaseFragment

class GenderFragment : BaseFragment() {

    lateinit var binding: FragmentGenderBinding
    private val viewModel: InfoScreenViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_gender, container, false)
        binding.viewModel = viewModel
        binding.lifecycleOwner = this
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        setClickListeners()
        observeViewModel()
        setStepToolbar(binding.toolbar.root, stepSelectedCount = 3, stepTotalCount = 8, onClick = {
            findNavController().navigateUp()
        })
    }

    private fun observeViewModel() {
        viewModel.selectedGender.observe(viewLifecycleOwner, Observer {
            if (it.first == getString(R.string.female) && it.second) {
                setGenderLayout(getString(R.string.female))
            } else if (it.first == getString(R.string.male) && it.second) {
                setGenderLayout(getString(R.string.male))
            }
        })
    }

    private fun setClickListeners() {
        binding.clFemale.setOnClickListener {
            viewModel.selectedGender.postValue(Pair(getString(R.string.female), true))
        }

        binding.clMale.setOnClickListener {
            viewModel.selectedGender.postValue(Pair(getString(R.string.male), true))
        }

        binding.btnGenderAccept.setOnClickListener {
            findNavController().navigate(R.id.action_genderFragment_to_ageFragment)
        }
    }

    private fun setGenderLayout(gender: String) {
        if (gender == getString(R.string.female)) {
            binding.clFemale.setBackgroundResource(R.drawable.custom_green_rounded_corners)
            binding.clMale.setBackgroundResource(R.drawable.custom_gray_rounded_corners)
            binding.tvGenderFemale.setTextColor(
                ContextCompat.getColor(
                    binding.root.context, R.color.page_title
                )
            )
            binding.tvGenderMale.setTextColor(
                ContextCompat.getColor(
                    binding.root.context, R.color.gray_dark
                )
            )
            viewModel.user.gender = getString(R.string.female)
        } else if (gender == getString(R.string.male)) {
            binding.clMale.setBackgroundResource(R.drawable.custom_green_rounded_corners)
            binding.clFemale.setBackgroundResource(R.drawable.custom_gray_rounded_corners)
            binding.tvGenderMale.setTextColor(
                ContextCompat.getColor(
                    binding.root.context, R.color.page_title
                )
            )
            binding.tvGenderFemale.setTextColor(
                ContextCompat.getColor(
                    binding.root.context, R.color.gray_dark
                )
            )
            viewModel.user.gender = getString(R.string.male)
        }
    }
}