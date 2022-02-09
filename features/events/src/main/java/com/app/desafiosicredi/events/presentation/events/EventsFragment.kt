package com.app.desafiosicredi.events.presentation.events

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.app.desafiosicredi.common.base.BaseFragment
import com.app.desafiosicredi.common.utils.extensions.isNetworkAvailable
import com.app.desafiosicredi.events.R
import com.app.desafiosicredi.events.databinding.FragmentEventsBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

class EventsFragment : BaseFragment<FragmentEventsBinding>(R.layout.fragment_events) {
    private val viewModel: EventsViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        super.onCreateView(inflater, container, savedInstanceState)
        binding.viewModel = viewModel
        getEventsData()
        setRetryListener()
        return binding.root
    }

    override fun subscribeUi() {
        viewModel.eventId.observe({ lifecycle }) { id ->
            id?.let {
                openEventDetail(it)
            }
        }

        viewModel.events.observe({ lifecycle }) {
            viewModel.loadEvents(it)
        }

        viewModel.errorState.observe({ lifecycle }) {
            if (it.errorVisibility) {
                val errorMsg = it.errorMessage ?: getString(R.string.generic_error)
/*                (activity as MainActivity).showSnack(
                    ContextCompat.getColor(requireContext(), R.color.redDark),
                    errorMsg
                )*/
            }
        }
    }

    private fun openEventDetail(eventId: String) {
/*        findNavController()
            .navigate(
                EventsFragmentDirections.actionEventsFragmentToEventDetailFragment(
                    eventId
                )
            )*/
    }

    private fun getEventsData() {
        if (requireContext().isNetworkAvailable()) {
            setRetryButtonVisibility(false)
            viewModel.getEvents()
        } else {
/*            (activity as MainActivity).showSnack(
                Color.GRAY,
                getString(R.string.no_internet)
            )*/
            setRetryButtonVisibility(true)
            viewModel.setProgressBarVisibility(false)
        }
    }

    private fun setRetryButtonVisibility(visibility: Boolean) {
        // binding.retryButton.isVisible = visibility
    }

    private fun setRetryListener() {
        binding.retryButton.setOnClickListener {
            getEventsData()
        }
    }
}
