package com.rviannaoliveira.vreddit.detail

import android.content.Intent
import android.support.test.rule.ActivityTestRule
import android.support.test.runner.AndroidJUnit4
import com.rviannaoliveira.vreddit.initMockServer
import com.rviannaoliveira.vreddit.main.MainActivity
import com.rviannaoliveira.vreddit.readFileFromAssets
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import java.net.HttpURLConnection

/**
 * Criado por rodrigo on 22/10/17.
 */
@RunWith(AndroidJUnit4::class)
class DetailFragmentTest {
    @get:Rule
    private val activityRule = ActivityTestRule(MainActivity::class.java, true, false)

    private lateinit var robo: RoboDetail
    private lateinit var server: MockWebServer

    @Before
    fun setUp() {
        robo = RoboDetail()
        server = MockWebServer()
        server.initMockServer()
        activityRule.launchActivity(Intent())
        server.enqueue(MockResponse().setResponseCode(HttpURLConnection.HTTP_OK).setBody(server.readFileFromAssets(activityRule.activity, "firstListing.json")))
    }

    @Test
    fun verify_if_description_was_loaded_completed() {
        server.enqueue(MockResponse().setResponseCode(HttpURLConnection.HTTP_OK).setBody(server.readFileFromAssets(activityRule.activity, "comments.json")))
        robo.clickItem(0)
                .verifyTextComplete("and move on.\n")
    }

    @Test
    fun verify_if_list_comments_was_loaded() {
        server.enqueue(MockResponse().setResponseCode(HttpURLConnection.HTTP_OK).setBody(server.readFileFromAssets(activityRule.activity, "comments.json")))
        robo.clickItem(0)
                .verifyRecyclerViewVisible()
    }

    @Test
    fun verify_if_message_when_list_is_empty() {
        server.enqueue(MockResponse().setResponseCode(HttpURLConnection.HTTP_OK).setBody(server.readFileFromAssets(activityRule.activity, "empty_comments.json")))
        robo.clickItem(0)
                .verifyMessageEmpty()
    }

}