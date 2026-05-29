//package com.example.gharkafit.ui.component
//
//import androidx.compose.foundation.layout.*
//import androidx.compose.material3.*
//import androidx.compose.runtime.Composable
//import androidx.compose.ui.Modifier
//import androidx.compose.ui.unit.dp
//import com.example.gharkafit.model.DailyFeedback
//import com.example.gharkafit.model.FeedbackStatus
//
//@Composable
//fun FeedbackCard(
//    feedback: DailyFeedback
//) {
//
//    val color = when (feedback.status) {
//
//        FeedbackStatus.NEEDS_IMPROVEMENT ->
//            MaterialTheme.colorScheme.errorContainer
//
//        FeedbackStatus.GOOD_PROGRESS ->
//            MaterialTheme.colorScheme.secondaryContainer
//
//        FeedbackStatus.ON_TRACK ->
//            MaterialTheme.colorScheme.primaryContainer
//    }
//
//    Card(
//        modifier = Modifier.fillMaxWidth(),
//        colors = CardDefaults.cardColors(containerColor = color)
//    ) {
//
//        Column(
//            modifier = Modifier.padding(16.dp)
//        ) {
//
//            Text(
//                text = feedback.title,
//                style = MaterialTheme.typography.titleMedium
//            )
//
//            Spacer(modifier = Modifier.height(6.dp))
//
//            Text(
//                text = feedback.message,
//                style = MaterialTheme.typography.bodyMedium
//            )
//        }
//    }
//}