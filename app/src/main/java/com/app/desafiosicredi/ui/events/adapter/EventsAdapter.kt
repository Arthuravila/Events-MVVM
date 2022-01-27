package com.app.desafiosicredi.ui.events.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.app.desafiosicredi.domain.model.events.Events
import com.app.desafiosicredi.domain.model.events.EventsItem
import com.app.desafiosicredi.databinding.ItemListEventsBinding

class EventsAdapter(
    private val events: Events,
    private val itemClicked: (EventsItem) -> Unit
) : RecyclerView.Adapter<EventsAdapter.EventsViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EventsViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemListEventsBinding.inflate(inflater, parent, false)
        return EventsViewHolder(binding)
    }

    override fun getItemCount(): Int = events.size

    override fun onBindViewHolder(viewHolder: EventsViewHolder, position: Int) {
        val event = events[position]
        viewHolder.itemView.setOnClickListener { itemClicked(event) }
        viewHolder.recyclerViewItemBinding.apply {
            item = event
            executePendingBindings()
        }
    }

    inner class EventsViewHolder(
        val recyclerViewItemBinding: ItemListEventsBinding
    ) : RecyclerView.ViewHolder(recyclerViewItemBinding.root)
}
