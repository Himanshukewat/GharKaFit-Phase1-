package com.example.gharkafit.core

fun bmiMessage(bmi: Double): String {

    return when {
        bmi < 18.5 -> "You are underweight"
        bmi < 25 -> "You are in a healthy range"
        bmi < 30 -> "You are slightly overweight"
        else -> "You are in the obese range"
    }
}