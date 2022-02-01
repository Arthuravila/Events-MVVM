package com.app.desafiosicredi.ui.eventdetail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.lifecycle.Observer
import androidx.navigation.fragment.navArgs
import com.app.desafiosicredi.R
import com.app.desafiosicredi.core.base.BaseFragment
import com.app.desafiosicredi.core.utils.CustomCheckinDialog
import com.app.desafiosicredi.core.utils.openShareDialog
import com.app.desafiosicredi.databinding.FragmentEventDetailBinding
import com.app.desafiosicredi.ui.main.MainActivity
import com.google.android.gms.maps.model.LatLng
import org.koin.androidx.viewmodel.ext.android.viewModel

class EventDetailFragment :
    BaseFragment<FragmentEventDetailBinding>(R.layout.fragment_event_detail) {
    private val viewModel: EventDetailViewModel by viewModel()
    private val args: EventDetailFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        super.onCreateView(inflater, container, savedInstanceState)
        binding.viewModel = viewModel
        viewModel.getEventDetail(args.eventId)
        setListeners()
        return binding.root
    }

    override fun subscribeUi() {

        viewModel.eventDetail.observe({ lifecycle }) {
            setMap(it.latitude, it.longitude)
        }

        viewModel.checkinResponse.observe(this, Observer {
            if (it.code == "200") {
                (activity as MainActivity).showSnack(
                    ContextCompat.getColor(requireContext(), R.color.greenDark),
                    getString(R.string.success_presence_confirm)
                )
            }
        })
    }

    private fun setMap(latitude: Double?, longitude: Double?) {
        val bundle = Bundle()
        val coordinates = latitude?.let { lat -> longitude?.let { long -> LatLng(lat, long) } }
        bundle.putParcelable("mapArgs", coordinates)
        val transaction = childFragmentManager.beginTransaction()
        transaction.replace(
            R.id.frame_map,
            MapsFragment::class.java,
            bundle,
            "mapArgs"
        )
        transaction.commit()
    }

    private fun setListeners() {
        binding.shareButton.setOnClickListener {
            showShareDialog()
        }

        binding.checkinButton.setOnClickListener {
            CustomCheckinDialog(requireContext()).showDialog { name, email ->
                viewModel.makeCheckin(name, email, args.eventId)
            }
        }
    }

    private fun showShareDialog() {
        val shareContentText =
            getString(R.string.look_at_this) + " " + viewModel.eventDetail.value?.title
        openShareDialog(
            requireContext(),
            shareContentText
        )
    }
}
