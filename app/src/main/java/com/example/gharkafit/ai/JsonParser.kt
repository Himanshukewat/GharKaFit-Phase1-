package com.example.gharkafit.ai

import kotlinx.serialization.json.Json

object JsonParser {
    private val json = Json {
        ignoreUnknownKeys = true
    }

    fun parse(response: String): MealAnalysisResult {
        return json.decodeFromString(response)
    }
}