package com.app.desafiosicredi.ui.eventdetail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.app.desafiosicredi.R
import com.app.desafiosicredi.core.base.BaseFragment
import com.app.desafiosicredi.databinding.FragmentMapsBinding
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MapStyleOptions
import com.google.android.gms.maps.model.MarkerOptions

class MapsFragment : BaseFragment<FragmentMapsBinding>(R.layout.fragment_maps) {
    private val callback = OnMapReadyCallback { googleMap ->
        val mapArgs = arguments?.getParcelable<LatLng>("mapArgs")
        googleMap.addMarker(mapArgs?.let { MarkerOptions().position(it).title("Marker in") })
        googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(mapArgs, 13f))
        googleMap.setMapStyle(MapStyleOptions.loadRawResourceStyle(context, R.raw.map_style))
    }

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
        val mapFragment = childFragmentManager.findFragmentById(R.id.mapsFragmentContainer) as SupportMapFragment?
        mapFragment?.getMapAsync(callback)
    }
}
