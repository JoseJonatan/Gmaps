package com.f8fit.gmaps_udemy.artistList

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.cursosandroidant.mapsbasics.common.dataAccess.FakeDatabase
import com.f8fit.gmaps_udemy.R
import com.f8fit.gmaps_udemy.databinding.ActivityLiteListBinding

class LiteListActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLiteListBinding
    private lateinit var adapter: ArtistAdapter

    private val recyclerListener = RecyclerView.RecyclerListener { holder ->
        (holder as ArtistAdapter.ViewHolder).clearMap()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLiteListBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupRecyclerView()
        getArtists()
    }

    private fun setupRecyclerView() {
        adapter = ArtistAdapter()

        binding.recyclerView.apply {
            //layoutManager = LinearLayoutManager(this@LiteListActivity)
            layoutManager = GridLayoutManager(this@LiteListActivity, 2)
            adapter = this@LiteListActivity.adapter
            setHasFixedSize(true)
            setRecyclerListener(recyclerListener)
        }
    }

    private fun getArtists() {
        FakeDatabase.getArtist(this)?.let { adapter.submitList(it)}
    }
}