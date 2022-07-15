package com.enesselcuk.hepsiburadachallenge.ui.search.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.ListAdapter
import com.enesselcuk.hepsiburadachallenge.R
import com.enesselcuk.hepsiburadachallenge.data.remote.model.Result

class ItunesAdapter(private val click: MusicClick) :
    ListAdapter<Result, ItunesVHolder>(DiffUtil) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItunesVHolder =
        ItunesVHolder(
            DataBindingUtil.inflate(
                LayoutInflater.from(parent.context),
                R.layout.item_music, parent, false
            )
        )

    override fun onBindViewHolder(holder: ItunesVHolder, position: Int) {
        val gamePosition = getItem(position)
        holder.bind(gamePosition, click)
    }

    class MusicClick(val click: (Result) -> Unit) {
        fun onClick(music: Result) = click(music)
    }
}