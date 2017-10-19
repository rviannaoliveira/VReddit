package com.rviannaoliveira.vreddit

import com.rviannaoliveira.vreddit.modal.RedditNewsDataResponse
import io.reactivex.Maybe

/**
 * Criado por rodrigo on 18/10/17.
 */
interface DataManagerInterface {
    fun getNewReddit(): Maybe<List<RedditNewsDataResponse>>
}
