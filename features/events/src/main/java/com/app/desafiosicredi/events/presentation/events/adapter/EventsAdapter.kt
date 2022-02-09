package com.app.desafiosicredi.events.presentation.events.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.lifecycle.MutableLiveData
import androidx.recyclerview.widget.RecyclerView
import com.app.desafiosicredi.common.domain.model.events.EventsItem
import com.app.desafiosicredi.events.databinding.ItemListEventsBinding

class EventsAdapter(
    private val eventId: MutableLiveData<String>
) : RecyclerView.Adapter<EventsAdapter.EventsViewHolder>() {

    private var events = arrayListOf<EventsItem>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EventsViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemListEventsBinding.inflate(inflater, parent, false)
        return EventsViewHolder(binding)
    }

    override fun getItemCount(): Int = events.size

    override fun onBindViewHolder(holder: EventsViewHolder, position: Int) = holder.bind(events[position])

    inner class EventsViewHolder(private val binding: ItemListEventsBinding): RecyclerView.ViewHolder(binding.root) {

        fun bind(event: EventsItem) = with(binding) {
            item = event
            root.setOnClickListener { eventId.postValue(item?.id) }
            executePendingBindings()
        }
    }

    fun addItems(data: List<EventsItem>) {
        events.addAll(data)
        notifyDataSetChanged()
    }

}
