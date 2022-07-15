package com.enesselcuk.hepsiburadachallenge.data.remote.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class ItunesResponse(
    val resultCount: Int? = null,
    val results: List<Result>? = null
):Parcelable