package com.rviannaoliveira.vreddit.data.api

import com.rviannaoliveira.vreddit.modal.RedditNewsDataResponse
import io.reactivex.Maybe
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

/**
 * Criado por rodrigo on 18/10/17.
 */
class RestApiDataSource(private val redditService: RedditService = RedditClient().createService(RedditService::class.java)) : RemoteDataSource {
    companion object {
        private val LIMIT_NEWS = 30
    }

    override fun getNewReddits(): Maybe<List<RedditNewsDataResponse>> {
        return redditService.getNewReddits(LIMIT_NEWS)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .concatMap({ dataWrappers ->
                    val reddits = mutableListOf<RedditNewsDataResponse>()

                    dataWrappers.data?.children?.forEach { redditChildrenDataNvl2Response ->
                        redditChildrenDataNvl2Response.data?.let { reddits.add(it) }
                    }
                    Maybe.just(reddits)
                })
    }
}