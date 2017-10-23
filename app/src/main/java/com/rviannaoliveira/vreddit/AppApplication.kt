package com.rviannaoliveira.vreddit

import android.app.Application
import com.crashlytics.android.Crashlytics
import com.facebook.stetho.Stetho
import com.rviannaoliveira.vreddit.data.repository.AppDatabaseFactory
import com.rviannaoliveira.vreddit.global.VRedditTimber
import io.fabric.sdk.android.Fabric
import okhttp3.HttpUrl
import timber.log.Timber


/**
 * Criado por rodrigo on 18/10/17.
 */
class AppApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        appContext = this
        if (BuildConfig.DEBUG) {
            initStetho()
        }
        Timber.plant(VRedditTimber())
        Fabric.with(this, Crashlytics())
        initRoom()
    }

    companion object {
        lateinit var appContext: AppApplication
            private set
        var URL: HttpUrl? = null
    }

    private fun initRoom() {
        AppDatabaseFactory.init(this)
    }

    private fun initStetho() {
        Stetho.initialize(Stetho.newInitializerBuilder(this)
                .enableWebKitInspector(Stetho.defaultInspectorModulesProvider(this))
                .enableDumpapp(Stetho.defaultDumperPluginsProvider(this))
                .build())
    }
}