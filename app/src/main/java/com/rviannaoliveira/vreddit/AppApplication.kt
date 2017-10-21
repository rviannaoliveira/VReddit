package com.rviannaoliveira.vreddit

import android.app.Application
import timber.log.Timber

/**
 * Criado por rodrigo on 18/10/17.
 */
class AppApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        appContext = this
        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        }
    }

    companion object {
        lateinit var appContext: AppApplication
            private set
    }
}