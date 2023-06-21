package com.f8fit.gmaps_udemy.topics

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.f8fit.gmaps_udemy.R
import com.f8fit.gmaps_udemy.common.Locations.cuspide
import com.f8fit.gmaps_udemy.common.Utils
import com.f8fit.gmaps_udemy.common.Utils.dp
import com.f8fit.gmaps_udemy.databinding.ActivityFirstMapBinding
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.MarkerOptions

class ControlGesturesActivity : AppCompatActivity(), OnMapReadyCallback {

    private lateinit var binding: ActivityFirstMapBinding
    private lateinit var map: GoogleMap

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFirstMapBinding.inflate(layoutInflater)
        setContentView(binding.root)

        Utils.binding = binding

        val mapFragment = supportFragmentManager.findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)

        setupToggle()
    }

    override fun onMapReady(googleMap: GoogleMap) {
        map = googleMap

        map.addMarker(MarkerOptions().position(cuspide).title("Cuspide Real del Monte"))
        map.moveCamera(CameraUpdateFactory.newLatLngZoom(cuspide,12f))

        //map.isMyLocationEnabled = true
        map.uiSettings.apply {
            //isMyLocationButtonEnabled = true
            isZoomControlsEnabled = true
            isCompassEnabled  = false
            isMapToolbarEnabled = true
            isRotateGesturesEnabled = true
            isZoomGesturesEnabled = false
            isTiltGesturesEnabled = false
        }

        map.setPadding(0,0,0, dp(64))
    }

    private fun setupToggle(){
        binding.toggleGroup.addOnButtonCheckedListener { group, checkedId, isChecked ->
            if(isChecked){
                map.mapType = when(checkedId){
                    R.id.btnNormal -> GoogleMap.MAP_TYPE_NORMAL
                    R.id.btnHybrid -> GoogleMap.MAP_TYPE_HYBRID
                    R.id.btnSatellite -> GoogleMap.MAP_TYPE_SATELLITE
                    R.id.btnTerrain -> GoogleMap.MAP_TYPE_TERRAIN
                    else -> GoogleMap.MAP_TYPE_NONE // Ideal para apps con permisos
                }
            }
        }
    }
}