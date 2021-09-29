package com.example.personaldietassistant.ui.info

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.example.personaldietassistant.R
import com.example.personaldietassistant.databinding.FragmentTargetBinding
import com.example.personaldietassistant.ui.base.BaseFragment

class TargetFragment : BaseFragment() {
    lateinit var binding: FragmentTargetBinding
    private val viewModel: InfoScreenViewModel by activityViewModels()
    var mTarget: Float = 0.0f
    var mTargetDecimal: Float = 0.0f

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_target, container, false)
        //binding.viewModel = viewModel
        binding.lifecycleOwner = this
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        onClickNumberPicker()
        binding.btnTargetAccept.setOnClickListener {
            viewModel.user.targetWeight = mTarget + mTargetDecimal
            //findNavController().navigate(R.id)
        }
        setToolbar(binding.toolbar.root, title = "Hedef Kilonu Gir", onClick = {
            findNavController().navigateUp()
        })
    }

    private fun onClickNumberPicker() {
        binding.npTarget.apply {
            maxValue = 220
            minValue = 45
            value = 70
            wrapSelectorWheel = false
        }

        binding.npTarget.setOnValueChangedListener { picker, oldVal, newVal ->
            binding.tvPickedTarget.text =
                (String.format(
                    "My target weight is %s.%s kg",
                    newVal,
                    binding.npTargetDecimal.value
                ))
            mTarget = newVal.toFloat()
        }

        binding.npTargetDecimal.apply {
            maxValue = 9
            minValue = 0
            value = 0
            wrapSelectorWheel = false
        }

        binding.tvPickedTarget.text = (String.format(
            "My target weight is %s.%s kg",
            binding.npTarget.value,
            binding.npTargetDecimal.value
        ))

        binding.npTargetDecimal.setOnValueChangedListener { picker, oldVal, newVal ->
            binding.tvPickedTarget.text =
                (String.format("My target weight is %s.%s kg", binding.npTarget.value, newVal))
            mTargetDecimal = (newVal.toFloat() / 10)
        }
    }
}