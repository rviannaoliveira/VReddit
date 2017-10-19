package com.rviannaoliveira.vreddit.data.api

import com.rviannaoliveira.vreddit.modal.RedditDataResponse
import io.reactivex.Maybe
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * Criado por rodrigo on 18/10/17.
 */

interface RedditService {

    @GET("new/.json")
    fun getNewReddits(@Query("limit") limit: Int): Maybe<RedditDataResponse>
}





