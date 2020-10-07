package com.app.desafiosicredi.ui.main.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.app.desafiosicredi.R
import com.app.desafiosicredi.data.events.model.Events
import com.app.desafiosicredi.databinding.ItemListEventsBinding

class EventsAdapter(
    private val events: Events
) : RecyclerView.Adapter<EventsAdapter.ProductViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        ProductViewHolder(
            DataBindingUtil.inflate(
                LayoutInflater.from(parent.context),
                R.layout.item_list_events,
                parent,
                false
            )
        )

    override fun getItemCount(): Int = events.size

    override fun onBindViewHolder(viewHolder: ProductViewHolder, position: Int) {
        viewHolder.recyclerViewItemBinding.item = events[position]
    }

    inner class ProductViewHolder(
        val recyclerViewItemBinding: ItemListEventsBinding
    ) : RecyclerView.ViewHolder(recyclerViewItemBinding.root)

}