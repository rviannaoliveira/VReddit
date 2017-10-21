package com.rviannaoliveira.vreddit.data.repository

import com.rviannaoliveira.vreddit.modal.RedditCommentData
import com.rviannaoliveira.vreddit.modal.RedditNewsData
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

    override fun getAllNews(): Maybe<MutableList<RedditNewsData>> {
        return newsDao.getAll()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
    }

    override fun insertNews(redditNewsData: RedditNewsData) {
        Single.fromCallable({ newsDao.insert(redditNewsData) })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ Timber.d("Success") },
                        { error -> Timber.w("Error>$error") })
    }

    override fun getCommentsForId(id: String): Maybe<List<RedditCommentData>> {
        return commentsDao.getCommentsForId(id)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
    }

    override fun insertComments(redditCommentData: RedditCommentData) {
        Single.fromCallable({ commentsDao.insert(redditCommentData) })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ Timber.d("Success") },
                        { error -> Timber.w("Error>$error") })
    }
}