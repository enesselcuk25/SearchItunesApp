package com.enesselcuk.hepsiburadachallenge.di


import com.enesselcuk.hepsiburadachallenge.BuildConfig.BASE_URL
import com.enesselcuk.hepsiburadachallenge.data.remote.service.NetworkService
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Singleton
    @Provides
    fun gson(): Gson = GsonBuilder()
        .setLenient()
        .create()

    @Singleton
    @Provides
    internal fun providesFoodApi(): Retrofit {
        return Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create(gson()))
            .baseUrl(BASE_URL)
            .build()

    }

    @Singleton
    @Provides
    fun provideGetQuotes(retrofit: Retrofit): NetworkService {
        return retrofit.create(NetworkService::class.java)
    }
}