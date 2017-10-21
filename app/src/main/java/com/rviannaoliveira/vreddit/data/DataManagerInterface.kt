package com.rviannaoliveira.vreddit.data

import com.rviannaoliveira.vreddit.modal.RedditCommentData
import com.rviannaoliveira.vreddit.modal.RedditNewsData
import com.rviannaoliveira.vreddit.modal.RedditNewsDataResponse
import io.reactivex.Maybe

/**
 * Criado por rodrigo on 18/10/17.
 */
interface DataManagerInterface {
    fun getNewsReddits(): Maybe<RedditNewsDataResponse>
    fun getNextPageNewReddit(after: String): Maybe<RedditNewsDataResponse>
    fun insertNews(redditNewsData: RedditNewsData)
    fun getAllNewsLocal(): Maybe<MutableList<RedditNewsData>>

    fun getAllCommentsNew(id: String): Maybe<MutableList<RedditCommentData>>
    fun getAllCommentsNewLocal(id: String): Maybe<List<RedditCommentData>>
    fun insertComments(redditCommentData: RedditCommentData)
}
