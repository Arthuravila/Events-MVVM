package com.app.desafiosicredi.ui.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.app.desafiosicredi.R
import com.app.desafiosicredi.core.base.BaseFragment
import com.app.desafiosicredi.core.utils.EventObserver
import com.app.desafiosicredi.core.utils.extensions.isNetworkAvailable
import com.app.desafiosicredi.databinding.FragmentMainBinding
import com.app.desafiosicredi.ui.main.adapter.EventsAdapter

import org.koin.androidx.viewmodel.ext.android.viewModel

class MainFragment : BaseFragment<FragmentMainBinding>(R.layout.fragment_main) {
    private val viewModel: MainViewModel by viewModel()

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
            it.let { products ->
                with(binding.eventsRecyclerview) {
                    adapter = EventsAdapter(products)
                }
            }
        })
    }
}
