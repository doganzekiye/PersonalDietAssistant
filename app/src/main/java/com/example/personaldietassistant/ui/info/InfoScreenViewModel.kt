package com.example.personaldietassistant.ui.info

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.personaldietassistant.data.User
import com.example.personaldietassistant.util.AppConstants.BMI_FEMALE_FACTOR_CONSTANT
import com.example.personaldietassistant.util.AppConstants.BMI_FEMALE_HEIGHT_CONSTANT
import com.example.personaldietassistant.util.AppConstants.BMI_HEIGHT_CONSTANT
import com.example.personaldietassistant.util.AppConstants.BMI_MALE_FACTOR_CONSTANT
import com.example.personaldietassistant.util.AppConstants.BMI_MALE_HEIGHT_CONSTANT
import com.example.personaldietassistant.util.AppConstants.BMI_RANGE_CONSTANT
import com.example.personaldietassistant.util.AppConstants.BMI_TARGET_MAX_CONSTANT
import com.example.personaldietassistant.util.AppConstants.BMI_TARGET_MIN_CONSTANT
import kotlin.math.pow

class InfoScreenViewModel : ViewModel() {
    var user: User = User(
        name = "",
        height = 150.0f,
        weight = 70.0f,
        gender = "",
        age = 18,
        dailyCal = 0,
        targetWeight = 0.0f
    )

    var isNameValid: MutableLiveData<Boolean> = MutableLiveData()
    var isPlanValid: MutableLiveData<Boolean> = MutableLiveData(false)
    var validPlanRowId: MutableLiveData<Int> = MutableLiveData(0)
    var selectedGender: MutableLiveData<Pair<String, Boolean>> = MutableLiveData()

    var userWelcomeText: MutableLiveData<String> = MutableLiveData()
    var userAgeText: MutableLiveData<String> = MutableLiveData()
    var userHeightText: MutableLiveData<String> = MutableLiveData()
    var userWeightText: MutableLiveData<String> = MutableLiveData()
    var userTargetText: MutableLiveData<String> = MutableLiveData()
    var targetMaxValue: MutableLiveData<Int> = MutableLiveData()
    var targetMinValue: MutableLiveData<Int> = MutableLiveData()

    var targetFemale: MutableLiveData<Float> = MutableLiveData()
    var targetMale: MutableLiveData<Float> = MutableLiveData()

    var recommendedCal : MutableLiveData<Int> = MutableLiveData()


    /*For men:
    BMR = 10W + 6.25H - 5A + 5
    For women:
    BMR = 10W + 6.25H - 5A - 161*/

    init {
        userWelcomeText.value = "Hadi seni tanıyalım.."
        userAgeText.value = "My age is " + user.age
        userHeightText.value = "My height is " + user.height + " cm"
        userWeightText.value = "My weight is " + user.weight + " kg"
    }

    fun setRecommendedCal(isFemale : Boolean) {
        val genderMultiplier = if(isFemale) -161 else 5
        recommendedCal.value = ((10*user.weight) + (6.25 * user.height) - (5 * user.age) + genderMultiplier).toInt()
    }

    fun getTargetMaxValue(): Int {
        targetMaxValue.value = (((this.user.height / 100).pow(2)) * BMI_TARGET_MAX_CONSTANT).toInt()
        return targetMaxValue.value!!
    }

    fun getTargetMinValue(): Int {
        targetMinValue.value = (((this.user.height / 100).pow(2)) * BMI_TARGET_MIN_CONSTANT).toInt()
        return targetMinValue.value!!
    }

    fun getTargetFemale(): Float {
        targetFemale.value =
            BMI_FEMALE_HEIGHT_CONSTANT + (BMI_FEMALE_FACTOR_CONSTANT * (this.user.height - BMI_HEIGHT_CONSTANT) / BMI_RANGE_CONSTANT).toFloat()
        return targetFemale.value!!
    }

    fun getTargetMale(): Float {
        targetMale.value =
            BMI_MALE_HEIGHT_CONSTANT + (BMI_MALE_FACTOR_CONSTANT * (this.user.height - BMI_HEIGHT_CONSTANT) / BMI_RANGE_CONSTANT).toFloat()
        return targetMale.value!!
    }
}