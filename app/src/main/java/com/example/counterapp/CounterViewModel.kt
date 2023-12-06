//package com.example.counterapp
//
//import androidx.compose.foundation.layout.*
//import androidx.compose.material.*
//import androidx.compose.runtime.*
//import androidx.compose.ui.Alignment
//import androidx.compose.ui.Modifier
//import androidx.compose.ui.platform.LocalContext
//import androidx.compose.ui.unit.dp
//import androidx.compose.ui.tooling.preview.Preview
//import androidx.lifecycle.ViewModel
//import android.os.VibrationEffect
//import android.os.Vibrator
//import androidx.compose.material3.AlertDialog
//import androidx.compose.material3.Button
//import androidx.compose.material3.Text
//
//class CounterViewModel : ViewModel() {
//    var count by mutableStateOf(0)
//
//    fun incrementCounter() {
//        count++
//        if (count % 100 == 0) {
//            vibrateLong()
//        } else {
//            vibrateShort()
//        }
//    }
//
//    fun resetCounter() {
//        count = 0
//    }
//
//    private fun vibrateShort() {
//        val vibrator = (LocalContext.current.getSystemService(Vibrator::class.java)) as Vibrator
//        vibrator.vibrate(VibrationEffect.createOneShot(50, VibrationEffect.DEFAULT_AMPLITUDE))
//    }
//
//    private fun vibrateLong() {
//        val vibrator = (LocalContext.current.getSystemService(Vibrator::class.java)) as Vibrator
//        vibrator.vibrate(VibrationEffect.createOneShot(500, VibrationEffect.DEFAULT_AMPLITUDE))
//    }
//}
//
//@Composable
//fun CounterApp(viewModel: CounterViewModel = viewModel()) {
//    val context = LocalContext.current
//
//    Column(
//        modifier = Modifier
//            .fillMaxSize()
//            .padding(16.dp),
//        horizontalAlignment = Alignment.CenterHorizontally,
//        verticalArrangement = Arrangement.Center
//    ) {
//        Text(text = "صلوات شمار: ${viewModel.count}")
//
//        Spacer(modifier = Modifier.height(16.dp))
//
//        Button(onClick = { viewModel.incrementCounter() }) {
//            Text(text = "افزودن صلوات")
//        }
//
//        Spacer(modifier = Modifier.height(16.dp))
//
//        Button(onClick = {
//            AlertDialog(
//                onDismissRequest = { },
//                title = { Text("ریست کردن شمارنده") },
//                confirmButton = {
//                    Button(onClick = {
//                        viewModel.resetCounter()
//                    }) {
//                        Text("تایید")
//                    }
//                },
//                dismissButton = {
//                    Button(onClick = { }) {
//                        Text("لغو")
//                    }
//                }
//            )
//        }) {
//            Text(text = "ریست کردن")
//        }
//    }
//}
//
//@Preview
//@Composable
//fun PreviewCounterApp() {
//    CounterApp()
//}
