package com.iptriana.rickymortywiki

import android.app.Application
import com.iptriana.rickymortywiki.di.initKoin
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger

class RickyMortyApp : Application() {

    override fun onCreate() {
        super.onCreate()

        initKoin{
            androidLogger()
            androidContext(this@RickyMortyApp)
        }
    }
}