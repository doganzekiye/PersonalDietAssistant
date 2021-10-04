package com.example.personaldietassistant.ui.base

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.example.personaldietassistant.R
import com.example.personaldietassistant.ui.customview.PdaStepView

open class BaseFragment : Fragment() {

    protected fun setToolbar(toolbar: View, title: String = "", onClick: () -> Unit) {
        val backButton = toolbar.findViewById<ImageView>(R.id.backButton)
        backButton.setOnClickListener {
            onClick.invoke()
        }
        val titleView = toolbar.findViewById<TextView>(R.id.toolbarTitle)
        titleView.text = title
    }

    protected fun setStepToolbar(
        toolbar: View,

        stepSelectedCount: Int,
        stepTotalCount: Int,
        onClick: () -> Unit
    ) {
        val backButton = toolbar.findViewById<ImageView>(R.id.backButton)
        backButton.setOnClickListener {
            onClick.invoke()
        }
        val stepView = toolbar.findViewById<PdaStepView>(R.id.pdaStepView)
        //stepView.setStepSelectedCount(stepSelectedCount)
        //stepView.setStepTotalCount(stepTotalCount)
        stepView.setStepCount(selected = stepSelectedCount, total = stepTotalCount)
    }
}