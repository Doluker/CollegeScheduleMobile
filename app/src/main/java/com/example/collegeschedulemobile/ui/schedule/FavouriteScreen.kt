package com.example.collegeschedulemobile.ui.schedule

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import com.example.collegeschedulemobile.data.dto.ScheduleByDateDto
import com.example.collegeschedulemobile.data.local.FavoritesManager
import com.example.collegeschedulemobile.data.network.RetrofitInstance
import com.example.collegeschedulemobile.ui.components.GroupDropDown
import com.example.collegeschedulemobile.utils.getWeekDateRange
import kotlinx.coroutines.launch

@Composable
fun FavouriteScreen() {
    var schedule by remember { mutableStateOf<List<ScheduleByDateDto>>(emptyList()) }
    var loading by remember { mutableStateOf(true) }
    var error by remember { mutableStateOf<String?>(null) }

    var groups by remember { mutableStateOf<List<String>>(emptyList()) }
    var selectedGroup by remember { mutableStateOf("") }

    val context = LocalContext.current
    val favoritesManager = remember { FavoritesManager(context) }
    val favorites by favoritesManager.favorites.collectAsState(initial = emptySet())

    if (selectedGroup.isEmpty() && favorites.isNotEmpty()) {
        selectedGroup = favorites.first()
    }

    LaunchedEffect(selectedGroup) {
        if (selectedGroup.isEmpty() || !favorites.contains(selectedGroup)) return@LaunchedEffect
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
        groups = favorites.toList()
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