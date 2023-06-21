package com.f8fit.gmaps_udemy.topics

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.f8fit.gmaps_udemy.R
import com.f8fit.gmaps_udemy.common.Locations.papantla
import com.f8fit.gmaps_udemy.common.Utils
import com.f8fit.gmaps_udemy.databinding.ActivityFirstMapBinding
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment

class GroundOverlayActivity : AppCompatActivity(), OnMapReadyCallback {

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
        map.uiSettings.isZoomControlsEnabled = true
        map.moveCamera(CameraUpdateFactory.newLatLngZoom(papantla, 10f))

        insertGroundOverlay()
    }

    private fun insertGroundOverlay() {
        /*Utils.getBitmapFromVector(this, R.drawable.ic_terrain)?.let{
            map.addGroundOverlay(GroundOverlayOptions()
                .position(papantla, 1_000f, 1_000f)
                .image(BitmapDescriptorFactory.fromBitmap(it)
            ))
        }*/
        /*map.addGroundOverlay(GroundOverlayOptions()
            .position(papantla, 3_000f, 3000f)
            .image(BitmapDescriptorFactory.fromResource(R.drawable.img_cursos_android_ant))
            .transparency(0.65f)
            .anchor(0.0f, 0.5f)
        )*/
        
    }
}