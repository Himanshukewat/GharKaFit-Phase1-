package com.example.gharkafit.ai

import com.google.genai.kotlin.Client
import com.example.gharkafit.BuildConfig

class GeminiRepository {
    private val client = Client(
        apiKey = BuildConfig.GEMINI_API_KEY
    )
}