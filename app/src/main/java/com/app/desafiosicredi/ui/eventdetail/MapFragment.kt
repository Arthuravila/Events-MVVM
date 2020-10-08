package com.app.desafiosicredi.ui.eventdetail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.app.desafiosicredi.R
import com.app.desafiosicredi.core.base.BaseFragment
import com.app.desafiosicredi.databinding.FragmentMapBinding
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MapStyleOptions
import com.google.android.gms.maps.model.MarkerOptions

class MapsFragment : OnMapReadyCallback,
    BaseFragment<FragmentMapBinding>(R.layout.fragment_map) {
    private var mMap: GoogleMap? = null
    private lateinit var mapArgs: LatLng

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        super.onCreateView(inflater, container, savedInstanceState)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if (mMap == null)
            (childFragmentManager.findFragmentById(R.id.mapsFragmentContainer) as SupportMapFragment).getMapAsync(
                this
            )
    }


    override fun onMapReady(googleMap: GoogleMap?) {
        arguments?.getParcelable<LatLng>("mapArgs")?.let { mapArgs = it }
        val latitude = mapArgs.latitude
        val longitude = mapArgs.longitude

        mMap = googleMap
        mMap?.setMapStyle(MapStyleOptions.loadRawResourceStyle(context, R.raw.map_style))
        mMap?.moveCamera(CameraUpdateFactory.zoomTo(13F))

        mMap?.addMarker(
            MarkerOptions().position(
                LatLng(
                    latitude,
                    longitude
                )
            ).title("Teste Mapa")
        )
    }
}