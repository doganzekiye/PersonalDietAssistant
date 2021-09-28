package com.example.personaldietassistant.ui.info

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.doAfterTextChanged
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.example.personaldietassistant.R
import com.example.personaldietassistant.databinding.FragmentNameBinding
import com.example.personaldietassistant.ui.base.BaseFragment
import com.example.personaldietassistant.util.showMessage

class NameFragment : BaseFragment() {

    lateinit var binding: FragmentNameBinding
    private val viewModel: InfoScreenViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_name, container, false)
        binding.viewModel = viewModel
        binding.lifecycleOwner = this
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        setToolbar(binding.toolbar.root, title = "Adını Gir", onClick = {
            findNavController().navigateUp()
        })

        binding.etEnterName.doAfterTextChanged { s ->
            s?.let {
                viewModel.isNameValid.postValue((s.length >= 2))

                if (s.toString().length >= 2) {
                    viewModel.userName.postValue("Merhaba $s,")
                } else {
                    viewModel.userName.postValue("Hadi seni tanıyalım..")
                }
            }
        }

        binding.btnNameAccept.setOnClickListener {
            findNavController().navigate(R.id.action_nameFragment_to_genderFragment)
        }
    }
}