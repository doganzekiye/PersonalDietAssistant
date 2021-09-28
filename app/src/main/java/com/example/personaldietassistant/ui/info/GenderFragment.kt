package com.example.personaldietassistant.ui.info

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.personaldietassistant.R
import com.example.personaldietassistant.databinding.FragmentGenderBinding
import com.example.personaldietassistant.ui.base.BaseFragment

class GenderFragment : BaseFragment() {

    lateinit var binding: FragmentGenderBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_gender, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        clickOnImage()
        binding.btnGenderAccept.setOnClickListener {
            findNavController().navigate(R.id.action_genderFragment_to_ageFragment)
        }
        setToolbar(binding.toolbar.root, title = "Cinsiyet Seç", onClick = {
            findNavController().navigateUp()
        })
    }

    fun clickOnImage() {
        val viewModel =
            ViewModelProvider(requireActivity()).get(InfoScreenViewModel::class.java)
        binding.clFemale.setOnClickListener {
            binding.clFemale.setBackgroundResource(R.drawable.custom_green_rounded_corners)
            binding.clMale.setBackgroundResource(R.drawable.custom_gray_rounded_corners)
            binding.tvGenderFemale.setTextColor(
                ContextCompat.getColor(
                    binding.root.context, R.color.info_title
                )
            )
            binding.tvGenderMale.setTextColor(
                ContextCompat.getColor(
                    binding.root.context, R.color.gray_dark
                )
            )
            viewModel.user.gender = "female"
        }
        binding.clMale.setOnClickListener {
            binding.clMale.setBackgroundResource(R.drawable.custom_green_rounded_corners)
            binding.clFemale.setBackgroundResource(R.drawable.custom_gray_rounded_corners)
            binding.tvGenderMale.setTextColor(
                ContextCompat.getColor(
                    binding.root.context, R.color.info_title
                )
            )
            binding.tvGenderFemale.setTextColor(
                ContextCompat.getColor(
                    binding.root.context, R.color.gray_dark
                )
            )
            viewModel.user.gender = "male"
        }

    }

}