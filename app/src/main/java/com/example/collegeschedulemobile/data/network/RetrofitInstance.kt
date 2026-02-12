package com.example.collegeschedulemobile.data.network

import com.example.collegeschedulemobile.data.api.ScheduleApi
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitInstance {
    private val retrofit = Retrofit.Builder()
        .baseUrl("http://192.168.1.101:5117/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()
    val api: ScheduleApi = retrofit.create(ScheduleApi::class.java)
}