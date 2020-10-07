package com.app.desafiosicredi.core.utils

import android.widget.ImageView
import androidx.core.content.ContextCompat
import androidx.swiperefreshlayout.widget.CircularProgressDrawable
import com.app.desafiosicredi.R
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.request.RequestOptions

fun loadImageView(image: ImageView, imageUrl: String?) {
    Glide.with(image.context)
        .load(imageUrl)
        .apply(
            RequestOptions()
                .transform(CenterCrop())
        )
        .error(R.drawable.ic_broken_image)
        .placeholder(getProgressDrawable(image))
        .into(image)
        .clearOnDetach()
}

fun getProgressDrawable(image: ImageView): CircularProgressDrawable {
    val progressDrawable = CircularProgressDrawable(image.context)
    progressDrawable.strokeWidth = 8f
    progressDrawable.centerRadius = 50f
    progressDrawable.setColorSchemeColors(
        ContextCompat.getColor(
            image.context,
            R.color.colorPrimaryDark
        )
    )
    progressDrawable.start()

    return progressDrawable
}
