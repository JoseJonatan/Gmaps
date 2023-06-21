package com.f8fit.gmaps_udemy.topics

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.f8fit.gmaps_udemy.R
import com.f8fit.gmaps_udemy.common.Locations.cic
import com.f8fit.gmaps_udemy.common.Locations.clusterV
import com.f8fit.gmaps_udemy.common.Utils
import com.f8fit.gmaps_udemy.databinding.ActivityFirstMapBinding
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.Marker
import com.google.android.gms.maps.model.MarkerOptions

class EventsActivity : AppCompatActivity(), OnMapReadyCallback,
    GoogleMap.OnMarkerClickListener, GoogleMap.OnMarkerDragListener {

    private val TAG = "EventsActivity"

    private lateinit var binding: ActivityFirstMapBinding
    private lateinit var map: GoogleMap

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFirstMapBinding.inflate(layoutInflater)
        setContentView(binding.root)

        Utils.binding = binding

        val mapFragment = supportFragmentManager.findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)
    }

    override fun onMapReady(googleMap: GoogleMap) {
        map = googleMap
        map.moveCamera(CameraUpdateFactory.newLatLngZoom(cic, 12f))

        val cic = map.addMarker(MarkerOptions().position(cic).title("CIC - IPN"))
        var cluster: Marker? = null
        map.setOnMapClickListener {
            Toast.makeText(this, "Click", Toast.LENGTH_SHORT).show()
            cluster = map.addMarker(MarkerOptions()
                .position(clusterV)
                .title("Cluster IPN")
            )
            cic?.zIndex = 1f
        }
        map.setOnMapClickListener {
            Toast.makeText(this, "Click largo", Toast.LENGTH_LONG).show()
            cluster?.remove()
        }

        cic?.tag = "Open to walk"
        map.setOnMarkerClickListener (this)
    }

    override fun onMarkerClick(marker: Marker): Boolean {
        Log.i(TAG,"onMarkerClick: ${marker.tag}")
        return false
    }

    override fun onMarkerDrag(p0: Marker) {
        TODO("Not yet implemented")
    }

    override fun onMarkerDragEnd(p0: Marker) {
        TODO("Not yet implemented")
    }

    override fun onMarkerDragStart(p0: Marker) {
        TODO("Not yet implemented")
    }


}