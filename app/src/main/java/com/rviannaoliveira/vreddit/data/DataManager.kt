package com.rviannaoliveira.vreddit.data

import com.rviannaoliveira.vreddit.data.api.RemoteDataSource
import com.rviannaoliveira.vreddit.data.api.RestApiDataSource
import com.rviannaoliveira.vreddit.data.repository.CachedRepository
import com.rviannaoliveira.vreddit.data.repository.RedditRepositoryDataSource
import com.rviannaoliveira.vreddit.modal.CommentData
import com.rviannaoliveira.vreddit.modal.NewsData
import com.rviannaoliveira.vreddit.modal.NewsDataResponse
import io.reactivex.Maybe
import timber.log.Timber

/**
 * Criado por rodrigo on 18/10/17.
 */
class DataManager(private val apiDataSource: RemoteDataSource = RestApiDataSource(),
                  private val cachedRepository: CachedRepository = RedditRepositoryDataSource()) : DataManagerInterface {

    override fun getNextPageNewReddit(after: String): Maybe<NewsDataResponse> {
        return apiDataSource.getNextPageNewReddit(after)
                .doOnError({ error -> Timber.w(error) })
    }

    override fun getNewsReddits(): Maybe<NewsDataResponse> {
        return apiDataSource.getNewReddits()
                .doOnError({ error -> Timber.w(error) })
    }

    override fun getAllNewsLocal(): Maybe<MutableList<NewsData>> {
        return cachedRepository.getAllNews()
    }


    override fun insertNews(newsData: NewsData) {
        cachedRepository.insertNews(newsData)
    }

    override fun getAllCommentsNew(id: String): Maybe<MutableList<CommentData>> {
        return apiDataSource.getAllCommentsNew(id)
                .concatMap({ dataWrappers ->
                    val comments = mutableListOf<CommentData>()
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

    override fun getAllCommentsNewLocal(id: String): Maybe<List<CommentData>> {
        return cachedRepository.getCommentsForId(id)
    }

    override fun insertComments(commentData: CommentData) {
        cachedRepository.insertComments(commentData)
    }
}