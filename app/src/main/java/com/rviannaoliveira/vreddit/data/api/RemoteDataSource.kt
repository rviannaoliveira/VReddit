package com.rviannaoliveira.vreddit.data.api

import com.rviannaoliveira.vreddit.modal.RedditCommentsDataNvl2Response
import com.rviannaoliveira.vreddit.modal.RedditNewsDataResponse
import io.reactivex.Maybe

/**
 * Criado por rodrigo on 18/10/17.
 */
interface RemoteDataSource {
    fun getNewReddits(): Maybe<RedditNewsDataResponse>
    fun getNextPageNewReddit(after: String): Maybe<RedditNewsDataResponse>
    fun getAllCommentsNew(id: String): Maybe<List<RedditCommentsDataNvl2Response>>
}