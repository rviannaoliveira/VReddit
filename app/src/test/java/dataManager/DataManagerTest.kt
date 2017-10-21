package dataManager

import com.nhaarman.mockito_kotlin.whenever
import com.rviannaoliveira.vreddit.BuildConfig
import com.rviannaoliveira.vreddit.data.DataManager
import com.rviannaoliveira.vreddit.data.DataManagerInterface
import com.rviannaoliveira.vreddit.data.api.RemoteDataSource
import com.rviannaoliveira.vreddit.data.repository.CachedRepository
import com.rviannaoliveira.vreddit.modal.*
import com.rviannaoliveira.vreddit.util.DefaultData
import io.reactivex.Maybe
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.junit.MockitoJUnitRunner
import org.robolectric.annotation.Config

/**
 * Criado por rodrigo on 21/10/17.
 */
@RunWith(MockitoJUnitRunner::class)
@Config(constants = BuildConfig::class)
class DataManagerTest {
    @Mock private
    lateinit var dataManager: DataManagerInterface

    @Mock private
    lateinit var apiDataResource: RemoteDataSource

    @Mock private
    lateinit var repositoryData: CachedRepository

    @Before
    fun setUp() {
        dataManager = getDataManager()
    }

    @Test
    fun getNewRedditsAndVerifyIfTheSameValue() {
        val redditNewsDataResponse = getRedditNewsDataResponse()

        whenever(apiDataResource.getNewReddits()).thenReturn(Maybe.just(redditNewsDataResponse))
        dataManager.getNewsReddits()
                .test()
                .assertValue(redditNewsDataResponse)
    }

    @Test
    fun getNextPageNewRedditsAndVerifyIfTheSameValue() {
        val redditNewsDataResponse = getRedditNewsDataResponse()
        val after = "wsd23"

        whenever(apiDataResource.getNextPageNewReddit(Mockito.anyString())).thenReturn(Maybe.just(redditNewsDataResponse))
        dataManager.getNextPageNewReddit(after)
                .test()
                .assertValue(redditNewsDataResponse)
    }

    @Test
    fun getAllNewsLocalNewRedditsAndVerifyIfTheSameValue() {
        val listRedditNewsData = mutableListOf(getRedditNewsData())

        whenever(repositoryData.getAllNews()).thenReturn(Maybe.just(listRedditNewsData))
        dataManager.getAllNewsLocal()
                .test()
                .assertValue(listRedditNewsData)
    }

    @Test
    fun getAllCommentsNewAndVerifyIfTheSameValue() {
        val redditCommentsDataNvl2ResponseIndex0 = getRedditCommentsDataNvl2Response("abcd")
        val redditCommentsDataNvl2ResponseIndex1 = getRedditCommentsDataNvl2Response()
        val listCallComments = listOf(redditCommentsDataNvl2ResponseIndex0, redditCommentsDataNvl2ResponseIndex1)

        whenever(apiDataResource.getAllCommentsNew(Mockito.anyString())).thenReturn(Maybe.just(listCallComments))

        dataManager.getAllCommentsNew(Mockito.anyString())
                .test()
                .assertValue({ (reddiCommentData) ->
                    reddiCommentData.id == redditCommentsDataNvl2ResponseIndex0.data!!.children[0].data!!.id
                })
    }


    private fun getDataManager(): DataManager {
        return DataManager(apiDataResource, repositoryData)
    }

    private fun getRedditNewsDataResponse(): RedditNewsDataResponse {
        val redditNewsData = getRedditNewsData()
        val redditNewsChildrenDataNvl2Response = RedditNewsChildrenDataNvl2Response(redditNewsData)
        val list = mutableListOf(redditNewsChildrenDataNvl2Response)
        val redditNewsChildrenResponse = RedditNewsChildrenResponse(list)
        return RedditNewsDataResponse(redditNewsChildrenResponse)
    }

    private fun getRedditNewsData(): RedditNewsData {
        return RedditNewsData()
    }

    private fun getRedditCommentsDataNvl2Response(id: String = DefaultData.getString()): RedditCommentsDataNvl2Response {
        val redditCommentData = RedditCommentData().apply {
            this.id = id
        }

        val redditCommentsChildrenDataNvl2Response = RedditCommentsChildrenDataNvl2Response(redditCommentData)
        val list = listOf(redditCommentsChildrenDataNvl2Response)
        val redditCommentsChildrenResponse = RedditCommentsChildrenResponse(list)
        return RedditCommentsDataNvl2Response(redditCommentsChildrenResponse)
    }
}