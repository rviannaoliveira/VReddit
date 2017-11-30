package com.rviannaoliveira.vreddit.core.data.repository

import com.rviannaoliveira.vreddit.core.model.CommentData
import com.rviannaoliveira.vreddit.core.model.NewsData
import io.reactivex.Maybe
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import timber.log.Timber
import javax.inject.Inject

/**
 * Criado por rodrigo on 18/10/17.
 */
class RedditRepositoryDataSource @Inject constructor(private val newsDao: NewsDao,
                                                     private val commentsDao: CommentsDao) : CachedRepository {

    override fun getAllNews(): Maybe<MutableList<NewsData>> {
        return newsDao.getAll()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
    }

    override fun insertNews(newsData: NewsData) {
        Single.fromCallable({ newsDao.insert(newsData) })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ Timber.d("Success") },
                        { error -> Timber.w("Error>$error") })
    }

    override fun getCommentsForId(id: String): Maybe<List<CommentData>> {
        return commentsDao.getCommentsForId(id)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
    }

    override fun insertComments(commentData: CommentData) {
        Single.fromCallable({ commentsDao.insert(commentData) })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ Timber.d("Success") },
                        { error -> Timber.w("Error>$error") })
    }
}