package com.app.desafiosicredi.ui.eventdetail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.navArgs
import com.app.desafiosicredi.R
import com.app.desafiosicredi.core.base.BaseFragment
import com.app.desafiosicredi.databinding.FragmentEventDetailBinding

class EventDetailFragment : BaseFragment<FragmentEventDetailBinding>(R.layout.fragment_event_detail) {
    private val args: EventDetailFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        // binding.viewModel = viewModel
        return binding.root
    }
}