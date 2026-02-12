package com.example.collegeschedulemobile

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountBox
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.tooling.preview.PreviewScreenSizes
import com.example.collegeschedulemobile.data.api.ScheduleApi
import com.example.collegeschedulemobile.data.repository.ScheduleRepository
import com.example.collegeschedulemobile.ui.schedule.ScheduleScreen
import com.example.collegeschedulemobile.ui.theme.CollegeScheduleMobileTheme
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import androidx.compose.material3.NavigationBarItem

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            CollegeScheduleMobileTheme {
                CollegeScheduleApp()
            }
        }
    }
}
@PreviewScreenSizes
@Composable
fun CollegeScheduleApp() {
    var currentDestination by rememberSaveable {
        mutableStateOf(AppDestinations.HOME) }
    val retrofit = remember {
        Retrofit.Builder()
            .baseUrl("http://192.168.1.101:5117/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
    val api = remember { retrofit.create(ScheduleApi::class.java) }
    val repository = remember { ScheduleRepository(api) }
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        bottomBar = {
            androidx.compose.material3.NavigationBar {
                AppDestinations.entries.forEach { destination ->
                    NavigationBarItem(
                        selected = currentDestination == destination,
                        onClick = { currentDestination = destination },
                        icon = { Icon(destination.icon, contentDescription = destination.label) },
                        label = { Text(destination.label) }
                    )
                }
            }
        }
    ) { innerPadding ->
        // Контент экрана
        androidx.compose.foundation.layout.Box(modifier = Modifier.padding(innerPadding)) {
            when (currentDestination) {
                AppDestinations.HOME -> ScheduleScreen()
                AppDestinations.FAVORITES -> Text("Избранное")
                AppDestinations.PROFILE -> Text("Профиль")
            }
        }
    }
}
enum class AppDestinations(
    val label: String,
    val icon: ImageVector,
) {
    HOME("Home", Icons.Default.Home),
    FAVORITES("Favorites", Icons.Default.Favorite),
    PROFILE("Profile", Icons.Default.AccountBox),
}