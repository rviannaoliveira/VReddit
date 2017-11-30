package com.rviannaoliveira.vreddit.core.di

import com.rviannaoliveira.vreddit.AppApplication
import com.rviannaoliveira.vreddit.core.global.DataConstants.API_URL
import com.rviannaoliveira.vreddit.core.global.DataConstants.DATABASE_URL

/**
 * Criado por rodrigo on 29/11/17.
 */
object VRedditInjector {
    lateinit var vRedditComponent: VRedditComponent
        private set

    fun initialize(appApplication: AppApplication) {
        vRedditComponent = DaggerVRedditComponent.builder()
                .networkModule(NetworkModule(API_URL))
                .serviceModule(ServiceModule())
                .roomModule(RoomModule(appApplication, DATABASE_URL))
                .build()
    }
}