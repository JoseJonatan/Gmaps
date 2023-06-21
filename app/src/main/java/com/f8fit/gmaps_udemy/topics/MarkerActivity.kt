package com.f8fit.gmaps_udemy.topics

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.f8fit.gmaps_udemy.topics.infoWindow.HillAdapter
import com.f8fit.gmaps_udemy.R
import com.f8fit.gmaps_udemy.common.Locations.papantla
import com.f8fit.gmaps_udemy.common.Utils
import com.f8fit.gmaps_udemy.databinding.ActivityFirstMapBinding
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.BitmapDescriptorFactory
import com.google.android.gms.maps.model.Marker
import com.google.android.gms.maps.model.MarkerOptions

class MarkerActivity : AppCompatActivity(), OnMapReadyCallback,
GoogleMap.OnMarkerDragListener,
GoogleMap.OnMarkerClickListener{

    private lateinit var binding: ActivityFirstMapBinding
    private lateinit var map: GoogleMap

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFirstMapBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.toggleGroup.visibility = View.VISIBLE

        val mapFragment = supportFragmentManager.findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)
    }

    override fun onMapReady(googleMap: GoogleMap) {
        map = googleMap
        map.moveCamera(CameraUpdateFactory.newLatLngZoom(papantla, 12f))

        val Veracruz = map.addMarker(MarkerOptions().position(papantla).title("Papantla Veracruz"))

        Veracruz?.run {
            //setIcon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_BLUE))
            //setIcon(BitmapDescriptorFactory.defaultMarker(100f))
            Utils.getBitmapFromVector(this@MarkerActivity, R.drawable.ic_terrain)?.let {
                setIcon(BitmapDescriptorFactory.fromBitmap(it))
            }
            rotation = 320f
            setAnchor(0.5f,0.5f)
            isFlat = false
            snippet = "Central"
        }
        map.setOnMarkerClickListener(this)
        Veracruz?.isDraggable = true
        map.setOnMarkerDragListener(this)

        //Custom InfoWindow
        map.setInfoWindowAdapter(HillAdapter(this))
    }

    override fun onMarkerClick(marker: Marker): Boolean {
        //marker.showInfoWindow()
        return false
    }

    override fun onMarkerDrag(marker: Marker) {
        marker.alpha = 0.4f

    }

    override fun onMarkerDragEnd(marker: Marker) {
        binding.toggleGroup.visibility = View.VISIBLE
        marker.title = "New Location"
        marker.snippet = "Welcome"
        marker.alpha = 1.0f
    }

    override fun onMarkerDragStart(marker: Marker) {
        binding.toggleGroup.visibility = View.INVISIBLE
    }

}