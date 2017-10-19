package com.rviannaoliveira.vreddit.data

import com.rviannaoliveira.vreddit.DataManagerInterface
import com.rviannaoliveira.vreddit.data.api.RemoteDataSource
import com.rviannaoliveira.vreddit.data.api.RestApiDataSource
import com.rviannaoliveira.vreddit.modal.RedditNewsDataResponse
import io.reactivex.Maybe

/**
 * Criado por rodrigo on 18/10/17.
 */
class DataManager(private val restApiDataSource: RemoteDataSource = RestApiDataSource()) : DataManagerInterface {

    override fun getNewReddit(): Maybe<List<RedditNewsDataResponse>> {
        return restApiDataSource.getNewReddits()
    }
}