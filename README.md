![Kotlin](https://img.shields.io/badge/Kotlin-2.0-purple)

![Jetpack Compose](https://img.shields.io/badge/Jetpack%20Compose-UI-blue)

![Firebase](https://img.shields.io/badge/Firebase-Firestore-orange)

![Gemini AI](https://img.shields.io/badge/Google-Gemini-blue)

# HearthDiet 🥗

### AI-Powered Nutrition & Meal Tracking Android App

HearthDiet is a modern Android application that helps users track meals, monitor nutrition, and receive personalized health insights. It combines Room Database, Firebase Cloud Firestore, and Google Gemini AI to deliver an offline-first, AI-assisted nutrition tracking experience.

> **An AI-powered Android application for personalized nutrition tracking, meal analysis, and healthy lifestyle management.**

## 🚀 Features

- 🤖 AI Meal Analyzer (Gemini AI)
  - Analyze meals written in Hindi, English, or Hinglish
  - Detect multiple food items automatically
  - Estimate calories, protein, carbs, and fat
 
- ☁️ Cloud Sync
  - Firebase Authentication
  - Firestore Backup & Restore
  - Secure Firestore Security Rules
  - Multi-device synchronization using Firebase
  - Offline-first architecture using Room Database

- 📊 Health Analysis
  - BMI Calculation
  - Healthy Weight Range
  - Daily Nutrition Targets
  - Personalized Health Suggestions

- 🎯 Personalized Plan
  - Goal-based recommendations
  - Daily calorie & protein targets
  - Food recommendations
  - Focus areas based on fitness goal

- 🍽️ Meal Tracking
  - Add meals manually or using AI
  - Track calories and protein
  - Delete meals
  - Dashboard meal status

- 📈 Progress Tracking
  - Daily nutrition summary
  - Weekly progress insights
  - Consistency tracking

- 👤 Profile Management
  - Edit profile
  - Auto-save user data
  - Auto-login on app restart

---

## ✨ Highlights

- AI-powered natural language meal analysis
- Supports Indian meals and Hinglish input
- Offline-first architecture using Room Database
- Cloud synchronization using Firebase Firestore
- Multi-device data synchronization
- Clean MVVM Architecture

---
## 🧠 Tech Stack

- Kotlin
- Jetpack Compose
- Material 3
- MVVM Architecture
- Room Database
- Firebase Authentication
- Cloud Firestore
- Google Gemini AI
- Kotlin Coroutines
- Repository Pattern
- Jetpack Navigation
- Android Studio
- Git & GitHub

---

## 🏗 Architecture

```text
Jetpack Compose UI
        │
        ▼
    ViewModel
        │
        ▼
    Repository
   ┌────┴────┐
   ▼         ▼
 Room    Firestore
              │
              ▼
 Firebase Authentication

        Google Gemini AI
              ▲
              │
      AI Meal Analysis
```

## 📐 Calculation Logic

* BMI using standard formula
* BMR using Mifflin-St Jeor Equation
* Calories adjusted based on:

  * Activity Level
  * Goal (Deficit / Surplus)
* Protein based on body weight & goal

---

## 💡 Unique Idea

Unlike many traditional calorie-tracking apps, HearthDiet focuses on real Indian home-cooked meals instead of complex food databases.
> Healthy eating starts with home-cooked meals.

---
## 🚀 Future Improvements

- Water Intake Tracking
- Barcode Scanner
- Push Notifications
- Nutrition Charts & Analytics
- Wear OS Support
---

## ⚙️ Installation
1. Clone the repository
2. Open the project in Android Studio
3. Add your Gemini API Key in `local.properties`
4. Configure Firebase using your own `google-services.json`
5. Build & Run


---
## 📸 Screenshots

| Login | Dashboard | AI Meal Analyzer |
|-------|-----------|------------------|
| Image | Image | Image |

| Progress | Profile | Meal History |
|----------|---------|--------------|
| Image | Image | Image |

---

## 👨‍💻 Author

**Himanshu Kewat**

- B.Tech Computer Science
- Aspiring Android & Software Engineer

GitHub: [Himanshukewat](https://github.com/Himanshukewat)

LinkedIn: [Himanshu Kewat](https://www.linkedin.com/in/kewathimanshu/)



---

## 📌 Note

This project is built for learning, experimentation, and real-world problem solving.
