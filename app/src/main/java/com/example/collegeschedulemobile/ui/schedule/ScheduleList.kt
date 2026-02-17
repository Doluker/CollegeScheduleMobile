    package com.example.collegeschedulemobile.ui.schedule

    import androidx.compose.foundation.layout.fillMaxSize
    import androidx.compose.foundation.layout.padding
    import androidx.compose.foundation.lazy.LazyColumn
    import androidx.compose.foundation.lazy.items
    import androidx.compose.material3.MaterialTheme
    import androidx.compose.material3.Text
    import androidx.compose.runtime.Composable
    import androidx.compose.ui.Modifier
    import androidx.compose.ui.unit.dp
    import com.example.collegeschedulemobile.data.dto.ScheduleByDateDto
    import com.example.collegeschedulemobile.ui.components.schedule.DayHeader
    import com.example.collegeschedulemobile.ui.components.schedule.ScheduleCard

    @Composable
    fun ScheduleList(data: List<ScheduleByDateDto>) {
        LazyColumn(modifier = Modifier.fillMaxSize()) {
            items(data) { day -> DayHeader(day.lessonDate, day.weekday)
                if (day.lessons.isEmpty())
                {
                    Text(
                        "Информация отсутствует",
                        style = MaterialTheme.typography.bodyMedium,
                        modifier = Modifier.padding(start = 16.dp, bottom = 8.dp)
                    )
                }
                else
                {
                    day.lessons.forEach { lesson -> ScheduleCard(lesson) }
                }
            }
        }
    }