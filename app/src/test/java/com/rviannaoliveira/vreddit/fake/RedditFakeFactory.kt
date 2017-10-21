package vreddit.fake

import com.rviannaoliveira.vreddit.modal.*
import com.rviannaoliveira.vreddit.util.DefaultData

/**
 * Criado por rodrigo on 21/10/17.
 */
object RedditFakeFactory {

    val fakeRedditNewsDataResponse by lazy {
        val redditNewsData = fakeRedditNewsData
        val redditNewsChildrenDataNvl2Response = RedditNewsChildrenDataNvl2Response(redditNewsData)
        val list = mutableListOf(redditNewsChildrenDataNvl2Response)
        val redditNewsChildrenResponse = RedditNewsChildrenResponse(list)
        RedditNewsDataResponse(redditNewsChildrenResponse)
    }
    val fakeRedditNewsData by lazy {
        RedditNewsData()
    }

    val fakeRedditCommentsDataNvl2Response by lazy {
        getRedditCommentsDataNvl2Response()
    }

    fun getRedditCommentsDataNvl2Response(id: String = DefaultData.getString()): RedditCommentsDataNvl2Response {
        val redditCommentData = RedditCommentData().apply {
            this.id = id
        }

        val redditCommentsChildrenDataNvl2Response = RedditCommentsChildrenDataNvl2Response(redditCommentData)
        val list = listOf(redditCommentsChildrenDataNvl2Response)
        val redditCommentsChildrenResponse = RedditCommentsChildrenResponse(list)
        return RedditCommentsDataNvl2Response(redditCommentsChildrenResponse)
    }
}
