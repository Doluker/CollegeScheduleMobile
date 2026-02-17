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
import com.example.collegeschedulemobile.data.dto.LessonGroupPart
import com.example.collegeschedulemobile.data.dto.LessonPartDto
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
                TimeBlock(item.time)
                Spacer(modifier = Modifier.width(16.dp))
                Column(modifier = Modifier.weight(1f))
                {
                    val parts = item.groupParts.filterValues { it != null }
                    parts.entries.forEachIndexed { index, entry ->
                        val partType = entry.key
                        val info = entry.value!!
                        LessonInfoBlock(
                            info = info,
                            partLabel = when (partType)
                            {
                                LessonGroupPart.SUB1 -> "1 подгруппа"
                                LessonGroupPart.SUB2 -> "2 подгруппа"
                                else -> null
                            }
                        )
                        if (index < parts.size - 1)
                        {
                            HorizontalDivider(
                                modifier = Modifier.padding(vertical = 8.dp),
                                color = Color.DarkGray,
                                thickness = 0.5.dp
                            )
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun TimeBlock(time: String) {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        val timeParts = time.split('-')
        Text(
            text = timeParts.getOrNull(0) ?: "??:??",
            color = Color.White,
            fontSize = 16.sp,
            fontWeight = FontWeight.Bold
        )
        Text(
            text = timeParts.getOrNull(1) ?: "??:??",
            color = Color.Gray,
            fontSize = 12.sp
        )
    }
}

@Composable
fun LessonInfoBlock(info: LessonPartDto, partLabel: String?) {
    Column {
        if (partLabel != null) {
            Text(
                text = partLabel,
                color = Color.Gray,
                fontSize = 10.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(bottom = 2.dp)
            )
        }
        Text(
            text = info.subject,
            color = mainRed,
            fontSize = 18.sp,
            fontWeight = FontWeight.SemiBold,
            maxLines = 1
        )
        Spacer(modifier = Modifier.height(4.dp))
        Text(
            text = info.teacher,
            color = Color.LightGray,
            fontSize = 14.sp
        )
        Text(
            text = "Ауд. ${info.classroom} (${info.building})",
            color = Color.Gray,
            fontSize = 12.sp
        )
    }
}