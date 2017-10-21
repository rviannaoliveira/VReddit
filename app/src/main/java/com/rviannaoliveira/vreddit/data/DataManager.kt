package com.rviannaoliveira.vreddit.data

import com.rviannaoliveira.vreddit.data.api.RemoteDataSource
import com.rviannaoliveira.vreddit.data.api.RestApiDataSource
import com.rviannaoliveira.vreddit.data.repository.CachedRepository
import com.rviannaoliveira.vreddit.data.repository.RedditRepositoryDataSource
import com.rviannaoliveira.vreddit.modal.RedditCommentData
import com.rviannaoliveira.vreddit.modal.RedditNewsData
import com.rviannaoliveira.vreddit.modal.RedditNewsDataResponse
import io.reactivex.Maybe
import timber.log.Timber

/**
 * Criado por rodrigo on 18/10/17.
 */
class DataManager(private val apiDataSource: RemoteDataSource = RestApiDataSource(),
                  private val cachedRepository: CachedRepository = RedditRepositoryDataSource()) : DataManagerInterface {
    override fun getNextPageNewReddit(after: String): Maybe<RedditNewsDataResponse> {
        return apiDataSource.getNextPageNewReddit(after)
                .doOnError({ error -> Timber.w(error) })
    }

    override fun getNewReddit(): Maybe<RedditNewsDataResponse> {
        return apiDataSource.getNewReddits()
                .doOnError({ error -> Timber.w(error) })
    }

    override fun getAllNewsLocal(): Maybe<MutableList<RedditNewsData>> {
        return cachedRepository.getAllNews()
    }


    override fun insertNews(redditNewsData: RedditNewsData) {
        cachedRepository.insertNews(redditNewsData)
    }

    override fun getAllCommentsNew(id: String): Maybe<MutableList<RedditCommentData>> {
        return apiDataSource.getAllCommentsNew(id)
                .concatMap({ dataWrappers ->
                    val comments = mutableListOf<RedditCommentData>()
                    val idNews = dataWrappers[0].data?.children?.first()?.data?.id

                    dataWrappers[1].data?.children?.forEach { redditCommentsChildrenDataNvl2Response ->
                        redditCommentsChildrenDataNvl2Response.data?.let {
                            it.id = idNews ?: ""
                            comments.add(it)
                            insertComments(it)
                        }
                    }

                    Maybe.just(comments)
                })
                .doOnError({ error -> Timber.w(error) })
    }

    override fun getAllCommentsNewLocal(id: String): Maybe<List<RedditCommentData>> {
        return cachedRepository.getCommentsForId(id)
    }

    override fun insertComments(redditCommentData: RedditCommentData) {
        cachedRepository.insertComments(redditCommentData)
    }
}