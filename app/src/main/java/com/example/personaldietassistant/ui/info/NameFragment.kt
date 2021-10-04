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
        setOnClick()
        setStepToolbar(binding.toolbar.root, stepSelectedCount = 2, stepTotalCount = 8, onClick = {
            findNavController().navigateUp()
        })
    }

    private fun setOnClick() {
        binding.etEnterName.doAfterTextChanged { s ->
            s?.let {
                viewModel.isNameValid.postValue((s.length >= 2))

                if (s.toString().length >= 2) {
                    viewModel.userWelcomeText.postValue(getString(R.string.hello) + " " + s.toString() + ",")
                    viewModel.user.name = s.toString()
                } else {
                    viewModel.userWelcomeText.postValue(getString(R.string.info_name_title))
                    viewModel.user.name = getString(R.string.empty)
                }
            }
        }
        binding.btnNameAccept.setOnClickListener {
            findNavController().navigate(R.id.action_nameFragment_to_genderFragment)
        }
    }
}