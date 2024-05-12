package com.sunnyweather.android.ui.place

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.sunnyweather.android.R
import com.sunnyweather.android.logic.model.Place

class PlaceAdapter(private val placeList: List<Place>) :
    RecyclerView.Adapter<PlaceAdapter.PlaceViewModel>() {

    inner class PlaceViewModel(view: View) : RecyclerView.ViewHolder(view) {
        val placeNameTv: TextView = view.findViewById(R.id.placeNameTv)
        val addressTv: TextView = view.findViewById(R.id.addressTv)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PlaceViewModel {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_place, parent, false)
        return PlaceViewModel(view)
    }

    override fun getItemCount(): Int {
        return placeList.size
    }

    override fun onBindViewHolder(holder: PlaceViewModel, position: Int) {
        val place = placeList[position]
        holder.placeNameTv.text = place.name
        holder.addressTv.text = place.address
    }

}