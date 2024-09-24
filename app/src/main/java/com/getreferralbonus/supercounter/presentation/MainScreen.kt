package com.getreferralbonus.supercounter.presentation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.wear.compose.material.Text

@Composable
fun MainScreen(navigateToCounterSelection: () -> Unit, navigateToSettings: () -> Unit) {
    var showMenu by remember { mutableStateOf(true) }
    val backgroundColor = Color.Black
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(20.dp)
            .wrapContentSize(Alignment.Center),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        DropdownMenu(
            expanded = showMenu,
            onDismissRequest = { showMenu = false },
            modifier = Modifier
                .fillMaxSize()
                .background(backgroundColor)
                .wrapContentSize(Alignment.Center)
        ) {
            DropdownMenuItem(
                text = { Text("Settings", modifier = Modifier.fillMaxWidth()) },
                onClick = {
                    navigateToSettings()
                    showMenu = false
                },
                modifier = Modifier.fillMaxWidth()
            )
            DropdownMenuItem(
                text = { Text("Choose Counter", modifier = Modifier.fillMaxWidth()) },
                onClick = {
                    navigateToCounterSelection()
                    showMenu = false
                },
                modifier = Modifier.fillMaxWidth()
            )
        }
    }
}
