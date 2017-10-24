package com.rviannaoliveira.vreddit.data.api

import com.rviannaoliveira.vreddit.modal.CommentsDataNvl2Response
import com.rviannaoliveira.vreddit.modal.NewsDataResponse
import io.reactivex.Maybe
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

/**
 * Criado por rodrigo on 18/10/17.
 */
class RestApiDataSource(private val redditService: RedditService = RedditClient().createService(RedditService::class.java)) : RemoteDataSource {

    companion object {
        val LIMIT_NEWS = 10
    }

    override fun getNewReddits(): Maybe<NewsDataResponse> {
        return redditService.getNewReddits(LIMIT_NEWS)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
    }

    override fun getNextPageNewReddit(after: String): Maybe<NewsDataResponse> {
        return redditService.getNextPageNewReddit(LIMIT_NEWS, after)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
    }

    override fun getAllCommentsNew(id: String): Maybe<List<CommentsDataNvl2Response>> {
        return redditService.getAllCommentsNew(id)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
    }

}