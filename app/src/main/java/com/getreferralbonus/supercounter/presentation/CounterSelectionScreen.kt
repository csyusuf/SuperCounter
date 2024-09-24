package com.getreferralbonus.supercounter.presentation

import android.content.Context
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.TextButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun CounterSelectionScreen(navigateToCounter: () -> Unit) {
    val context = LocalContext.current
    val counterLimits = listOf(33, 100, 150, 129) // Predefined limits

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Black)
            .padding(16.dp),
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = "Select",
            color = Color.White,
            fontSize = 18.sp,
            modifier = Modifier.padding(bottom = 16.dp)
        )
        counterLimits.forEach { limit ->
            TextButton(
                onClick = {
                    // Save the selected limit to SharedPreferences
                    context.getSharedPreferences("MySettings", Context.MODE_PRIVATE)
                        .edit()
                        .putInt("counterLimit", limit)
                        .apply()
                    // Navigate to the counter screen
                    navigateToCounter()
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 4.dp)
            ) {
                Text(
                    text = "Limit: $limit",
                    color = Color.White,
                    fontSize = 16.sp
                )
            }
        }
    }
}
