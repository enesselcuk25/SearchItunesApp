package com.enesselcuk.hepsiburadachallenge.di


import com.enesselcuk.hepsiburadachallenge.data.domain.ItunesRepos
import com.enesselcuk.hepsiburadachallenge.data.remote.repos.ItunesReposImpl
import com.enesselcuk.hepsiburadachallenge.data.remote.service.NetworkService
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import kotlinx.coroutines.CoroutineDispatcher

@Module
@InstallIn(ViewModelComponent::class)
object RepositoryModule {

    @Provides
    fun providesRepository(
        api: NetworkService,
        coroutineDispatcher: CoroutineDispatcher
    ) = ItunesReposImpl(api, coroutineDispatcher)

}

@Module
@InstallIn(ViewModelComponent::class)
interface Repos {
    @Binds
    fun music(gameRepositoryImpl: ItunesReposImpl): ItunesRepos
}

