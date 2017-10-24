package com.rviannaoliveira.vreddit.data.api

import com.rviannaoliveira.vreddit.modal.CommentsDataNvl2Response
import com.rviannaoliveira.vreddit.modal.NewsDataResponse
import io.reactivex.Maybe

/**
 * Criado por rodrigo on 18/10/17.
 */
interface RemoteDataSource {
    fun getNewReddits(): Maybe<NewsDataResponse>
    fun getNextPageNewReddit(after: String): Maybe<NewsDataResponse>
    fun getAllCommentsNew(id: String): Maybe<List<CommentsDataNvl2Response>>
}