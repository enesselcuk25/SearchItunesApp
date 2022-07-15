package com.enesselcuk.hepsiburadachallenge.ui.search

import com.enesselcuk.hepsiburadachallenge.data.remote.model.Result


data class ItunesUiState(
    val isLoading: Boolean? = null,
    val isError: Boolean? = null,
    val result: List<Result> = emptyList()
)
