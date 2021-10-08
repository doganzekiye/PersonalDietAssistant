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
import com.example.personaldietassistant.util.getDecimal
import com.example.personaldietassistant.util.getNumber

class TargetFragment : BaseFragment() {
    lateinit var binding: FragmentTargetBinding
    private val viewModel: InfoScreenViewModel by activityViewModels()
    var mTarget = 0.0f
    var mTargetDecimal = 0.0f


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
        setRecommendedCal(getString(R.string.female))
        setOnClick()
        setStepToolbar(binding.toolbar.root, stepSelectedCount = 7, stepTotalCount = 8, onClick = {
            findNavController().navigateUp()
        })
    }

    private fun setOnClick() {

        binding.npTarget.apply {
            maxValue = viewModel.getTargetMaxValue()
            minValue = viewModel.getTargetMinValue()
            value = viewModel.user.targetWeight.getNumber()
            wrapSelectorWheel = false
        }

        binding.npTargetDecimal.apply {
            maxValue = 9
            minValue = 0
            value = viewModel.user.targetWeight.getDecimal()
            wrapSelectorWheel = false
        }

        binding.npTarget.setOnValueChangedListener { picker, oldVal, newVal ->
            viewModel.userTargetText.postValue("My target is " + newVal + "." + binding.npTargetDecimal.value + " kg")
            mTarget = newVal.toFloat()
            viewModel.user.targetWeight = mTarget + mTargetDecimal
        }

        //TODO oldVal if kosulu
        binding.npTargetDecimal.setOnValueChangedListener { picker, oldVal, newVal ->
            viewModel.userTargetText.postValue("My target is " + binding.npTarget.value + "." + newVal + " kg")
            mTargetDecimal = (newVal.toFloat() / 10)
            viewModel.user.targetWeight = mTarget + mTargetDecimal
        }

        binding.btnTargetAccept.setOnClickListener {
            findNavController().navigate(R.id.action_targetFragment_to_summaryFragment)
        }
    }

    private fun setRecommendedCal(gender: String) {
        if (gender == getString(R.string.female)) {
            viewModel.setRecommendedCal(true)
        } else if (gender == getString(R.string.male)) {
            viewModel.setRecommendedCal(false)
        }
    }
}