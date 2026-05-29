//package com.example.gharkafit.ui.screen.dailySummary
//
//import androidx.compose.foundation.layout.*
//import androidx.compose.material3.*
//import androidx.compose.runtime.*
//import androidx.compose.ui.Modifier
//import androidx.compose.ui.unit.dp
//import androidx.compose.ui.platform.LocalContext
//import androidx.lifecycle.viewmodel.compose.viewModel
//import com.example.gharkafit.data.MainDatabase
//import com.example.gharkafit.ui.component.SummaryCard
//import com.example.gharkafit.ui.component.FeedbackCard
//
//@Composable
//fun DailySummaryScreen(
//    onDone: () -> Unit
//) {
//
//    val context = LocalContext.current
//    val db = MainDatabase.getDatabase(context)
//
//    val viewModel: DailySummaryVM = viewModel(
//        factory = DailySummaryVMF(db.mealDao(), db.userDao())
//    )
//
//    val calories by viewModel.calories.collectAsState()
//    val protein by viewModel.protein.collectAsState()
//
//    Column(
//        modifier = Modifier
//            .fillMaxSize()
//            .padding(16.dp),
//
//        verticalArrangement = Arrangement.spacedBy(20.dp)
//    ) {
//
//        Text(
//            text = "Today's Summary 📊",
//            style = MaterialTheme.typography.headlineSmall
//        )
//
//        SummaryCard(
//            title = "Calories",
//            taken = calories,
//            target = 2000,
//            unit = "kcal"
//        )
//
//        SummaryCard(
//            title = "Protein",
//            taken = protein.toInt(),
//            target = 80,
//            unit = "g"
//        )
//
//        Spacer(modifier = Modifier.weight(1f))
//
//        Button(
//            onClick = {
//                onDone()
//            },
//            modifier = Modifier.fillMaxWidth()
//        ) {
//            Text("Done for Today")
//        }
//    }
//}