package com.rviannaoliveira.vreddit.data.repository

import android.arch.persistence.room.Database
import android.arch.persistence.room.RoomDatabase
import com.rviannaoliveira.vreddit.modal.CommentData
import com.rviannaoliveira.vreddit.modal.NewsData

/**
 * Criado por rodrigo on 21/10/17.
 */
@Database(entities = arrayOf(NewsData::class, CommentData::class), version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun getNewsDao(): NewsDao
    abstract fun getCommentsDao(): CommentsDao
}