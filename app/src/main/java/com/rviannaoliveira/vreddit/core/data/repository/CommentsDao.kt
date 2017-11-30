package com.rviannaoliveira.vreddit.core.data.repository

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.Query
import com.rviannaoliveira.vreddit.core.model.CommentData
import io.reactivex.Maybe

/**
 * Criado por rodrigo on 21/10/17.
 */
@Dao
interface CommentsDao {
    @Query("SELECT * FROM comments WHERE id = :id")
    fun getCommentsForId(id: String): Maybe<List<CommentData>>

    @Insert
    fun insert(commentData: CommentData)
}