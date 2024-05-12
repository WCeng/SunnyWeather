package com.sunnyweather.android.ui.place

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.ImageView
import android.widget.Toast
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.sunnyweather.android.MainActivity
import com.sunnyweather.android.R
import com.sunnyweather.android.ui.weather.WeatherActivity

class PlaceFragment : Fragment() {

    val viewModel by lazy {
        ViewModelProvider(this)[PlaceViewModel::class.java]
    }

    private lateinit var placeInput: EditText
    private lateinit var rv: RecyclerView
    private lateinit var bgImageView: ImageView
    private lateinit var placeAdapter: PlaceAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? =
        LayoutInflater.from(context).inflate(R.layout.fragment_place, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        if (activity is MainActivity && viewModel.isPlaceSaved()) {
            val place = viewModel.getSavedPlace()
            val intent = Intent(activity, WeatherActivity::class.java).apply {
                putExtra("location_lng", place.location.lng)
                putExtra("location_lat", place.location.lat)
                putExtra("place_name", place.name)
            }

            activity?.startActivity(intent)
            activity?.finish()
            return
        }


        placeInput = view.findViewById(R.id.searchPlaceInput)
        rv = view.findViewById(R.id.rv)
        bgImageView = view.findViewById(R.id.bgImageView)

        rv.layoutManager = LinearLayoutManager(context)
        placeAdapter = PlaceAdapter(this, viewModel.placeList)
        rv.adapter = placeAdapter


        placeInput.addTextChangedListener {
            val query = placeInput.text.toString().trim()
            if (query.isNotEmpty()) {
                viewModel.searchPlace(query)
            } else {
                viewModel.placeList.clear()
                placeAdapter.notifyDataSetChanged()
                bgImageView.visibility = View.VISIBLE
                rv.visibility = View.GONE
            }
        }

        viewModel.placeLiveData.observe(viewLifecycleOwner, Observer { result ->
            val places = result.getOrNull()
            if (places != null) {
                viewModel.placeList.clear()
                viewModel.placeList.addAll(places)
                placeAdapter.notifyDataSetChanged()
                bgImageView.visibility = View.GONE
                rv.visibility = View.VISIBLE
            } else {
                Toast.makeText(
                    context,
                    context?.resources?.getText(R.string.notAnyPlacesForQuery),
                    Toast.LENGTH_SHORT
                ).show()
                result.exceptionOrNull()?.printStackTrace()
            }
        })
    }
}