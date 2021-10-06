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
    var target = 0.0f

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
        setTarget(viewModel.user.gender)
        setOnClick()
        setStepToolbar(binding.toolbar.root, stepSelectedCount = 7, stepTotalCount = 8, onClick = {
            findNavController().navigateUp()
        })
    }

    private fun setOnClick() {
        var mTarget = 0.0f
        var mTargetDecimal = 0.0f

        binding.npTarget.apply {
            maxValue = viewModel.getTargetMaxValue()
            minValue = viewModel.getTargetMinValue()
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

        //TODO oldVal if kosulu
        binding.npTargetDecimal.setOnValueChangedListener { picker, oldVal, newVal ->
            viewModel.userTargetText.postValue("My target is " + binding.npTarget.value + "." + newVal + "kg")
            mTargetDecimal = (newVal.toFloat() / 10)
            viewModel.user.targetWeight = mTarget + mTargetDecimal
        }

        binding.btnTargetAccept.setOnClickListener {
            findNavController().navigate(R.id.action_targetFragment_to_summaryFragment)
        }
    }

    private fun setTarget(gender: String) {
        if (gender == getString(R.string.female)) {
            target = viewModel.getTargetFemale()
            viewModel.setRecommendedCal(true)
        } else if (gender == getString(R.string.male)) {
            target = viewModel.getTargetMale()
            viewModel.setRecommendedCal(false)
        }
    }
}