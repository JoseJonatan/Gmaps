package com.f8fit.gmaps_udemy.modes

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.f8fit.gmaps_udemy.R
import com.f8fit.gmaps_udemy.common.Locations
import com.f8fit.gmaps_udemy.common.Locations.papantla
import com.f8fit.gmaps_udemy.databinding.ActivityFirstMapBinding
import com.f8fit.gmaps_udemy.databinding.ActivityLiteModeBinding
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.MarkerOptions

class LiteModeActivity : AppCompatActivity(), OnMapReadyCallback {

    private lateinit var binding: ActivityLiteModeBinding
    private lateinit var map: GoogleMap

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLiteModeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val mapFragment = supportFragmentManager.findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)
    }

    override fun onMapReady(googleMap: GoogleMap) {

        map = googleMap
        map.addMarker(MarkerOptions().position(papantla).title("Papantla - Verazcruz"))
        map.moveCamera(CameraUpdateFactory.newLatLngZoom(papantla, 10f))

        map.uiSettings.apply {
            isMapToolbarEnabled = false
            //isZoomGesturesEnabled = true
            //isZoomControlsEnabled = true
        }


    }
}