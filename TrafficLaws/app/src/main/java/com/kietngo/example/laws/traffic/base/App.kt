package com.kietngo.example.laws.traffic.base

import android.app.Application
import com.kietngo.example.laws.traffic.BuildConfig
import timber.log.Timber

class App : Application() {
    override fun onCreate() {
        super.onCreate()

        if(BuildConfig.DEBUG){
            Timber.plant(Timber.DebugTree())
        }
        Timber.d("Init Timber")
    }
}