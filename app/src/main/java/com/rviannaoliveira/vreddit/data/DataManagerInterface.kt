package com.rviannaoliveira.vreddit.data

import com.rviannaoliveira.vreddit.modal.RedditCommentData
import com.rviannaoliveira.vreddit.modal.RedditNewsDataResponse
import io.reactivex.Maybe

/**
 * Criado por rodrigo on 18/10/17.
 */
interface DataManagerInterface {
    fun getNewReddit(): Maybe<RedditNewsDataResponse>
    fun getNextPageNewReddit(after: String): Maybe<RedditNewsDataResponse>
    fun getAllCommentsNew(id: String): Maybe<MutableList<RedditCommentData>>
}
