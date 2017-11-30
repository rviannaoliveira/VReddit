package com.rviannaoliveira.vreddit.core.di

import com.rviannaoliveira.vreddit.core.data.DataManager
import com.rviannaoliveira.vreddit.core.data.DataManagerInterface
import com.rviannaoliveira.vreddit.core.data.api.RedditService
import com.rviannaoliveira.vreddit.core.data.api.RemoteDataSource
import com.rviannaoliveira.vreddit.core.data.api.RestApiDataSource
import com.rviannaoliveira.vreddit.core.data.repository.CachedRepository
import com.rviannaoliveira.vreddit.core.data.repository.CommentsDao
import com.rviannaoliveira.vreddit.core.data.repository.NewsDao
import com.rviannaoliveira.vreddit.core.data.repository.RedditRepositoryDataSource
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

/**
 * Criado por rodrigo on 29/11/17.
 */

@Module(includes = arrayOf(NetworkModule::class, RoomModule::class))
class ServiceModule {

    @Provides
    @Singleton
    fun providesApiDataSource(redditService: RedditService) : RemoteDataSource = RestApiDataSource(redditService)

    @Provides
    @Singleton
    fun providesRepositoryDataSource(newsDao: NewsDao, commentsDao: CommentsDao):
            CachedRepository = RedditRepositoryDataSource(newsDao, commentsDao)

    @Provides
    @Singleton
    fun providesDataManager(remoteDataSource: RemoteDataSource, redditRepositoryDataSource: RedditRepositoryDataSource):
            DataManagerInterface = DataManager(remoteDataSource, redditRepositoryDataSource)
}