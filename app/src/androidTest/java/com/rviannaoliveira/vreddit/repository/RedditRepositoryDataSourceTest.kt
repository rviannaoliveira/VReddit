package com.rviannaoliveira.vreddit.repository

import android.arch.core.executor.testing.InstantTaskExecutorRule
import android.arch.persistence.room.Room
import android.support.test.InstrumentationRegistry
import android.support.test.runner.AndroidJUnit4
import com.rviannaoliveira.vreddit.data.repository.AppDatabase
import com.rviannaoliveira.vreddit.data.repository.CommentsDao
import com.rviannaoliveira.vreddit.data.repository.NewsDao
import com.rviannaoliveira.vreddit.modal.CommentData
import com.rviannaoliveira.vreddit.modal.NewsData
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import java.io.IOException

/**
 * Criado por rodrigo on 21/10/17.
 */
@RunWith(AndroidJUnit4::class)
class RedditRepositoryDataSourceTest {
    lateinit var commentsDao: CommentsDao
    lateinit var newsDao: NewsDao
    lateinit var appDatabase: AppDatabase

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    @Before
    fun createDb() {
        val context = InstrumentationRegistry.getTargetContext()
        appDatabase = Room.inMemoryDatabaseBuilder(context, AppDatabase::class.java)
                .allowMainThreadQueries()
                .build()
        commentsDao = appDatabase.getCommentsDao()
        newsDao = appDatabase.getNewsDao()
    }

    @Test
    @Throws(Exception::class)
    fun writeNews_and_CompareIfReturnTheSame() {
        val redditNewsData1 = NewsData("a")
        val redditNewsData2 = NewsData("b")
        val list = mutableListOf(redditNewsData1, redditNewsData2)

        newsDao.insert(redditNewsData1)
        newsDao.insert(redditNewsData2)

        newsDao.getAll()
                .test()
                .assertValues(list)
    }

    @Test
    @Throws(Exception::class)
    fun writeComments_and_CompareIfReturnTheSame() {
        val idTest = "a"
        val redditCommentData1 = CommentData().apply { id = idTest }
        val redditCommentData2 = CommentData().apply { id = "b" }
        val redditCommentData3 = CommentData().apply { id = idTest }
        val list = mutableListOf(redditCommentData1, redditCommentData3)

        commentsDao.insert(redditCommentData1)
        commentsDao.insert(redditCommentData2)
        commentsDao.insert(redditCommentData3)

        commentsDao.getCommentsForId(idTest)
                .test()
                .assertValue({ list[0].id == idTest && list[1].id == idTest })
    }

    @After
    @Throws(IOException::class)
    fun closeDb() {
        appDatabase.close()
    }
}