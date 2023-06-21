package com.f8fit.gmaps_udemy.topics.infoWindow

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import com.f8fit.gmaps_udemy.R
import com.f8fit.gmaps_udemy.databinding.InfoHillBinding
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.model.Marker

class HillAdapter (context: Context) : GoogleMap.InfoWindowAdapter{

    private val binding: InfoHillBinding?
    init {
        val viewRoot = LayoutInflater.from(context).inflate(R.layout.info_hill, null)
        binding = InfoHillBinding.bind(viewRoot)
    }

    override fun getInfoContents(marker: Marker): View? {
        binding?.let {
            it.imgHill.setImageResource(R.drawable.ic_local_florist)
            it.rbScore.rating = 4.7f
            it.tvName.text = "Gaya"
            it.tvDescription.text = "Gestión y análisis de información, caracterización y seguimiento del cultivo de la vainilla en el país, de las diferentes regiones y de sus productores."

        }
        return  binding?.root
    }

    override fun getInfoWindow(p0: Marker): View? {
        return null
    }
}