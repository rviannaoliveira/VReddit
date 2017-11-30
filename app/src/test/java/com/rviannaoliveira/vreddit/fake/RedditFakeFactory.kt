package com.rviannaoliveira.vreddit.fake

import com.rviannaoliveira.vreddit.core.model.*
import com.rviannaoliveira.vreddit.core.util.DefaultData

/**
 * Criado por rodrigo on 21/10/17.
 */
object RedditFakeFactory {

    val fakeRedditNewsDataResponse by lazy {
        val newsData = fakeRedditNewsData
        val newsChildrenDataNvl2Response = NewsChildrenDataNvl2Response(newsData)
        val list = mutableListOf(newsChildrenDataNvl2Response)
        val redditNewsChildrenResponse = NewsChildrenResponse(list)
        NewsDataResponse(redditNewsChildrenResponse)
    }
    val fakeRedditNewsData by lazy {
        NewsData()
    }

    val fakeRedditCommentsDataNvl2Response by lazy {
        getRedditCommentsDataNvl2Response()
    }

    fun getRedditCommentsDataNvl2Response(id: String = DefaultData.getString()): CommentsDataNvl2Response {
        val redditCommentData = CommentData().apply {
            this.id = id
        }

        val commentsChildrenDataNvl2Response = CommentsChildrenDataNvl2Response(redditCommentData)
        val list = listOf(commentsChildrenDataNvl2Response)
        val commentsChildrenResponse = CommentsDataResponse(list)
        return CommentsDataNvl2Response(commentsChildrenResponse)
    }
}
