package com.f8fit.gmaps_udemy.topics

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.f8fit.gmaps_udemy.R
import com.f8fit.gmaps_udemy.databinding.ActivityFirstMapBinding
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions

class FirstMapActivity : AppCompatActivity(), OnMapReadyCallback {

    private lateinit var binding: ActivityFirstMapBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_first_map)
        val mapFragment = supportFragmentManager.findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)
    }

    override fun onMapReady(googleMap: GoogleMap) {

        val papantla = LatLng(20.457273378951175, -97.31359613211191)
        googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(papantla, 15f))
        googleMap.addMarker(MarkerOptions().position(papantla).title("Papantla"))

        //googleMap.isTrafficEnabled = true
    }
}