package com.example.personaldietassistant.ui.info

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.fragment.findNavController
import com.example.personaldietassistant.R
import com.example.personaldietassistant.databinding.FragmentHeightBinding
import com.example.personaldietassistant.ui.base.BaseFragment

class HeightFragment : BaseFragment() {
    lateinit var binding: FragmentHeightBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_height, container, false)
        return binding.root
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding.btnHeightAccept.setOnClickListener {
            //findNavController().navigate(R.id.he)
        }
        setToolbar(binding.toolbar.root, title = "Boyunu Gir", onClick = {
            findNavController().navigateUp()
        })
    }
}