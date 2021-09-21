package com.example.personaldietassistant.ui.info

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.fragment.findNavController
import com.example.personaldietassistant.R
import com.example.personaldietassistant.databinding.FragmentPlanBinding
import com.example.personaldietassistant.ui.base.BaseFragment

class PlanFragment : BaseFragment() {

    lateinit var binding: FragmentPlanBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_plan, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding.rvPlan.adapter = PlanAdapter(canNavigateToNextScreen = { canNavigate ->
            if (canNavigate) {
                binding.buttonNext.visibility = View.VISIBLE
            } else {
                binding.buttonNext.visibility = View.GONE
            }
        })

        binding.buttonNext.setOnClickListener {
            findNavController().navigate(R.id.action_planFragment_to_nameFragment)
        }

        setToolbar(binding.toolbar.root,title = "Plan Seç", onClick = {
            findNavController().navigateUp()
        })
    }
}