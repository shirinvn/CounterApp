package com.example.counterapp

import android.content.Context
import android.content.SharedPreferences
import android.os.Build
import android.os.VibrationEffect
import android.os.Vibrator
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext

const val COUNT_PREF_KEY = "count_pref_key"

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun TasbihCounter() {
    val context = LocalContext.current
    var count by remember { mutableStateOf(loadCount(context)) }

    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = count.toString())



        Button(onClick = {
            count++
            saveCount(context, count)
            // Add vibration here for each click if needed
            vibrateShort(context)
            if (count % 100 == 0) {
                vibrateLong(context)
            }
        }) {
            Text(text = "افزودن")
        }

        ResetButton { count = 0 }

    }
}

fun loadCount(context: Context): Int {
    val sharedPreferences: SharedPreferences = context.getSharedPreferences("CountPref", Context.MODE_PRIVATE)
    return sharedPreferences.getInt(COUNT_PREF_KEY, 0)
}

fun saveCount(context: Context, count: Int) {
    val sharedPreferences: SharedPreferences = context.getSharedPreferences("CountPref", Context.MODE_PRIVATE)
    val editor: SharedPreferences.Editor = sharedPreferences.edit()
    editor.putInt(COUNT_PREF_KEY, count)
    editor.apply()
}


@RequiresApi(Build.VERSION_CODES.O)
fun vibrateShort(context: Context) {
    val vibrator = context.getSystemService(Context.VIBRATOR_SERVICE) as Vibrator?
    vibrator?.let {
        it.vibrate(VibrationEffect.createOneShot(50, VibrationEffect.DEFAULT_AMPLITUDE))
    }
}

@RequiresApi(Build.VERSION_CODES.O)
fun vibrateLong(context: Context) {
    val vibrator = context.getSystemService(Context.VIBRATOR_SERVICE) as Vibrator?
    vibrator?.let {
        it.vibrate(VibrationEffect.createOneShot(500, VibrationEffect.DEFAULT_AMPLITUDE))
    }
}


@Composable
fun ResetButton(resetAction: () -> Unit = { 0 }){
    var showDialog by remember { mutableStateOf(false) }

    if (showDialog) {
        AlertDialog(
            onDismissRequest = { showDialog = false },
            title = { Text(text = "ریست کردن شمارنده") },
            text = { Text(text = "آیا مطمئنید می‌خواهید شمارنده را ریست کنید؟") },
            confirmButton = {
                Button(
                    onClick = {
                        resetAction() // اجرای عملیات ریست
                        showDialog = false
                    }
                ) {
                    Text(text = "تایید")
                }
            },
            dismissButton = {
                Button(
                    onClick = {
                        showDialog = false
                    }
                ) {
                    Text(text = "لغو")
                }
            }
        )
    }

    Button(onClick = { showDialog = true }) {
        Text(text = "ریست")
    }
}
