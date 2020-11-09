package com.test.guardiannews

import android.app.Application
import com.test.guardiannews.di.applicationModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level

class GuardianNewsApp : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidLogger(Level.NONE)
            androidContext(this@GuardianNewsApp)
            modules(applicationModule)
        }
    }
}