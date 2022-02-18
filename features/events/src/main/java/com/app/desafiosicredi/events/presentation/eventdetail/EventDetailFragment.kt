package com.app.desafiosicredi.events.presentation.eventdetail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.navArgs
import com.app.desafiosicredi.common.base.BaseFragment
import com.app.desafiosicredi.common.utils.CustomCheckinDialog
import com.app.desafiosicredi.common.utils.openShareDialog
import com.app.desafiosicredi.events.R
import com.app.desafiosicredi.events.databinding.FragmentEventDetailBinding
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

        viewModel.checkinResponse.observe({ lifecycle }) {
/*            (activity as MainActivity).showSnack(
                ContextCompat.getColor(requireContext(), R.color.greenDark),
                getString(R.string.success_presence_confirm)
            )*/
        }

        viewModel.errorState.observe({ lifecycle }) {
                val errorMsg = it.errorMessage ?: getString(R.string.generic_error)
/*                (activity as MainActivity).showSnack(
                    ContextCompat.getColor(requireContext(), R.color.redDark),
                    errorMsg
                )*/
        }
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
/*        binding.shareButton.setOnClickListener {
            showShareDialog()
        }

        binding.checkinButton.setOnClickListener {
            CustomCheckinDialog(requireContext()).showDialog { name, email ->
                viewModel.makeCheckin(name, email, args.eventId)
            }
        }*/
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
