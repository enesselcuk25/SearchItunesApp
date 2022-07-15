package com.enesselcuk.hepsiburadachallenge.ui.search

import com.enesselcuk.hepsiburadachallenge.data.remote.model.Result


data class ItunesUiState(
    val isLoading: Boolean? = null,
    val isError: String? = null,
    val result: List<Result> = emptyList()
)
