/* While this template provides a good starting point for using Wear Compose, you can always
 * take a look at https://github.com/android/wear-os-samples/tree/main/ComposeStarter and
 * https://github.com/android/wear-os-samples/tree/main/ComposeAdvanced to find the most up to date
 * changes to the libraries and their usages.
 */

package com.getreferralbonus.supercounter.presentation

import android.content.Context
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.*
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import com.getreferralbonus.supercounter.presentation.theme.SuperCounterTheme
import androidx.compose.runtime.remember

class MainActivity : ComponentActivity() {

    private val sharedPref by lazy { getSharedPreferences("MySettings", Context.MODE_PRIVATE) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            SuperCounterTheme {
                var currentScreen by remember { mutableStateOf(AppScreen.MainMenu) }

                when (currentScreen) {
                    AppScreen.MainMenu -> MainScreen(
                        navigateToCounter = { currentScreen = AppScreen.Counter },
                        navigateToSettings = { currentScreen = AppScreen.Settings }
                    )
                    AppScreen.Counter -> CounterApp()
                    AppScreen.Settings -> SettingsScreen(
                        context = this,
                        onLimitChange = { newLimit -> saveLimit(newLimit) },
                        currentLimit = getSavedLimit(),
                        navigateBack = { currentScreen = AppScreen.MainMenu }
                    )
                }
            }
        }
    }

    private fun saveLimit(limit: Int) {
        with(sharedPref.edit()) {
            putInt("counterLimit", limit)
            apply()
        }
    }
    private fun getSavedLimit(): Int {
        return sharedPref.getInt("counterLimit", 100) // Default limit is 100
    }
}

@Preview(device = Devices.WEAR_OS_SMALL_ROUND, showSystemUi = true)
@Composable
fun CounterAppPreview() {
    CounterApp()
}