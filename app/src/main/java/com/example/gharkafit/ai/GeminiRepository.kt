package com.example.gharkafit.ai

import com.example.gharkafit.BuildConfig
import com.google.genai.kotlin.Client

class GeminiRepository {
    private val client = Client(
        apiKey = BuildConfig.GEMINI_API_KEY
    )
    suspend fun analyzeMeal(userMessage: String): MealAnalysisResult {
//      don't forward to ai , that received structure according to our prompt
        val prompt = PromptBuilder.buildPrompt(userMessage)
        // they call gemini api
        val response = client.models.generateContent(
            model = "gemini-2.5-flash",
            text = prompt
        )
        // gemini json format store
        val json = response.text ?: throw Exception("Empty response from Gemini")
        return JsonParser.parse(json)
    }
}