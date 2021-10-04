package com.example.personaldietassistant.ui.base

import android.os.Bundle
import android.util.Log
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
        stepView.setStepCount(selected = stepSelectedCount, total = stepTotalCount)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        logLifecycleEvents("onCreate")
    }

    override fun onDestroy() {
        super.onDestroy()
        logLifecycleEvents("onDestroy")
    }

    private fun logLifecycleEvents(lifeCycleName: String) {
        Log.d("Lifecycle", this::class.simpleName + " " + lifeCycleName)
    }
}