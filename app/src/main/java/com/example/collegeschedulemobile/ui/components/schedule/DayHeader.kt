package com.example.collegeschedulemobile.ui.components.schedule

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.collegeschedulemobile.ui.theme.mainRed

@Composable
fun DayHeader(date: String, weekday: String) {
    val dateWithoutTime = date.split("T").getOrNull(0)
    val day = java.time.LocalDate.parse(dateWithoutTime).dayOfMonth
    val month = java.time.LocalDate.parse(dateWithoutTime).format(java.time.format.DateTimeFormatter.ofPattern("MMMM", java.util.Locale("ru")))
    Column(modifier = Modifier.fillMaxWidth().padding(top = 24.dp, bottom = 8.dp, start = 16.dp, end = 16.dp))
    {
        Row(verticalAlignment = Alignment.CenterVertically)
        {
            Box(modifier = Modifier.size(10.dp).background(mainRed))
            Spacer(modifier = Modifier.width(12.dp))
            Text(
                text = "$day $month",
                color = Color.White,
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
                style = MaterialTheme.typography.titleMedium
            )
            Spacer(modifier = Modifier.width(8.dp))
            Text(
                text = weekday.uppercase(),
                color = Color.Gray,
                fontSize = 14.sp,
                fontWeight = FontWeight.Normal
            )
        }
        Spacer(modifier = Modifier.height(8.dp))
        Box(modifier = Modifier.fillMaxWidth().height(1.dp).background(Color(51,51,51)))
    }
}