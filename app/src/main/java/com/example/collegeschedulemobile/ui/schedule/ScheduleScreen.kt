package com.example.collegeschedulemobile.ui.schedule

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import com.example.collegeschedulemobile.data.dto.ScheduleByDateDto
import com.example.collegeschedulemobile.data.network.RetrofitInstance
import com.example.collegeschedulemobile.ui.components.GroupDropDown
import com.example.collegeschedulemobile.utils.getWeekDateRange
@Composable
fun ScheduleScreen() {
    var groups by remember { mutableStateOf<List<String>>(emptyList()) }
    var selectedGroup by remember { mutableStateOf("") }
    var schedule by remember { mutableStateOf<List<ScheduleByDateDto>>(emptyList()) }
    var loading by remember { mutableStateOf(true) }
    var error by remember { mutableStateOf<String?>(null) }
    LaunchedEffect(Unit)
    {
        try
        {
            groups = RetrofitInstance.api.getGroups()
            if (groups.isNotEmpty())
            {
                selectedGroup = groups[0]
            }
        }
        catch (e:Exception)
        {
            error = "Не удалось загрузить список групп: ${e.message}"
        }
    }
    LaunchedEffect(selectedGroup) {
        if (selectedGroup.isEmpty()) return@LaunchedEffect
        loading = true
        val (start, end) = getWeekDateRange()
        try {
            schedule = RetrofitInstance.api.getSchedule(
                selectedGroup,
                start,
                end
            )
        } catch (e: Exception) {
            error = e.message
        } finally {
            loading = false
        }
    }
    Column {
        if (groups.isNotEmpty())
        {
            GroupDropDown(
                groups = groups,
                selectedGroup = selectedGroup,
                onGroupSelected = { selectedGroup = it }
            )
        }
        when
        {
            loading -> CircularProgressIndicator()
            error != null -> Text("Ошибка: $error")
            else -> ScheduleList(schedule)
        }
    }
}