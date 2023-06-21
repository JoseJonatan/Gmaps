package com.f8fit.gmaps_udemy.topics

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.lifecycle.lifecycleScope
import com.f8fit.gmaps_udemy.R
import com.f8fit.gmaps_udemy.common.Locations.Veracruz
import com.f8fit.gmaps_udemy.common.Locations.cic
import com.f8fit.gmaps_udemy.common.Locations.clusterV
import com.f8fit.gmaps_udemy.common.Locations.cuspide
import com.f8fit.gmaps_udemy.common.Locations.diosa
import com.f8fit.gmaps_udemy.common.Locations.papantla
import com.f8fit.gmaps_udemy.common.Locations.xalapa
import com.f8fit.gmaps_udemy.common.Utils
import com.f8fit.gmaps_udemy.databinding.ActivityFirstMapBinding
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.*
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class ShapesActivity : AppCompatActivity(), OnMapReadyCallback {

    private lateinit var binding: ActivityFirstMapBinding
    private lateinit var map: GoogleMap

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFirstMapBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val mapFragment = supportFragmentManager.findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)
    }

    override fun onMapReady(googleMap: GoogleMap) {
        map = googleMap

        map.uiSettings.isZoomControlsEnabled = true
        //map.moveCamera(CameraUpdateFactory.newLatLngZoom(papantla, 20f))

        //runPolyline()
        //runPolygon()
        runCircle()
    }

    private fun runPolyline() {
        val route = mutableListOf(papantla, cuspide, cic, clusterV)
        map.addMarker(MarkerOptions().position(papantla).title("Papantla Veracruz"))

        val polyline = map.addPolyline(PolylineOptions()
            .width(8f)
            .color(Color.BLUE)
            .geodesic(true)
            .clickable(true)
            .jointType(JointType.BEVEL)
        )
        //polyline.points = route

        lifecycleScope.launch {
            route.add(xalapa)
            route.add(Veracruz)
            val runtimeRoute = mutableListOf<LatLng>()
            for (point in route){
                runtimeRoute.add(point)
                polyline.points = runtimeRoute
                delay(1_500)
            }
        }

        polyline.tag = "Estado de Veracruz"
        map.setOnPolylineClickListener {
            Toast.makeText(this, "${it.tag}", Toast.LENGTH_LONG).show()
        }

        polyline.pattern = listOf(Dot(), Gap(16f), Dash(32f), Gap(16f))
        polyline.width = 14f
        polyline.jointType = JointType.ROUND

        //polyline.startCap = RoundCap()
        //polyline.startCap = ButtCap()
        //polyline.endCap = SquareCap()

        Utils.getBitmapFromVector(this, R.drawable.ic_directions_run)?.let {
            polyline.startCap = CustomCap(BitmapDescriptorFactory.fromBitmap(it),12f)
        }
        Utils.getBitmapFromVector(this, R.drawable.ic_star)?.let {
            polyline.endCap = CustomCap(BitmapDescriptorFactory.fromBitmap(it),24f)
        }
    }

    private fun runPolygon() {

        map.moveCamera(CameraUpdateFactory.newLatLngZoom(papantla,14f))

        val polygon = map.addPolygon(
            PolygonOptions()
                .add(papantla, clusterV, diosa)
                .fillColor(ContextCompat.getColor(this, R.color.fill_polygon))
                .strokeColor(ContextCompat.getColor(this, R.color.stroke_polygon))
                .strokeWidth(4f)
                .geodesic(true)
                .clickable(true)
        )
        polygon.tag = "Gaya"
        map.setOnPolygonClickListener {
            Toast.makeText(this,"IPN - ${it.tag}", Toast.LENGTH_SHORT).show()
        }
    }

    private fun runCircle() {

        map.moveCamera(CameraUpdateFactory.newLatLngZoom(papantla,14f))

        val circle = map.addCircle(CircleOptions()
            .center(papantla)
            .radius(2_000.0)
            .fillColor(ContextCompat.getColor(this, R.color.fill_polygon))
            .strokeColor(ContextCompat.getColor(this, R.color.stroke_polygon))
            .clickable(true)
        )
        circle.tag = "Gaya"
        map.setOnCircleClickListener {
            Toast.makeText(this,"IPN - ${it.tag}", Toast.LENGTH_SHORT).show()
        }

        lifecycleScope.launch {
            delay(3_000)
            circle.isVisible = false
            delay(1_000)
            circle.isVisible = true
            delay(2_000)
            //circle.remove()
        }
    }
}