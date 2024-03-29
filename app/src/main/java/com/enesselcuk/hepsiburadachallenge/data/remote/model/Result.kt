package com.enesselcuk.hepsiburadachallenge.data.remote.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Result(
    val artistName: String? = null,
    val artworkUrl100: String? = null,
    val artworkUrl30: String? = null,
    val artworkUrl60: String? = null,
    val collectionArtistId: Int? = null,
    val collectionArtistViewUrl: String? = null,
    val collectionCensoredName: String? = null,
    val collectionExplicitness: String? = null,
    val collectionHdPrice: Double? = null,
    val collectionId: Int? = null,
    val collectionName: String? = null,
    val collectionPrice: Double? = null,
    val collectionViewUrl: String? = null,
    val contentAdvisoryRating: String? = null,
    val country: String? = null,
    val currency: String? = null,
    val discCount: Int? = null,
    val discNumber: Int? = null,
    val hasITunesExtras: Boolean? = null,
    val kind: String? = null,
    val longDescription: String? = null,
    val previewUrl: String? = null,
    val primaryGenreName: String? = null,
    val releaseDate: String? = null,
    val shortDescription: String? = null,
    val trackCensoredName: String? = null,
    val trackCount: Int? = null,
    val trackExplicitness: String? = null,
    val trackHdPrice: Double? = null,
    val trackHdRentalPrice: Double? = null,
    val trackId: Int? = null,
    val trackName: String? = null,
    val trackNumber: Int? = null,
    val trackPrice: Double? = null,
    val trackRentalPrice: Double? = null,
    val trackTimeMillis: Int? = null,
    val trackViewUrl: String? = null,
    val wrapperType: String? = null
):Parcelable