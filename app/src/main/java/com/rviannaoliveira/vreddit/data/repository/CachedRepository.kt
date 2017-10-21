package com.rviannaoliveira.vreddit.data.repository

import com.rviannaoliveira.vreddit.modal.RedditCommentData
import com.rviannaoliveira.vreddit.modal.RedditNewsData
import io.reactivex.Maybe

/**
 * Criado por rodrigo on 18/10/17.
 */
interface CachedRepository {
    fun getAllNews(): Maybe<MutableList<RedditNewsData>>
    fun insertNews(redditNewsData: RedditNewsData)
    fun getCommentsForId(id: String): Maybe<List<RedditCommentData>>
    fun insertComments(redditCommentData: RedditCommentData)
}