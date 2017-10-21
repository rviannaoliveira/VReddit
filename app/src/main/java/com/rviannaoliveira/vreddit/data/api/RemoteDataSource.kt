package com.rviannaoliveira.vreddit.data.api

import com.rviannaoliveira.vreddit.modal.RedditCommentDataResponse
import com.rviannaoliveira.vreddit.modal.RedditDataResponse
import io.reactivex.Maybe

/**
 * Criado por rodrigo on 18/10/17.
 */
interface RemoteDataSource {
    fun getNewReddits(): Maybe<RedditDataResponse>
    fun getNextPageNewReddit(after: String): Maybe<RedditDataResponse>
    fun getAllCommentsNew(id: String): Maybe<RedditCommentDataResponse>
}