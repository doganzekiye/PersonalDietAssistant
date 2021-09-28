package com.example.personaldietassistant.model

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import com.example.personaldietassistant.R

enum class PlanModel (
    @StringRes val title: Int,
    @StringRes val desc: Int,
    @DrawableRes val image: Int,
    val isEnabled: Boolean,
    var isSelected: Boolean = false
) {
    PLAN_1(R.string.screen_info_plan_1_title, R.string.screen_info_plan_1_desc, R.drawable.ic_plan_item_body, true, false),
    PLAN_2(R.string.screen_info_plan_2_title, R.string.screen_info_plan_2_desc, R.drawable.ic_plan_item_enabled_body, false, false),
    PLAN_3(R.string.screen_info_plan_3_title, R.string.screen_info_plan_3_desc, R.drawable.ic_plan_item_enabled_body, false, false),
    PLAN_4(R.string.screen_info_plan_4_title, R.string.screen_info_plan_4_desc, R.drawable.ic_plan_item_enabled_body, false, false),
    PLAN_5(R.string.screen_info_plan_5_title, R.string.screen_info_plan_5_desc, R.drawable.ic_plan_item_enabled_body, false, false)
}