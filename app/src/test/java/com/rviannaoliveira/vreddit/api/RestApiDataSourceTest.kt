package com.rviannaoliveira.vreddit.api

import com.nhaarman.mockito_kotlin.whenever
import com.rviannaoliveira.vreddit.BuildConfig
import com.rviannaoliveira.vreddit.Rx2ExternalResources
import com.rviannaoliveira.vreddit.core.data.api.RedditService
import com.rviannaoliveira.vreddit.core.data.api.RestApiDataSource
import com.rviannaoliveira.vreddit.fake.RedditFakeFactory
import io.reactivex.Maybe
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.junit.MockitoJUnit
import org.mockito.junit.MockitoJUnitRunner
import org.robolectric.annotation.Config

/**
 * Criado por rodrigo on 21/10/17.
 */

@RunWith(MockitoJUnitRunner::class)
@Config(constants = BuildConfig::class)
class RestApiDataSourceTest {
    @get:Rule
    val mockito = MockitoJUnit.rule()

    @get:Rule
    val rxExternalResources = Rx2ExternalResources()

    @Mock private
    lateinit var redditService: RedditService


    @Test
    fun call_all_news_posts() {
        val restApiDataSource = getApiDataResource()
        val redditNewsDataResponse = RedditFakeFactory.fakeRedditNewsDataResponse

        whenever(redditService.getNewReddits(RestApiDataSource.LIMIT_NEWS)).thenReturn(Maybe.just(redditNewsDataResponse))

        restApiDataSource.getNewReddits()
                .test()
                .assertComplete()
                .assertValue(redditNewsDataResponse)
    }

    @Test
    fun call_all_comments() {
        val restApiDataSource = getApiDataResource()
        val listRedditCommentsDataNvl2Response = listOf(RedditFakeFactory.fakeRedditCommentsDataNvl2Response)

        whenever(redditService.getAllCommentsNew(Mockito.anyString())).thenReturn(Maybe.just(listRedditCommentsDataNvl2Response))

        restApiDataSource.getAllCommentsNew("")
                .test()
                .assertComplete()
                .assertValue(listRedditCommentsDataNvl2Response)
    }

    private fun getApiDataResource(): RestApiDataSource {
        return RestApiDataSource(redditService)
    }
}