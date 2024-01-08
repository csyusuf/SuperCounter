package com.getreferralbonus.supercounter.presentation

import androidx.compose.runtime.*
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.*
import android.content.Context
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.wear.compose.material.MaterialTheme

@Composable
fun SettingsScreen(context: Context
                   , onLimitChange: (Int) -> Unit
                   , currentLimit: Int
                   , navigateBack: () -> Unit) {
    var limit by remember { mutableStateOf(currentLimit.toString()) }

    Column(horizontalAlignment = Alignment.CenterHorizontally
        , modifier = Modifier.background(Color.White)
            .fillMaxSize()
        ,verticalArrangement = Arrangement.Center) {
        Text("Set Counter Limit", style = MaterialTheme.typography.title3)
        Spacer(modifier = Modifier.height(10.dp))
        TextField(
            value = limit,
            onValueChange = { limit = it },
            label = { Text("Limit") }
        )
        Spacer(modifier = Modifier.height(10.dp))
        Button(onClick = { onLimitChange(limit.toIntOrNull() ?: currentLimit)
                            navigateBack()
        }) {
            Text("Save")
        }
    }
}

@Preview
@Composable
fun PreviewSettingsScreen() {
    SettingsScreen(context = LocalContext.current, onLimitChange = {}, currentLimit = 100, navigateBack = {})
}
