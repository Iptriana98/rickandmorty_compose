package com.iptrianaa.therickandmortywiki.config.network.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.Dispatcher
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    private const val MAX_REQUEST_COUNT = 8
    private const val TIME_OUT_CONNECT = 25L
    private const val TIME_OUT_WRITE = 25L
    private const val TIME_OUT_READ = 25L

    @Singleton
    @Provides
    fun provideRetrofit(): Retrofit = Retrofit.Builder()
        .baseUrl("https://rickandmortyapi.com/api/")
        .addConverterFactory(GsonConverterFactory.create())
        .client(
            OkHttpClient.Builder()
                .connectTimeout(TIME_OUT_CONNECT, TimeUnit.SECONDS)
                .writeTimeout(TIME_OUT_WRITE, TimeUnit.SECONDS)
                .readTimeout(TIME_OUT_READ, TimeUnit.SECONDS)
                .dispatcher(Dispatcher().apply { maxRequests = MAX_REQUEST_COUNT })
                .build()
        )
        .build()
}