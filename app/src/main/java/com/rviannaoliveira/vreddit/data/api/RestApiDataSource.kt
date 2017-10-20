package com.rviannaoliveira.vreddit.data.api

import com.rviannaoliveira.vreddit.modal.RedditDataResponse
import io.reactivex.Maybe
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

/**
 * Criado por rodrigo on 18/10/17.
 */
class RestApiDataSource(private val redditService: RedditService = RedditClient().createService(RedditService::class.java)) : RemoteDataSource {
    companion object {
        private val LIMIT_NEWS = 10
    }

    override fun getNewReddits(): Maybe<RedditDataResponse> {
        return redditService.getNewReddits(LIMIT_NEWS)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
    }

    override fun getNextPageNewReddit(after: String): Maybe<RedditDataResponse> {
        return redditService.getNextPageNewReddit(LIMIT_NEWS, after)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
    }
}