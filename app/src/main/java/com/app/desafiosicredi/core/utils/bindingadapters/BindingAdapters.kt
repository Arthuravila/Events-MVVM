package com.app.desafiosicredi.core.utils.bindingadapters


import android.view.View
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.app.desafiosicredi.core.utils.loadImageView

@BindingAdapter("url")
fun setImage(view: ImageView, url: String?) {

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
fun ImageView.setImageView(imageUrl: String) {
        loadImageView(this, imageUrl)
}