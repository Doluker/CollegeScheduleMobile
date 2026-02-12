package com.example.collegeschedulemobile.data.repository

import com.example.collegeschedulemobile.data.api.ScheduleApi
import com.example.collegeschedulemobile.data.dto.ScheduleByDateDto

class ScheduleRepository (private val api: ScheduleApi) {
    suspend fun loadSchedule(group: String): List<ScheduleByDateDto> {
        return api.getSchedule(
            groupName = group,
            start = "2026-02-12",
            end = "2026-02-17"
        )
    }
}