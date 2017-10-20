package com.rviannaoliveira.vreddit.data

import com.rviannaoliveira.vreddit.DataManagerInterface
import com.rviannaoliveira.vreddit.data.api.RemoteDataSource
import com.rviannaoliveira.vreddit.data.api.RestApiDataSource
import com.rviannaoliveira.vreddit.modal.RedditDataResponse
import io.reactivex.Maybe

/**
 * Criado por rodrigo on 18/10/17.
 */
class DataManager(private val restApiDataSource: RemoteDataSource = RestApiDataSource()) : DataManagerInterface {

    override fun getNextPageNewReddit(after: String): Maybe<RedditDataResponse> {
        return restApiDataSource.getNextPageNewReddit(after)
    }

    override fun getNewReddit(): Maybe<RedditDataResponse> {
        return restApiDataSource.getNewReddits()

    }


}