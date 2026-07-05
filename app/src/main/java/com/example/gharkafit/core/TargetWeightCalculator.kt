package com.example.gharkafit.core

import android.util.Log

object TargetWeightCalculator {
    fun calculate(
        heightCm: Double,
        currentWeight: Double,
        goal: String
    ): Double {
        Log.d("HEIGHT_CM", heightCm.toString())
        val heightM = heightCm / 100

        Log.d("HEIGHT_M", heightM.toString())


        val minNormal = 18.5 * heightM * heightM
        val maxNormal = 24.9 * heightM * heightM

        return when (goal) {

            "FAT_LOSS" -> maxNormal

            "MUSCLE_GAIN" -> {
                if (currentWeight < maxNormal)
                    maxNormal
                else
                    currentWeight
            }

            else -> currentWeight
        }
    }
}