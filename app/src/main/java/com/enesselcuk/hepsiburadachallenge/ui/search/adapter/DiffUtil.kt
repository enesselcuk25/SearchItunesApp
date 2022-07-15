package com.enesselcuk.hepsiburadachallenge.ui.search.adapter

import androidx.recyclerview.widget.DiffUtil
import com.enesselcuk.hepsiburadachallenge.data.remote.model.Result

object DiffUtil : DiffUtil.ItemCallback<Result>() {
    override fun areItemsTheSame(oldItem: Result, newItem: Result): Boolean =
        oldItem.trackName == newItem.trackName
    override fun areContentsTheSame(oldItem: Result, newItem: Result): Boolean =
        oldItem == newItem
}