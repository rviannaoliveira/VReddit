package com.rviannaoliveira.vreddit.data.api

import com.rviannaoliveira.vreddit.modal.RedditNewsDataResponse
import io.reactivex.Maybe

/**
 * Criado por rodrigo on 18/10/17.
 */
interface RemoteDataSource {
    fun getNewReddits(): Maybe<List<RedditNewsDataResponse>>
}