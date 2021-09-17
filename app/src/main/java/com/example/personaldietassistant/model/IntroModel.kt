package com.example.personaldietassistant.model

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import com.example.personaldietassistant.R

enum class IntroModel(
    @StringRes val title: Int,
    @StringRes val desc: Int,
    @DrawableRes val image: Int
) {
    INTRO_1(R.string.screen_intro1_title, R.string.screen_intro1_desc, R.drawable.intro_1_image),
    INTRO_2(R.string.screen_intro2_title, R.string.screen_intro2_desc, R.drawable.intro_1_image),
    INTRO_3(R.string.screen_intro3_title, R.string.screen_intro3_desc, R.drawable.intro_1_image)
}