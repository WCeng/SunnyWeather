package com.sunnyweather.android

import android.annotation.SuppressLint
import android.app.Application
import android.content.Context

class SunnyWeatherApp : Application() {
    companion object {
        @SuppressLint("StaticFieldLeak")
        lateinit var content: Context
        const val TOKEN = "SWiLg8WtrXDkJS1e"
    }

    override fun onCreate() {
        super.onCreate()
        content = applicationContext
    }
}