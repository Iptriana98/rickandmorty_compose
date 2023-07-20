package com.iptrianaa.therickandmortywiki.config.network.di

import com.iptrianaa.therickandmortywiki.config.network.api.NetworkApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object ServiceModule {

    @Provides
    fun provideNetworkApi(retrofit: Retrofit): NetworkApi =
        retrofit.create(NetworkApi::class.java)
}