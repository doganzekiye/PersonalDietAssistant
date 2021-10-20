package com.example.personaldietassistant.util

import android.content.Context
import android.content.SharedPreferences
import com.example.personaldietassistant.R

object PrefUtil {

    fun getPref(context: Context): SharedPreferences {
        val sharedPref = context.getSharedPreferences(
            context.getString(R.string.preference_file_key), Context.MODE_PRIVATE
        )
        return sharedPref
    }

    fun getIntroCompleted(context: Context): Boolean {
        return getPref(context).getBoolean(AppConstants.PREF_INTRO_COMPLETED, false)
    }

    fun setIntroCompleted(context: Context) {
        getPref(context).edit().apply {
            putBoolean(AppConstants.PREF_INTRO_COMPLETED, true)
            apply()
        }
    }

    fun getInfoCompleted(context: Context): Boolean {
        return getPref(context).getBoolean(AppConstants.PREF_INFO_COMPLETED, false)
    }

    fun setInfoCompleted(context: Context) {
        getPref(context).edit().apply {
            putBoolean(AppConstants.PREF_INFO_COMPLETED, true)
            apply()
        }
    }
}