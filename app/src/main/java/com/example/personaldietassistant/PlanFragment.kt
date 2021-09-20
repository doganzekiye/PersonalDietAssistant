package com.example.personaldietassistant

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import com.example.personaldietassistant.databinding.FragmentPlanBinding
import com.example.personaldietassistant.ui.adapter.PlanAdapter

class PlanFragment : Fragment() {

    var binding: FragmentPlanBinding? = null
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_plan, container, false)
        return binding!!.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding!!.rvPlan.adapter = PlanAdapter()
        setOnClick()
    }
    fun setOnClick(){
        binding!!.btnPlanAccept.setOnClickListener {
            findNavController().navigate(R.id.action_planFragment_to_nameFragment)
        }
    }
}