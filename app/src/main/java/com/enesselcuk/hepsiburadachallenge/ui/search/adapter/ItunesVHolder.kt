package com.enesselcuk.hepsiburadachallenge.ui.search.adapter

import androidx.recyclerview.widget.RecyclerView
import com.enesselcuk.hepsiburadachallenge.data.remote.model.Result
import com.enesselcuk.hepsiburadachallenge.databinding.ItemMusicBinding

class ItunesVHolder(private val binding: ItemMusicBinding) :
    RecyclerView.ViewHolder(binding.root) {
    fun bind(
        music: Result,
        click: ItunesAdapter.MusicClick
    ) {
        binding.musicData = music
        binding.mClick = click
    }
}