package com.getreferralbonus.supercounter.presentation

import android.content.Context
import android.os.VibrationEffect
import android.os.Vibrator
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.wear.compose.material.MaterialTheme
import androidx.wear.compose.material.Text
import com.getreferralbonus.supercounter.presentation.theme.SuperCounterTheme

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun CounterApp() {
    val context = LocalContext.current
    val limit = context.getSharedPreferences("MySettings", Context.MODE_PRIVATE)
        .getInt("counterLimit", 100)
    var showResetDialog by remember { mutableStateOf(false) }
    var count by remember { mutableStateOf(0) }
    var disableLimit by remember { mutableStateOf(false)}

    SuperCounterTheme {

        val vibrator = context.getSystemService(Context.VIBRATOR_SERVICE) as Vibrator
        var longPress by remember { mutableStateOf(false) }

        if (showResetDialog) {
            AlertDialog(
                onDismissRequest = { showResetDialog = false },
                modifier = Modifier.padding(15.dp, 30.dp, 15.dp, 70.dp).background(Color.Transparent).fillMaxWidth(),
                title = { Text("Reset Counter $count", style = TextStyle(fontSize = 10.sp, color = Color.Black, fontWeight = FontWeight.SemiBold)) },
                confirmButton = {
                    TextButton(onClick = {
                        count = 0
                        showResetDialog = false
                        vibrator.cancel()
                    },
                        contentPadding = PaddingValues(start = 4.dp, top = 4.dp, end = 4.dp, bottom = 4.dp),
                        modifier = Modifier.background(Color.Red).wrapContentSize()
                    ) {
                        Text("Reset", style = TextStyle(fontSize = 10.sp, color = Color.Black))
                    }
                },
                dismissButton = {
                    TextButton(onClick = {
                        showResetDialog = false
                        disableLimit = true
                        vibrator.cancel()
                                         },
                        contentPadding = PaddingValues(start = 4.dp, top = 4.dp, end = 4.dp, bottom = 4.dp),
                        modifier = Modifier.background(Color.LightGray).wrapContentSize()) {
                        Text("Cancel", style = TextStyle(fontSize = 10.sp, color = Color.Black))
                    }
                }
            )
        }

        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.DarkGray),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(modifier = Modifier.fillMaxWidth(),
                textAlign = TextAlign.Center,
                color = Color.White,
                text = "Count")
            Box(
                modifier = Modifier
                    .size(150.dp)
                    .clip(CircleShape)
                    .background(Color.Black)
                    .pointerInput(Unit) {
                        detectTapGestures(
                            onPress = {
                                tryAwaitRelease()
                                if(!longPress)
                                {
                                    count++
                                    if (!disableLimit && count >= limit) {
                                        showResetDialog = true
                                        vibrator.vibrate(VibrationEffect.createWaveform(longArrayOf(0, 100, 500), 0))
                                    }
                                    vibrator.vibrate(VibrationEffect.createOneShot(50, VibrationEffect.DEFAULT_AMPLITUDE))
                                }
                                longPress = false
                            },
                            onLongPress = {
                                count = 0
                                longPress = true
                                vibrator.vibrate(VibrationEffect.createOneShot(50, VibrationEffect.DEFAULT_AMPLITUDE))
                            }
                        )
                    })
            {
                Text(text = "$count"
                    , fontSize = MaterialTheme.typography.title1.fontSize
                    , modifier = Modifier.align(Alignment.Center))
            }
        }
    }
}