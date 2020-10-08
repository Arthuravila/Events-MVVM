package com.app.desafiosicredi.ui.eventdetail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.navArgs
import com.app.desafiosicredi.R
import com.app.desafiosicredi.core.base.BaseFragment
import com.app.desafiosicredi.core.utils.EventObserver
import com.app.desafiosicredi.databinding.FragmentEventDetailBinding
import com.app.desafiosicredi.ui.main.MainActivity
import com.google.android.gms.maps.model.LatLng
import org.koin.androidx.viewmodel.ext.android.viewModel

class EventDetailFragment : BaseFragment<FragmentEventDetailBinding>(R.layout.fragment_event_detail) {
    private val viewModel: EventDetailViewModel by viewModel()
    private val args: EventDetailFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        binding.viewModel = viewModel
        (activity as MainActivity).setToolbarIcon()
        viewModel.getEventDetail(args.eventId)


        return binding.root
    }

    override fun subscribeUi() {
        viewModel.eventDetail.observe(this, EventObserver {
            setMap(it.latitude, it.longitude)
        })
    }

    private fun setMap(latitude: Double?, longitude: Double?) {
        val bundle = Bundle()
        val coordinates = latitude?.let { lat -> longitude?.let { long -> LatLng(lat, long) } }
        bundle.putParcelable("mapArgs", coordinates)
        val transaction = childFragmentManager.beginTransaction()
        transaction.replace(
            R.id.flMap,
            MapsFragment::class.java,
            bundle,
            "mapArgs"
        )
    }
}