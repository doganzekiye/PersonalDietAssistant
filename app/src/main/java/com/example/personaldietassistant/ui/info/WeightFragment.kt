package com.example.personaldietassistant.ui.info

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.fragment.findNavController
import com.example.personaldietassistant.R
import com.example.personaldietassistant.databinding.FragmentWeightBinding
import com.example.personaldietassistant.ui.base.BaseFragment

class WeightFragment : BaseFragment() {
    lateinit var binding: FragmentWeightBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_weight, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding.btnWeightAccept.setOnClickListener {
            //findNavController().navigate(R.id.he)
        }
        setToolbar(binding.toolbar.root, title = "Kilonu Gir", onClick = {
            findNavController().navigateUp()
        })
    }
}