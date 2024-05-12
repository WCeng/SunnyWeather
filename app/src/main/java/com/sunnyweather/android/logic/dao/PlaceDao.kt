package com.sunnyweather.android.logic.dao

import android.content.Context
import com.google.gson.Gson
import com.sunnyweather.android.SunnyWeatherApp
import com.sunnyweather.android.logic.model.Place

object PlaceDao {

    fun savePlace(place: Place) {
        sp().edit().putString("place", Gson().toJson(place)).apply()
    }

    fun getSavedPlace(): Place {
        val string = sp().getString("place", "")
        return Gson().fromJson(string, Place::class.java)
    }

    fun isPlaceSaved(): Boolean {
        return sp().contains("place")
    }

    private fun sp() =
        SunnyWeatherApp.content.getSharedPreferences("sunny_weather", Context.MODE_PRIVATE)
}