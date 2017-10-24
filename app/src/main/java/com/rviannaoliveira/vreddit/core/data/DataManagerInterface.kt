package com.rviannaoliveira.vreddit.core.data

import com.rviannaoliveira.vreddit.modal.CommentData
import com.rviannaoliveira.vreddit.modal.NewsData
import com.rviannaoliveira.vreddit.modal.NewsDataResponse
import io.reactivex.Maybe

/**
 * Criado por rodrigo on 18/10/17.
 */
interface DataManagerInterface {
    fun getNewsReddits(): Maybe<NewsDataResponse>
    fun getNextPageNewReddit(after: String): Maybe<NewsDataResponse>
    fun insertNews(newsData: NewsData)
    fun getAllNewsLocal(): Maybe<MutableList<NewsData>>

    fun getAllCommentsNew(id: String): Maybe<MutableList<CommentData>>
    fun getAllCommentsNewLocal(id: String): Maybe<List<CommentData>>
    fun insertComments(commentData: CommentData)
}
