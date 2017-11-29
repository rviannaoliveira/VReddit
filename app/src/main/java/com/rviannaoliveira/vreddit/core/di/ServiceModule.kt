package com.rviannaoliveira.vreddit.core.di

import com.rviannaoliveira.vreddit.core.data.DataManager
import com.rviannaoliveira.vreddit.core.data.api.RedditService
import com.rviannaoliveira.vreddit.core.data.api.RemoteDataSource
import com.rviannaoliveira.vreddit.core.data.api.RestApiDataSource
import com.rviannaoliveira.vreddit.core.data.repository.RedditRepositoryDataSource
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

/**
 * Criado por rodrigo on 29/11/17.
 */

@Module(includes = arrayOf(NetworkModule::class) )
class ServiceModule {

    @Provides
    fun providesApiDataSource(redditService: RedditService) : RemoteDataSource = RestApiDataSource(redditService)

    @Provides
    fun providesRepositoryDataSource() : RedditRepositoryDataSource = RedditRepositoryDataSource()

    @Provides
    @Singleton
    fun providesDataManager(remoteDataSource: RemoteDataSource, redditRepositoryDataSource: RedditRepositoryDataSource):
            DataManager = DataManager(remoteDataSource, redditRepositoryDataSource)
}