package com.enesselcuk.hepsiburadachallenge.util

import android.view.View
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import java.text.DateFormat
import java.text.SimpleDateFormat
import java.util.*

@BindingAdapter("app:progressbar")
fun ProgressBar.setProgress(visible: Boolean) {
    this.progress = if (visible) View.VISIBLE else View.GONE
}

@BindingAdapter("app:musicImage")
fun ImageView.setStoreUrl(imageIcon: String?) {
    Glide.with(context)
        .load(imageIcon)
        .fitCenter()
        .into(this)
}

@BindingAdapter("app:setReleaseDate")
fun setReleaseDate(view: TextView, date: String) {
    val dateParse = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'", Locale.getDefault())
    val dateFormat = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault())
    val text = dateFormat.format(dateParse.parse(date))
    view.text = text
}