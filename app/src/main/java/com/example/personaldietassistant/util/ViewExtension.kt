package com.example.personaldietassistant.util

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.SnapHelper

fun View.show(shouldShow: Boolean) {
    if (shouldShow) {
        this.visibility = View.VISIBLE
    } else {
        this.visibility = View.GONE
    }
}

fun SnapHelper.getSnapPosition(recyclerView: RecyclerView): Int {
    val layoutManager = recyclerView.layoutManager ?: return RecyclerView.NO_POSITION
    val snapView = findSnapView(layoutManager) ?: return RecyclerView.NO_POSITION
    return layoutManager.getPosition(snapView)
}

fun RecyclerView.attachSnapHelperWithPositionListener(
    snapHelper: SnapHelper,
    onSnapPositionChangeListener: OnSnapPositionChangeListener) {
    snapHelper.attachToRecyclerView(this)
    val snapOnScrollListener = SnapScrollListener(snapHelper, onSnapPositionChangeListener)
    addOnScrollListener(snapOnScrollListener)
}