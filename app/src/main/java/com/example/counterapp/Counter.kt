package com.example.counterapp

import android.content.Context
import android.content.SharedPreferences
import android.os.Build
import android.os.VibrationEffect
import android.os.Vibrator
import androidx.annotation.RequiresApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.scale
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.counterapp.ui.theme.Purple40
import com.example.counterapp.ui.theme.Purple80
import com.example.counterapp.ui.theme.PurpleGrey80

const val COUNT_PREF_KEY = "count_pref_key"

@Preview
@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun TasbihCounter() {
    val context = LocalContext.current
    var count by remember { mutableStateOf(loadCount(context)) }


    Box(modifier = Modifier.fillMaxSize()) {

//        Image(modifier=Modifier.fillMaxSize() ,
//            contentScale = ContentScale.FillBounds
//            ,
//            painter = painterResource(id = R.drawable.back1), contentDescription = "")

    Column(

        modifier = Modifier
            .fillMaxSize()
            .background(Purple80)
          ,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        ResetButton { count = 0 }


        Text(
            modifier = Modifier
                .height(70.dp)
                .width(250.dp)
                .background(
                    shape = RoundedCornerShape(10.dp),
                    color = Color.Green
                ), text = count.toString(), fontFamily = FontFamily.Cursive,
            fontSize = 50.sp,
            textAlign = TextAlign.Center,
            color = Color.Black

        )



        Button(modifier = Modifier
            .weight(1f)
            .fillMaxWidth()
            .padding(10.dp)
,            colors = ButtonDefaults.buttonColors(
                containerColor = PurpleGrey80
            ),
            onClick = {
                count++
                saveCount(context, count)
                vibrateShort(context)
                if (count % 100 == 0) {
                    vibrateLong(context)
                }
            }) {
            Text(text = "اینجا بشمار", color = Color.Black, fontSize = 18.sp)
        }

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
fun ResetButton(resetAction: () -> Unit = { 0 }) {
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

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(110.dp)
            .padding(top = 10.dp, end = 10.dp),
        horizontalArrangement = Arrangement.End,
        verticalAlignment = Alignment.Bottom,
    ) {

        Button(modifier = Modifier
            .size(78.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = PurpleGrey80
            ),

            onClick = { showDialog = true }) {
            Text(text = "ریست", color = Color.Black, fontSize = 14.sp)
        }
    }

}
