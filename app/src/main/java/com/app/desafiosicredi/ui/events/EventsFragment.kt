package com.app.desafiosicredi.ui.events

import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.navigation.fragment.findNavController
import com.app.desafiosicredi.R
import com.app.desafiosicredi.core.base.BaseFragment
import com.app.desafiosicredi.core.utils.helpers.EventObserver
import com.app.desafiosicredi.core.utils.extensions.isNetworkAvailable
import com.app.desafiosicredi.domain.model.events.Events
import com.app.desafiosicredi.databinding.FragmentEventsBinding
import com.app.desafiosicredi.ui.events.adapter.EventsAdapter
import com.app.desafiosicredi.ui.main.MainActivity

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
    }

    private fun openEventDetail(eventId: String) {
        findNavController()
            .navigate(
                EventsFragmentDirections.actionEventsFragmentToEventDetailFragment(
                    eventId
                )
            )
    }

    private fun getEventsData() {
        if (requireContext().isNetworkAvailable()) {
            setRetryButtonVisibility(false)
            viewModel.getEvents()
        } else {
            (activity as MainActivity).showSnack(
                Color.GRAY,
                getString(R.string.no_internet)
            )
            setRetryButtonVisibility(true)
            viewModel.setProgressBarVisibility(false)
        }
    }

    private fun setRetryButtonVisibility(visibility: Boolean) {
        binding.retryButton.isVisible = visibility
    }

    private fun setRetryListener() {
        binding.retryButton.setOnClickListener {
            getEventsData()
        }
    }
}
