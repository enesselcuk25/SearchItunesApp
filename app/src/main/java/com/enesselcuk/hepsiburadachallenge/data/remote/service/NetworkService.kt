package com.enesselcuk.hepsiburadachallenge.data.remote.service

import com.enesselcuk.hepsiburadachallenge.data.remote.model.ItunesResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface NetworkService {
    @GET("search")
    suspend fun searchName(
        @Query("term") term: String? = null,
        @Query("limit") limit: String? = null,
        @Query("entity") media: String? = null
    ): ItunesResponse
}