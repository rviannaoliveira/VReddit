package com.rviannaoliveira.vreddit.core.data.repository

import com.rviannaoliveira.vreddit.modal.CommentData
import com.rviannaoliveira.vreddit.modal.NewsData
import io.reactivex.Maybe

/**
 * Criado por rodrigo on 18/10/17.
 */
interface CachedRepository {
    fun getAllNews(): Maybe<MutableList<NewsData>>
    fun insertNews(newsData: NewsData)
    fun getCommentsForId(id: String): Maybe<List<CommentData>>
    fun insertComments(commentData: CommentData)
}