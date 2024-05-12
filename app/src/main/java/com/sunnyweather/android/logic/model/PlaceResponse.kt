package com.sunnyweather.android.logic.model

import com.google.gson.annotations.SerializedName

data class PlaceResponse(val status: String, val places: List<Place>)

data class Place(
    val name: String,
    val locations: List<Location>,
    @SerializedName("formatted_address") val address: String
)

data class Location(val lat: String, val lng: String)