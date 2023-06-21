package com.f8fit.gmaps_udemy.topics

import android.annotation.SuppressLint
import android.content.pm.PackageManager
import android.location.Location
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.f8fit.gmaps_udemy.R
import com.f8fit.gmaps_udemy.common.PermissionUtils
import com.f8fit.gmaps_udemy.common.Locations.cuspide
import com.f8fit.gmaps_udemy.databinding.ActivityFirstMapBinding
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.MarkerOptions

class MyLocationActivity : AppCompatActivity(), OnMapReadyCallback,
    ActivityCompat.OnRequestPermissionsResultCallback,
    GoogleMap.OnMyLocationButtonClickListener,
    GoogleMap.OnMyLocationClickListener{

    private lateinit var binding: ActivityFirstMapBinding
    private lateinit var map: GoogleMap
    private var permissionDenied = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFirstMapBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val mapFragment = supportFragmentManager.findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)
    }

    override fun onMapReady(googleMap: GoogleMap) {
        map = googleMap

        map.moveCamera(CameraUpdateFactory.newLatLng(cuspide))
        map.addMarker(MarkerOptions().position(cuspide).title("Cuspide - Real de Monte"))

        map.setOnMyLocationClickListener(this)
        map.setOnMyLocationButtonClickListener(this)

        enableMyLocation()

    }

    @SuppressLint("MissingPermission")
    private fun enableMyLocation() {
        if (ContextCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_FINE_LOCATION)
        == PackageManager.PERMISSION_GRANTED
            || ContextCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_COARSE_LOCATION)
            == PackageManager.PERMISSION_GRANTED){
            map.isMyLocationEnabled = true
            return
        }

        if ( ActivityCompat.shouldShowRequestPermissionRationale(this, android.Manifest.permission.ACCESS_FINE_LOCATION)
             || ActivityCompat.shouldShowRequestPermissionRationale(this, android.Manifest.permission.ACCESS_COARSE_LOCATION)){
             PermissionUtils.RationaleDialog
                 .newInstance(LOCATION_PERMISSION_REQUEST_CODE, true)
                 .show(supportFragmentManager, "dialog")
            return
        }

        ActivityCompat.requestPermissions(this,
            arrayOf(android.Manifest.permission.ACCESS_FINE_LOCATION,
                android.Manifest.permission.ACCESS_COARSE_LOCATION), LOCATION_PERMISSION_REQUEST_CODE
        )
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<String>,
        grantResults: IntArray
    ) {
        if( requestCode != LOCATION_PERMISSION_REQUEST_CODE) {
            super.onRequestPermissionsResult(requestCode, permissions, grantResults)
            return
        }
        if (PermissionUtils.isPermissionGranted(permissions, grantResults, android.Manifest.permission.ACCESS_FINE_LOCATION)
            || PermissionUtils.isPermissionGranted(permissions, grantResults, android.Manifest.permission.ACCESS_COARSE_LOCATION)){
            enableMyLocation()
        } else {
            permissionDenied = true
        }
    }

    override fun onResumeFragments() {
        super.onResumeFragments()
        if (permissionDenied){
            showMissingPermissionError()
            permissionDenied = false
        }
    }

    private fun showMissingPermissionError() {
        PermissionUtils.PermissionDeniedDialog.newInstance(true)
            .show(supportFragmentManager, "dialog")
    }

    override fun onMyLocationButtonClick(): Boolean {
        Toast.makeText(this, "Mi ubicación", Toast.LENGTH_SHORT).show()
        return false
    }

    override fun onMyLocationClick(location: Location) {
        Toast.makeText(this, "Mi ubicación: $location", Toast.LENGTH_SHORT).show()
    }

    companion object {
        private const val LOCATION_PERMISSION_REQUEST_CODE = 21
    }
}