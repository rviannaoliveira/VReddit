package dataManager

import com.nhaarman.mockito_kotlin.whenever
import com.rviannaoliveira.vreddit.BuildConfig
import com.rviannaoliveira.vreddit.data.DataManager
import com.rviannaoliveira.vreddit.data.DataManagerInterface
import com.rviannaoliveira.vreddit.data.api.RemoteDataSource
import com.rviannaoliveira.vreddit.data.repository.CachedRepository
import fake.RedditFakeFactory
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
        val redditNewsDataResponse = RedditFakeFactory.fakeRedditNewsDataResponse
        whenever(apiDataResource.getNewReddits()).thenReturn(Maybe.just(redditNewsDataResponse))
        dataManager.getNewsReddits()
                .test()
                .assertValue(redditNewsDataResponse)
    }

    @Test
    fun getNextPageNewRedditsAndVerifyIfTheSameValue() {
        val redditNewsDataResponse = RedditFakeFactory.fakeRedditNewsDataResponse
        val after = "wsd23"

        whenever(apiDataResource.getNextPageNewReddit(Mockito.anyString())).thenReturn(Maybe.just(redditNewsDataResponse))
        dataManager.getNextPageNewReddit(after)
                .test()
                .assertValue(redditNewsDataResponse)
    }

    @Test
    fun getAllNewsLocalNewRedditsAndVerifyIfTheSameValue() {
        val listRedditNewsData = mutableListOf(RedditFakeFactory.fakeRedditNewsData)

        whenever(repositoryData.getAllNews()).thenReturn(Maybe.just(listRedditNewsData))
        dataManager.getAllNewsLocal()
                .test()
                .assertValue(listRedditNewsData)
    }

    @Test
    fun getAllCommentsNewAndVerifyIfTheSameValue() {
        val redditCommentsDataNvl2ResponseIndex0 = RedditFakeFactory.getRedditCommentsDataNvl2Response("abcd")
        val redditCommentsDataNvl2ResponseIndex1 = RedditFakeFactory.getRedditCommentsDataNvl2Response()
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
}