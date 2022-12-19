package com.example.testpractice

import android.app.Application
import com.example.data.di.dataModule
import com.example.domain.di.domainModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class MainApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            modules(dataModule + domainModule)
            androidContext(this@MainApplication)
        }
    }
}