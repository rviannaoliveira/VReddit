package com.rviannaoliveira.vreddit.core.di

import android.app.Application
import android.content.Context
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

/**
 * Criado por rodrigo on 29/11/17.
 */
@Module @Singleton
class ApplicationModule(private val applicationContext: Application) {

    @Provides @Singleton
    fun providesApplicationContext(): Context {
        return applicationContext
    }
}