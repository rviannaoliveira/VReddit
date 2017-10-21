package com.rviannaoliveira.vreddit.data.repository

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.OnConflictStrategy
import android.arch.persistence.room.Query
import com.rviannaoliveira.vreddit.modal.RedditNewsData
import io.reactivex.Maybe

/**
 * Criado por rodrigo on 21/10/17.
 */
@Dao
interface NewsDao {
    @Query("SELECT * FROM news")
    fun getAll(): Maybe<MutableList<RedditNewsData>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insert(redditNewsData: RedditNewsData)
}