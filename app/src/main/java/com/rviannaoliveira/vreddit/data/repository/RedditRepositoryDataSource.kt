package com.rviannaoliveira.vreddit.data.repository

import com.rviannaoliveira.vreddit.modal.CommentData
import com.rviannaoliveira.vreddit.modal.NewsData
import io.reactivex.Maybe
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import timber.log.Timber

/**
 * Criado por rodrigo on 18/10/17.
 */
class RedditRepositoryDataSource(private val newsDao: NewsDao = AppDatabaseFactory.getDefaultInstance().getNewsDao(),
                                 private val commentsDao: CommentsDao = AppDatabaseFactory.getDefaultInstance().getCommentsDao()) : CachedRepository {

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