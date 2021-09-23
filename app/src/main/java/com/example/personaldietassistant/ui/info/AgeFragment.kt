package com.example.personaldietassistant.ui.info

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearSnapHelper
import com.example.personaldietassistant.R
import com.example.personaldietassistant.databinding.FragmentAgeBinding
import com.example.personaldietassistant.ui.base.BaseFragment
import com.example.personaldietassistant.util.OnSnapPositionChangeListener
import com.example.personaldietassistant.util.attachSnapHelperWithPositionListener
import com.example.personaldietassistant.util.showMessage

class AgeFragment : BaseFragment(), OnSnapPositionChangeListener {

    private lateinit var binding: FragmentAgeBinding
    private var mCanNavigate: Boolean = false
    val viewModel : InfoScreenViewModel by activityViewModels()


    override fun onCreateView(
        inflater: LayoutInflater,

        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_age, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        binding.rvAge.attachSnapHelperWithPositionListener(LinearSnapHelper(), this)
        binding.rvAge.adapter = AgeAdapter(canNavigateToNextScreen = { canNavigate ->
            mCanNavigate = canNavigate
            if (canNavigate) {
                binding.buttonNext.visibility = View.VISIBLE
            } else {
                binding.buttonNext.visibility = View.GONE
            }
        }, onAgeSelected = {
            showMessage(it.toString())
        })

        setToolbar(binding.toolbar.root, title = "Select Your Age", onClick = {
            findNavController().navigateUp()
        })

        binding.buttonNext.setOnClickListener {
            findNavController().navigate(R.id.action_ageFragment_to_heightFragment)
        }
    }

    override fun onSnapPositionChange(position: Int) {
        //showMessage(position.toString())
    }
}

