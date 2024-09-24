package com.getreferralbonus.supercounter.presentation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable

@Composable
fun AppNavHost(navController: NavHostController) {
    NavHost(navController, startDestination = "main") {
        composable("main") {
            MainScreen(
                navigateToCounterSelection = { navController.navigate("counterSelection") },
                navigateToSettings = { navController.navigate("settings") } // Implement your settings navigation
            )
        }
        composable("counterSelection") {
            CounterSelectionScreen(
                navigateToCounter = { navController.navigate("counterScreen") }
            )
        }
        composable("counterScreen") {
            CounterApp()
        }
        // Add other composable destinations as needed
    }
}
