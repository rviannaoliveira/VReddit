package com.rviannaoliveira.vreddit.core.di

import android.arch.persistence.room.Room
import com.rviannaoliveira.vreddit.AppApplication
import com.rviannaoliveira.vreddit.core.data.repository.AppDatabase
import com.rviannaoliveira.vreddit.core.data.repository.CommentsDao
import com.rviannaoliveira.vreddit.core.data.repository.NewsDao
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

/**
 * Criado por rodrigo on 30/11/17.
 */
@Module
class RoomModule(application: AppApplication, urlDatabase: String) {
    private var appDatabase: AppDatabase = Room.databaseBuilder(application, AppDatabase::class.java, urlDatabase).build()

    @Provides
    @Singleton
    fun providesRoomDatabase(): AppDatabase {
        return appDatabase
    }

    @Provides
    @Singleton
    fun providesNewsDao(): NewsDao {
        return appDatabase.getNewsDao()
    }

    @Provides
    @Singleton
    fun providesCommentsDao(): CommentsDao {
        return appDatabase.getCommentsDao()
    }

}