package com.iptrianaa.therickandmortywiki.config.network.di

import com.iptrianaa.therickandmortywiki.config.datasource.ApiDataSource
import com.iptrianaa.therickandmortywiki.config.network.api.NetworkApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object DataSourceModule {

    @Provides
    fun provideApiDataSource(networkApi: NetworkApi): ApiDataSource = ApiDataSource(networkApi)
}