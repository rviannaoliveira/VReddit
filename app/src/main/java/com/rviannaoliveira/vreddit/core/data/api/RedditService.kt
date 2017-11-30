package com.rviannaoliveira.vreddit.core.data.api

import com.rviannaoliveira.vreddit.core.model.CommentsDataNvl2Response
import com.rviannaoliveira.vreddit.core.model.NewsDataResponse
import io.reactivex.Maybe
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

/**
 * Criado por rodrigo on 18/10/17.
 */

interface RedditService {

    @GET("new/.json")
    fun getNewReddits(@Query("limit") limit: Int): Maybe<NewsDataResponse>

    @GET("new/.json")
    fun getNextPageNewReddit(@Query("limit") limit: Int, @Query("after") after: String): Maybe<NewsDataResponse>

    @GET("comments/{id}/.json")
    fun getAllCommentsNew(@Path("id") id: String): Maybe<List<CommentsDataNvl2Response>>
}





