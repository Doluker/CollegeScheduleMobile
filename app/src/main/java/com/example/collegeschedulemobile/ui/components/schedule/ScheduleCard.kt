package com.example.collegeschedulemobile.ui.components.schedule

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.collegeschedulemobile.data.dto.LessonDto
import com.example.collegeschedulemobile.ui.theme.cardBackground
import com.example.collegeschedulemobile.ui.theme.mainRed

@Composable
fun ScheduleCard(item: LessonDto) {

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 6.dp),
        shape = RoundedCornerShape(12.dp),
        colors = CardDefaults.cardColors(containerColor = cardBackground),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    )
    {
        Row(
            modifier = Modifier
                .height(IntrinsicSize.Min)
                .fillMaxWidth()
        )
        {
            val info = item.groupParts.values.filterNotNull().firstOrNull()
            Box(
                modifier = Modifier
                    .fillMaxHeight()
                    .width(5.dp)
                    .background(mainRed)
            )
            Row(
                modifier = Modifier
                    .padding(16.dp)
                    .fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            )
            {
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    val timeParts = item.time.split('-')
                    val startTime = timeParts.getOrNull(0) ?: "??:??"
                    val endTime = timeParts.getOrNull(1) ?: "??:??"
                    Text(
                        text = startTime,
                        color = Color.White,
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Bold
                    )
                    Text(
                        text = endTime,
                        color = Color.Gray,
                        fontSize = 12.sp
                    )
                }
                Spacer(modifier = Modifier.width(16.dp))
                Column(modifier = Modifier.weight(1f))
                {
                    Text(
                        text = info?.subject.toString(),
                        color = mainRed,
                        fontSize = 18.sp,
                        fontWeight = FontWeight.SemiBold,
                        maxLines = 1
                    )
                    Spacer(modifier = Modifier.height(4.dp))
                    Text(
                        text = info?.teacher.toString(),
                        color = Color.LightGray,
                        fontSize = 14.sp
                    )
                    Text(
                        text = "Ауд. ${info?.classroom} (${info?.building})",
                        color = Color.Gray,
                        fontSize = 12.sp
                    )
                }
            }
        }
    }
}