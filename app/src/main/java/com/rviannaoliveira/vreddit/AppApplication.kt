package com.rviannaoliveira.vreddit

import android.app.Application

/**
 * Criado por rodrigo on 18/10/17.
 */
class AppApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        appContext = this
    }

    companion object {
        lateinit var appContext: AppApplication
            private set
    }
}