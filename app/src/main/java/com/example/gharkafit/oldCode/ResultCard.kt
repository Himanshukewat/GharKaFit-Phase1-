//package com.example.gharkafit.ui.component
//
//import androidx.compose.foundation.layout.*
//import androidx.compose.material3.*
//import androidx.compose.runtime.Composable
//import androidx.compose.ui.Modifier
//import androidx.compose.ui.unit.dp
//
//@Composable
//fun ResultCard(
//    title: String,
//    value: String,
//    subtitle: String
//) {
//
//    Card(
//        modifier = Modifier.fillMaxWidth(),
//        colors = CardDefaults.cardColors(
//            containerColor = MaterialTheme.colorScheme.surfaceVariant
//        )
//    ) {
//
//        Column(
//            modifier = Modifier.padding(16.dp),
//            verticalArrangement = Arrangement.spacedBy(4.dp)
//        ) {
//
//            Text(
//                text = title,
//                style = MaterialTheme.typography.titleMedium
//            )
//
//            Text(
//                text = value,
//                style = MaterialTheme.typography.headlineSmall
//            )
//
//            Text(
//                text = subtitle,
//                style = MaterialTheme.typography.bodyMedium,
//                color = MaterialTheme.colorScheme.onSurfaceVariant
//            )
//        }
//    }
//}