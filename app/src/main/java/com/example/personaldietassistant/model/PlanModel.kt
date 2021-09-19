package com.example.personaldietassistant.model

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import com.example.personaldietassistant.R

enum class PlanModel (
    @StringRes val title: Int,
    @StringRes val desc: Int,
    @DrawableRes val image: Int
) {
    PLAN_1(R.string.screen_intro1_title, R.string.empty, R.drawable.intro_1_image),
    PLAN_2(R.string.screen_intro2_title, R.string.empty, R.drawable.intro_1_image),
    PLAN_3(R.string.screen_intro3_title, R.string.empty, R.drawable.intro_1_image)
}