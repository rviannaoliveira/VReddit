package com.rviannaoliveira.vreddit.data

import com.rviannaoliveira.vreddit.data.api.RemoteDataSource
import com.rviannaoliveira.vreddit.data.api.RestApiDataSource
import com.rviannaoliveira.vreddit.modal.RedditCommentData
import com.rviannaoliveira.vreddit.modal.RedditNewsDataResponse
import io.reactivex.Maybe
import timber.log.Timber

/**
 * Criado por rodrigo on 18/10/17.
 */
class DataManager(private val restApiDataSource: RemoteDataSource = RestApiDataSource()) : DataManagerInterface {
    override fun getNextPageNewReddit(after: String): Maybe<RedditNewsDataResponse> {
        return restApiDataSource.getNextPageNewReddit(after)
                .doOnError({ error -> Timber.w(error) })
    }

    override fun getNewReddit(): Maybe<RedditNewsDataResponse> {
        return restApiDataSource.getNewReddits()
                .doOnError({ error -> Timber.w(error) })
    }

    override fun getAllCommentsNew(id: String): Maybe<MutableList<RedditCommentData>> {
        return restApiDataSource.getAllCommentsNew(id)
                .concatMap({ dataWrappers ->
                    val comments = mutableListOf<RedditCommentData>()
                    dataWrappers[1].data?.children?.forEach { redditCommentsChildrenDataNvl2Response ->
                        redditCommentsChildrenDataNvl2Response.data?.let { comments.add(it) }
                    }
                    Maybe.just(comments)
                })
                .doOnError({ error -> Timber.w(error) })
    }
}