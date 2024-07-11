package com.iptrianaa.therickandmortywiki

import android.app.Application
import com.iptrianaa.therickandmortywiki.data.di.dataModule
import com.iptrianaa.therickandmortywiki.data.di.repositoryModule
import com.iptrianaa.therickandmortywiki.di.appModule
import com.iptrianaa.therickandmortywiki.di.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class App: Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidLogger()
            androidContext(this@App)
            modules(appModule, dataModule, repositoryModule, viewModelModule)
        }
    }
}