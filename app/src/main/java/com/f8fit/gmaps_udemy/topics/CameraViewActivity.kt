package com.f8fit.gmaps_udemy.topics

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.f8fit.gmaps_udemy.R
import com.f8fit.gmaps_udemy.common.Locations.papantla
import com.f8fit.gmaps_udemy.common.Utils
import com.f8fit.gmaps_udemy.databinding.ActivityFirstMapBinding
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.MarkerOptions

class CameraViewActivity : AppCompatActivity(), OnMapReadyCallback {

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
        map.addMarker(MarkerOptions().position(papantla).title("Papantla"))
        //map.moveCamera(CameraUpdateFactory.newLatLng(papantla))
        //map.moveCamera(CameraUpdateFactory.newLatLngZoom(papantla, 10f))

        /*val clusterCamera = CameraPosition.Builder()
            .target(clusterV)
            .bearing(245f)
            .tilt(40f)
            .zoom(16f)
            .build()
        map.moveCamera(CameraUpdateFactory.newCameraPosition(clusterCamera))

        map.apply {
            setMinZoomPreference(16f)
            setMaxZoomPreference(20f)
        }*/
        /*map.moveCamera(CameraUpdateFactory.newLatLngZoom(cic, 10f))
        lifecycleScope.launch {
            delay(5_000)
            map.addMarker(MarkerOptions().position(papantla))
            map.animateCamera(CameraUpdateFactory.newLatLngZoom(papantla,13f))
        }*/
        /*lifecycleScope.launch {
            delay(2_500)
            //map.moveCamera(CameraUpdateFactory.zoomBy(2f))
            map.moveCamera(CameraUpdateFactory.zoomIn())
            delay(2_500)
            map.moveCamera(CameraUpdateFactory.zoomOut())
            delay(3_500)
            map.animateCamera(CameraUpdateFactory.zoomTo(16f))
        }*/
        /*val clusterCamera = CameraPosition.Builder()
            .target(clusterV)
            .zoom(16f)
            .build()
        map.animateCamera(CameraUpdateFactory.newCameraPosition(clusterCamera))

        lifecycleScope.launch {
            delay(2_500)
            for(i in 1..15){
                map.animateCamera(CameraUpdateFactory.scrollBy(0f,-150f))
                delay(500)
            }
        }*/

        /*val papantlaDowntownBounds = LatLngBounds(
            LatLng(20.45759504905523, -97.31419694694708),
            LatLng(20.468812872588376, -97.27505815250946)
        )*/
        /*map.moveCamera(CameraUpdateFactory.newLatLngZoom(papantla,10f))
        lifecycleScope.launch {
            delay(2_0000)
            map.animateCamera(CameraUpdateFactory.newLatLngBounds(papantlaDowntownBounds,dp(32)))
        }*/
        /*map.animateCamera(CameraUpdateFactory.newLatLngZoom(papantlaDowntownBounds.center,15f))
        map.setLatLngBoundsForCameraTarget(papantlaDowntownBounds)*/ //Limitar Area
    }
}