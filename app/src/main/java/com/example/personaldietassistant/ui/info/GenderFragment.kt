package com.example.personaldietassistant.ui.info

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
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
        binding.btnGenderAccept.setOnClickListener {
            findNavController().navigate(R.id.action_genderFragment_to_ageFragment)
        }
        setToolbar(binding.toolbar.root, title = "Cinsiyet Seç", onClick = {
            findNavController().navigateUp()
        })
    }
}