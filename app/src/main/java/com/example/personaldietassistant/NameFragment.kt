package com.example.personaldietassistant

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.personaldietassistant.databinding.FragmentNameBinding

class NameFragment : Fragment() {
    var binding: FragmentNameBinding? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_name, container, false)
        return binding!!.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding!!.etEnterName.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {}
            override fun afterTextChanged(s: Editable) {
                binding!!.tvNameHelloMessage.text = "Hello, "+ s.toString()
            }
        })

        setOnClick()
    }
    fun setOnClick(){
        binding!!.btnNameAccept.setOnClickListener {
            findNavController().navigate(R.id.action_nameFragment_to_genderFragment)
        }
    }
}