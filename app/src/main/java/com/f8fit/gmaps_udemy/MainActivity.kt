package com.f8fit.gmaps_udemy

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.f8fit.gmaps_udemy.artistList.LiteListActivity
import com.f8fit.gmaps_udemy.databinding.ActivityMainBinding
import com.f8fit.gmaps_udemy.modes.LiteModeActivity
import com.f8fit.gmaps_udemy.topics.*

class MainActivity : AppCompatActivity(), View.OnClickListener {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        with(binding){
            btnFirstMap.setOnClickListener(this@MainActivity)
            btnCameraView.setOnClickListener(this@MainActivity)
            btnControlGestures.setOnClickListener(this@MainActivity)
            btnEvents.setOnClickListener(this@MainActivity)
            btnMyLocation.setOnClickListener(this@MainActivity)
            btnMarker.setOnClickListener(this@MainActivity)
            btnShapes.setOnClickListener(this@MainActivity)
            btnGroundOverlay.setOnClickListener(this@MainActivity)
            btnLiteMods.setOnClickListener(this@MainActivity)
            //Apps
            btnArtistMep.setOnClickListener(this@MainActivity)
            btnLiteList.setOnClickListener(this@MainActivity)
            btnFormMap.setOnClickListener(this@MainActivity)
        }
    }

    override fun onClick(view: View?) {
        view?.let {
            when(it.id){
                R.id.btnFirstMap -> startActivity(Intent(this, FirstMapActivity::class.java))
                R.id.btnCameraView -> startActivity(Intent(this, CameraViewActivity::class.java ))
                R.id.btnControlGestures -> startActivity(Intent(this, ControlGesturesActivity::class.java ))
                R.id.btnEvents -> startActivity(Intent(this, EventsActivity::class.java ))
                R.id.btnMyLocation -> startActivity(Intent(this, MyLocationActivity::class.java ))
                R.id.btnMarker -> startActivity(Intent(this, MarkerActivity::class.java))
                R.id.btnShapes -> startActivity(Intent(this, ShapesActivity::class.java))
                R.id.btnGroundOverlay -> startActivity(Intent(this, GroundOverlayActivity::class.java))
                R.id.btnLiteMods -> startActivity(Intent(this, LiteModeActivity::class.java))
                // Apps
                R.id.btnArtistMep -> startActivity(Intent(this, FirstMapActivity::class.java))
                R.id.btnLiteList -> startActivity(Intent(this, LiteListActivity::class.java))
                R.id.btnFormMap -> startActivity(Intent(this, FirstMapActivity::class.java))
            }
        }
    }
}