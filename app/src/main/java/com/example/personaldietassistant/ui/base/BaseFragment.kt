package com.example.personaldietassistant.ui.base

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ImageView
import androidx.fragment.app.Fragment
import com.example.personaldietassistant.R
import com.example.personaldietassistant.ui.customview.PdaStepView

open class BaseFragment : Fragment() {

    protected fun setStepToolbar(
        showBackIcon: Boolean = true,
        toolbar: View,
        stepSelectedCount: Int,
        stepTotalCount: Int,
        onClick: () -> Unit
    ) {

        val backButton = toolbar.findViewById<ImageView>(R.id.backButton)
        backButton.setOnClickListener {
            onClick.invoke()
        }
        if (showBackIcon.not()) {
            backButton.visibility = View.GONE
        }
        val stepView = toolbar.findViewById<PdaStepView>(R.id.pdaStepView)
        stepView.setStepCount(selected = stepSelectedCount, total = stepTotalCount)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        logLifecycleEvents("onCreate")
    }

    override fun onResume() {
        super.onResume()
        logLifecycleEvents("onResume")
    }

    override fun onDestroy() {
        super.onDestroy()
        logLifecycleEvents("onDestroy")
    }

    private fun logLifecycleEvents(lifeCycleName: String) {
        Log.d("Lifecycle", this::class.simpleName + " " + lifeCycleName)
    }
}