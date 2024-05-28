package com.example.technicaltest.ui

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import com.example.technicaltest.core.Event
import com.example.technicaltest.core.MovieErrorModel

@Composable
fun ErrorDialog(
    error: Event<MovieErrorModel>? = null,
    onOKClicked: () -> Unit = {}
    ) {
    val errorModel = error?.getContent() ?: return
    val showDialog = remember {
        mutableStateOf(true)
    }
    if(showDialog.value){
        Dialog(
            onDismissRequest = {showDialog.value = false},
            properties = DialogProperties(dismissOnBackPress = false, dismissOnClickOutside = false)
        ) {
            Card {
               Column(modifier = Modifier.background(Color.White)
                   .padding(16.dp),
                   horizontalAlignment = Alignment.CenterHorizontally
               ) {
                   Text(
                       text = errorModel.title.toString(),
                       style = TextStyle(fontSize = 18.sp, color = Color(0xFF0E0D0D), fontWeight = FontWeight.W400)
                   )
                   Spacer(modifier = Modifier.size(8.dp))
                   Text(
                       text = errorModel.message.toString(),
                       style = TextStyle(fontSize = 12.sp, color = Color(0xFF0E0D0D), fontWeight = FontWeight.W400)
                   )
                   Spacer(modifier = Modifier.size(16.dp))
                   Row(modifier = Modifier.fillMaxWidth().padding(16.dp),
                       horizontalArrangement = Arrangement.SpaceAround) {
                       Text(
                           modifier = Modifier.clickable {
                               showDialog.value = false
                               onOKClicked.invoke() },
                           text = "OK",
                           style = TextStyle(fontSize = 12.sp, color = Color(0xFF0E0D0D), fontWeight = FontWeight.W600)
                       )
                       Spacer(modifier = Modifier.size(8.dp))
                       Text(
                           modifier = Modifier.clickable { showDialog.value = false },
                           text = "Cancel",
                           style = TextStyle(fontSize = 12.sp, color = Color(0xFF0E0D0D), fontWeight = FontWeight.W600)
                       )
                   }
               }
            }
        }
    }
}