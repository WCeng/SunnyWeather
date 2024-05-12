package com.sunnyweather.android.logic.network

import com.sunnyweather.android.SunnyWeatherApp
import com.sunnyweather.android.logic.model.PlaceResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query


interface PlaceService {
    @GET("v2/place?token=${SunnyWeatherApp.TOKEN}&lang=zh_CN")
    fun queryPlace(@Query("query") query: String): Call<PlaceResponse>
}