package com.rviannaoliveira.vreddit.core.di

import com.rviannaoliveira.vreddit.AppApplication

/**
 * Criado por rodrigo on 29/11/17.
 */
object VRedditInjector {
    lateinit var vRedditComponent: VRedditComponent
        private set

    fun initialize(appApplication: AppApplication) {
        vRedditComponent = DaggerVRedditComponent.builder()
                .applicationModule(ApplicationModule(appApplication))
                .networkModule(NetworkModule())
                .serviceModule(ServiceModule())
                .build()
    }
}