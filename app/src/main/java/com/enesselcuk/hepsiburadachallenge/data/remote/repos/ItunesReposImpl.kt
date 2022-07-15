package com.enesselcuk.hepsiburadachallenge.data.remote.repos

import com.enesselcuk.hepsiburadachallenge.data.domain.ItunesRepos
import com.enesselcuk.hepsiburadachallenge.data.remote.model.ItunesResponse
import com.enesselcuk.hepsiburadachallenge.data.remote.service.NetworkService
import com.enesselcuk.hepsiburadachallenge.util.NetworkResult
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class ItunesReposImpl @Inject constructor(
    private val api: NetworkService,
    private val ioDispatcher: CoroutineDispatcher = Dispatchers.IO
) : ItunesRepos {
    override suspend fun searchList(
        name: String?,
        limit: String?,
        entity: String?
    ): Flow<NetworkResult<ItunesResponse>> = flow {
        emit(NetworkResult.Loading())
        try {
            val data = api.searchName(name, limit, entity)
            emit(NetworkResult.Success(data))
        } catch (ex: Throwable) {
            emit(NetworkResult.Error(ex.localizedMessage))
        }
    }.flowOn(ioDispatcher)
}