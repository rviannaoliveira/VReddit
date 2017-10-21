package com.rviannaoliveira.vreddit.data

import com.rviannaoliveira.vreddit.modal.RedditDataResponse
import io.reactivex.Maybe

/**
 * Criado por rodrigo on 18/10/17.
 */
interface DataManagerInterface {
    fun getNewReddit(): Maybe<RedditDataResponse>
    fun getNextPageNewReddit(after: String): Maybe<RedditDataResponse>
}
