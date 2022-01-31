package com.app.desafiosicredi.core.utils.bindingadapters

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.app.desafiosicredi.core.utils.formatDate
import com.app.desafiosicredi.core.utils.getWeekDay
import com.app.desafiosicredi.core.utils.helpers.loadImageView
import com.app.desafiosicredi.core.utils.setCurrency
import java.util.*

@BindingAdapter("bind:setAdapter")
fun RecyclerView.setEventsAdapter(rvAdapter: RecyclerView.Adapter<*>?) {
    rvAdapter?.let { adapter ->
        this.layoutManager = LinearLayoutManager(this.context)
        this.adapter = adapter
        this.adapter?.notifyDataSetChanged()
    }
}

@BindingAdapter("bind:progressBarObserver")
fun setProgressBarObserver(view: View, isVisible: Boolean) {
    if (isVisible) {
        view.visibility = View.VISIBLE
    } else {
        view.visibility = View.GONE
    }
}

@BindingAdapter("bind:imageSet")
fun ImageView.setImageView(imageUrl: String?) {
        loadImageView(this, imageUrl)
}

@BindingAdapter("bind:currencyText")
fun TextView.setTextCurrency(value: Double?) {
    this.text = setCurrency(value)
}

@BindingAdapter("bind:date")
fun TextView.setDate(date: Long?) {
    date?.let { this.text = formatDate(Date(it)) }
}

@BindingAdapter("bind:weekDay")
fun TextView.setWeekDay(date: Long?) {
    date?.let { this.text = getWeekDay(Date(it * 1000)) }
}
