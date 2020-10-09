package com.app.desafiosicredi.core.utils.bindingadapters
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.app.desafiosicredi.core.utils.formatDate
import com.app.desafiosicredi.core.utils.getWeekDay
import com.app.desafiosicredi.core.utils.helpers.loadImageView
import com.app.desafiosicredi.core.utils.setCurrency
import java.util.*

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
    date?.let { this.text = formatDate(Date(it * 1000)) }
}

@BindingAdapter("bind:weekday")
fun TextView.setWeekDay(date: Long?) {
    date?.let { this.text = getWeekDay(Date(it * 1000)) }
}

