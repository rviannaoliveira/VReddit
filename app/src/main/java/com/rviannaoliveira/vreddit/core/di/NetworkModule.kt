package com.rviannaoliveira.vreddit.core.di

import com.rviannaoliveira.vreddit.core.data.api.RedditClient
import com.rviannaoliveira.vreddit.core.data.api.RedditService
import dagger.Module
import dagger.Provides

/**
 * Criado por rodrigo on 29/11/17.
 */
@Module
class NetworkModule {

    @Provides
    fun providesRedditRestApi() : RedditService = RedditClient().createService(RedditService::class.java)
}