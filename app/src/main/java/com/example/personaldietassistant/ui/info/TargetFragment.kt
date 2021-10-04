package com.example.personaldietassistant.ui.info

import android.annotation.SuppressLint
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
import com.example.personaldietassistant.util.getDecimal
import com.example.personaldietassistant.util.getNumber
import kotlin.math.pow

class TargetFragment : BaseFragment() {
    lateinit var binding: FragmentTargetBinding
    private val viewModel: InfoScreenViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_target, container, false)
        binding.viewModel = viewModel
        binding.lifecycleOwner = this
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        setOnClick()
        setStepToolbar(binding.toolbar.root, stepSelectedCount = 7, stepTotalCount = 8, onClick = {
            findNavController().navigateUp()
        })
    }

    @SuppressLint("SetTextI18n")
    private fun setOnClick() {
        var mTarget = 0.0f
        var mTargetDecimal = 0.0f
        var target = 0.0f
        if (viewModel.user.gender == getString(R.string.female)) {
            target = 49 + ((1.7) * (viewModel.user.height - 152.4) / (2.54)).toFloat()
            binding.tvTargetTitle.text = target.toString() + "female"
        } else if (viewModel.user.gender == getString(R.string.male)) {
            target = 52 + ((1.9) * (viewModel.user.height - 152.4) / (2.54)).toFloat()
            binding.tvTargetTitle.text = target.toString() + "male"
        }

        binding.npTarget.apply {
            maxValue = (((viewModel.user.height / 100).pow(2)) * (25)).toInt()
            minValue = (((viewModel.user.height / 100).pow(2)) * (18.5)).toInt()
            value = target.getNumber()
            wrapSelectorWheel = false
        }

        binding.npTarget.setOnValueChangedListener { picker, oldVal, newVal ->
            viewModel.userTargetText.postValue("My target is " + newVal + "." + binding.npTargetDecimal.value + "kg")
            mTarget = newVal.toFloat()
            viewModel.user.targetWeight = mTarget + mTargetDecimal
        }

        binding.npTargetDecimal.apply {
            maxValue = 9
            minValue = 0
            value = target.getDecimal()
            wrapSelectorWheel = false
        }

        /*binding.tvPickedTarget.text = (String.format(
            "My target is %s.%s kg",
            binding.npTarget.value,
            binding.npTargetDecimal.value
        ))*/

        binding.npTargetDecimal.setOnValueChangedListener { picker, oldVal, newVal ->
            viewModel.userTargetText.postValue("My target is " + binding.npTarget.value + "." + newVal + "kg")
            mTargetDecimal = (newVal.toFloat() / 10)
            viewModel.user.targetWeight = mTarget + mTargetDecimal
        }

        binding.btnTargetAccept.setOnClickListener {
            findNavController().navigate(R.id.action_targetFragment_to_summaryFragment)
        }
    }
}