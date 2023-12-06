package com.example.counterapp

import android.content.Context
import android.content.SharedPreferences
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp

const val COUNT_PREF_KEY = "count_pref_key"

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
        }) {
            Text(text = "افزودن")
        }

        Button(onClick = {
            // Show a confirmation dialog here if needed
            count = 0
            saveCount(context, count)
        }) {
            Text(text = "ریست")
        }
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
