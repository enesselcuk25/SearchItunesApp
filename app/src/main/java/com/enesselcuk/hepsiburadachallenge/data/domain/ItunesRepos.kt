package com.enesselcuk.hepsiburadachallenge.data.domain

import com.enesselcuk.hepsiburadachallenge.data.remote.model.ItunesResponse
import com.enesselcuk.hepsiburadachallenge.util.NetworkResult
import kotlinx.coroutines.flow.Flow

interface ItunesRepos {
    suspend fun searchList(
        name: String?= null,
        limit: String?= null,
        entity: String? = null
    ): Flow<NetworkResult<ItunesResponse>>
}