package com.app.desafiosicredi.ui.events

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import com.app.desafiosicredi.R
import com.app.desafiosicredi.core.base.BaseFragment
import com.app.desafiosicredi.core.utils.EventObserver
import com.app.desafiosicredi.core.utils.extensions.isNetworkAvailable
import com.app.desafiosicredi.data.events.model.Events
import com.app.desafiosicredi.databinding.FragmentEventsBinding
import com.app.desafiosicredi.ui.events.adapter.EventsAdapter

import org.koin.androidx.viewmodel.ext.android.viewModel

class EventsFragment : BaseFragment<FragmentEventsBinding>(R.layout.fragment_events) {
    private val viewModel: EventsViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        binding.viewModel = viewModel
        return binding.root
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (requireContext().isNetworkAvailable()) {
            viewModel.getEvents()
        } else {
            Toast.makeText(requireContext(), "SEM CONEXÃO", Toast.LENGTH_LONG).show()
        }
    }

    override fun subscribeUi() {
        viewModel.events.observe(this, EventObserver {
            it.let { events ->
                with(binding.eventsRecyclerview) {
                    adapter = setAdapter(events)
                }
            }
        })
    }

    private fun setAdapter(events: Events) = EventsAdapter(events) { item ->
        item.id.let { id ->
            findNavController()
                .navigate(
                    EventsFragmentDirections.actionEventsFragmentToEventDetailFragment(
                        id
                    )
                )
        }
    }
}
